package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plaxi.backend.dto.ActualizarPerfilDto;
import plaxi.backend.dto.PerfilDto;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.entity.Persona;
import plaxi.backend.entity.S3Object;
import plaxi.backend.entity.Usuario;
import plaxi.backend.repository.PersonaRepository;
import plaxi.backend.repository.S3ObjectRepository;
import plaxi.backend.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private S3ObjectRepository s3ObjectRepository;

    public List<PerfilDto> getAllProfiles() {
        List<Usuario> usuarios = usuarioRepository.findAllByStatusTrue();
        return usuarios.stream().map(usuario -> {
            Persona persona = usuario.getPersonaId();
            String imagenUrl = usuario.getImagenPerfil() != null ? usuario.getImagenPerfil().getUrl() : null;  // Si existe imagen, obtener URL
            return new PerfilDto(
                    usuario.getIdUsuario(),
                    usuario.getUsername(),
                    usuario.getGmail(),
                    usuario.isStatus(),
                    persona.getNombre(),
                    persona.getPrimerApellido(),
                    persona.getSegundoApellido(),
                    persona.getTelefono(),
                    persona.getCi(),
                    imagenUrl  // Añadir la URL de la imagen de perfil
            );
        }).collect(Collectors.toList());
    }


    public PerfilDto getProfile(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!usuario.isStatus()) {
            throw new Exception("El usuario ha sido desactivado");
        }

        Persona persona = usuario.getPersonaId();
        String imagenUrl = usuario.getImagenPerfil() != null ? usuario.getImagenPerfil().getUrl() : null;  // Obtener la URL de la imagen si existe

        return new PerfilDto(
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getGmail(),
                usuario.isStatus(),
                persona.getNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getTelefono(),
                persona.getCi(),
                imagenUrl  // Añadir la URL de la imagen de perfil
        );
    }


    public ActualizarPerfilDto updateProfile(Long idUsuario, ActualizarPerfilDto perfilDto) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!usuario.isStatus()) {
            throw new Exception("El usuario ha sido desactivado y no se puede actualizar.");
        }

        // Si el DTO incluye un archivo, subimos la imagen a MinIO y guardamos el enlace
        if (perfilDto.getFile() != null && !perfilDto.getFile().isEmpty()) {
            S3ObjectDto s3ObjectDto = minioService.uploadFile(perfilDto.getFile());
            S3Object s3Object = s3ObjectRepository.findById(s3ObjectDto.getS3ObjectId())
                    .orElseThrow(() -> new Exception("Imagen de perfil no encontrada"));
            usuario.setImagenPerfil(s3Object);  // Asociamos la imagen al perfil del usuario
        }

        // Actualizamos los datos del usuario
        usuario.setUsername(perfilDto.getUsername());
        usuario.setGmail(perfilDto.getGmail());
        usuarioRepository.save(usuario);

        // Actualizamos los datos de la persona asociada al usuario
        Persona persona = usuario.getPersonaId();
        persona.setNombre(perfilDto.getNombre());
        persona.setPrimerApellido(perfilDto.getPrimerApellido());
        persona.setSegundoApellido(perfilDto.getSegundoApellido());
        persona.setTelefono(perfilDto.getTelefono());
        persona.setCi(perfilDto.getCi());
        personaRepository.save(persona);

        // Retornamos el DTO actualizado
        return perfilDto;
    }

    // Borrado lógico del perfil (solo cambia el estado del usuario a falso)
    public void deleteProfile(Long idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        usuario.setStatus(false);  // Borrado lógico
        usuarioRepository.save(usuario);
    }
}
