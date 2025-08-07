package plaxi.backend.service;

import jakarta.mail.Multipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.controller.TemaController;
import plaxi.backend.dto.PaginadoDto;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.dto.TemaDto;
import plaxi.backend.entity.S3Object;
import plaxi.backend.entity.Tema;
import plaxi.backend.repository.S3ObjectRepository;
import plaxi.backend.repository.TemaRepository;

import plaxi.backend.entity.Leccion;
import plaxi.backend.repository.LeccionRepository;
import plaxi.backend.dto.LeccionDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemaService {

    private static final Logger logger = LoggerFactory.getLogger(TemaController.class);

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private LeccionRepository leccionRepository;

    @Autowired
    private MinioService recursoMultimediaService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    // Obtener todos los temas
    public Page<TemaDto> getAllTemas(PaginadoDto paginadoDto) {
        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);
        Page<TemaDto> temas = temaRepository.findAllPaginado(page);

        return temas;
    }

    // Obtener un tema por su ID
    public TemaDto getTema(Long idTema) throws Exception {
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new Exception("Tema no encontrado"));

        return new TemaDto(
                tema.getIdTema(),
                tema.getTitulo(),
                tema.getOrden(),
                tema.getDescripcion(),
                tema.getRecursoMultimedia().getUrl(),
                tema.getEstado(),
                tema.getLeccion().getIdLeccion()
        );
    }

    // Obtener temas por curso
    public Page<TemaDto> getTemasByCurso(Long cursoId, PaginadoDto paginadoDto) {

        Sort sort = paginadoDto.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(paginadoDto.getSortBy()).ascending() : Sort.by(paginadoDto.getSortBy()).descending();
        Pageable page = PageRequest.of(paginadoDto.getPage(), paginadoDto.getSize(), sort);

        Page<TemaDto> temas = temaRepository.findAllByLeccionIdLeccion(cursoId, page);

        return temas;
    }

    // Crear un tema
    public void createTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Subir archivo a MinIO y guardar detalles en la base de datos
        logger.info("Nombre del archivo: "+file.getOriginalFilename());
        logger.info("Tipo de archivo: "+file.getContentType());
        S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
        Optional<S3Object> s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId());
        S3Object s3Object1 = new S3Object();

        if (s3Object.isEmpty()) {
            throw new RuntimeException("Error al subir el archivo");
        } else if (s3Object.isPresent()) {
            logger.info("Archivo subido exitosamente");
            s3Object1 = s3Object.get();
            logger.info("ID del archivo: "+s3Object1.getS3ObjectId());
            logger.info("URL del archivo: "+s3Object1.getUrl());
            logger.info("Nombre del archivo: "+s3Object1.getBucket());
            logger.info("Tipo de archivo: "+s3Object1.getContentType());
            logger.info("Tamaño del archivo: "+s3Object1.getFilename());
        }


        Leccion leccion = leccionRepository.findById(temaDto.getLeccionId())
                .orElseThrow(() -> new RuntimeException("Leccion no encontrada"));

        Tema tema = new Tema();
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setRecursoMultimedia(s3Object1);
        tema.setLeccion(leccion);
        tema.setEstado(true);
        temaRepository.save(tema);
    }

    // Actualizar un tema
    public void updateTema(TemaDto temaDto, MultipartFile file) throws Exception {
        // Subir archivo a MinIO y guardar detalles en la base de datos
        S3ObjectDto s3ObjectDto = recursoMultimediaService.uploadFile(file);
        S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId()).get();

        Tema tema = temaRepository.findById(temaDto.getIdTema()).get();
        tema.setTitulo(temaDto.getTitulo());
        tema.setOrden(temaDto.getOrden());
        tema.setDescripcion(temaDto.getDescripcion());
        tema.setRecursoMultimedia(s3Object);
        temaRepository.save(tema);
    }

    // Eliminar un tema
    public void deleteTema(Long idTema) {
        // borrado lógico
        Tema tema = temaRepository.findById(idTema)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
        tema.setEstado(false);
        temaRepository.save(tema);
    }
}
