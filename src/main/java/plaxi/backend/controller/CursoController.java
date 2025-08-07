package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.ActualizarCursoDto;
//import plaxi.backend.dto.ActualizarCursoDto2;
import plaxi.backend.dto.CursoDto;
import plaxi.backend.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping("/all")
    public ResponseEntity<List<CursoDto>> getAllCursos() {
        logger.info("Solicitud para obtener todos los cursos");
        try {
            List<CursoDto> cursos = cursoService.getAllCursos();
            logger.info("Cursos obtenidos exitosamente, total: {}", cursos.size());
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            logger.error("Error al obtener los cursos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener curso por ID
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDto> getCursoById(@PathVariable Long idCurso) {
        logger.info("Solicitud para obtener el curso con ID: {}", idCurso);
        try {
            CursoDto curso = cursoService.getCurso(idCurso);
            logger.info("Curso obtenido exitosamente con ID: {}", idCurso);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            logger.error("Error al obtener el curso con ID: {} - {}", idCurso, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener cursos por usuario creador
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CursoDto>> getCursosByUsuario(@PathVariable Long usuarioId) {
        logger.info("Solicitud para obtener cursos del usuario con ID: {}", usuarioId);
        try {
            List<CursoDto> cursos = cursoService.getCursosByUsuario(usuarioId);
            logger.info("Cursos obtenidos exitosamente para el usuario con ID: {}", usuarioId);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            logger.error("Error al obtener cursos del usuario con ID: {} - {}", usuarioId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Crear un nuevo curso
    @PostMapping("/create")
    public ResponseEntity<ActualizarCursoDto> createCurso(@ModelAttribute ActualizarCursoDto cursoDto) {
        logger.info("Solicitud para crear un nuevo curso: {}", cursoDto.getNombre());
        try {
            ActualizarCursoDto nuevoCurso = cursoService.createCurso(cursoDto);
            logger.info("Curso creado exitosamente con ID: {}", nuevoCurso.getIdCurso());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
        }catch (Exception e) {
            logger.error("Error al crear el curso: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // Actualizar un curso por ID
    /*@PutMapping("/update/{idCurso}")
    public ResponseEntity<ActualizarCursoDto2> updateCurso(@PathVariable String idCurso, @RequestBody ActualizarCursoDto2 cursoDto) throws Exception {
        logger.info("Solicitud para actualizar el curso con ID: {}", idCurso);

        Long id = Long.parseLong(idCurso);
        ActualizarCursoDto2 cursoActualizado = cursoService.updateCurso(id, cursoDto);
        logger.info("Curso actualizado exitosamente con ID: {}", idCurso);
        return ResponseEntity.ok(cursoActualizado);
    }*/

    // Borrado lógico del curso
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long idCurso) {
        logger.info("Solicitud para borrar lógicamente el curso con ID: {}", idCurso);
        try {
            cursoService.deleteCurso(idCurso);
            logger.info("Curso borrado lógicamente exitosamente con ID: {}", idCurso);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error al borrar lógicamente el curso con ID: {} - {}", idCurso, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
