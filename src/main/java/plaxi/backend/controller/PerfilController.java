package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.ActualizarPerfilDto;
import plaxi.backend.dto.PerfilDto;
import plaxi.backend.service.PerfilService;

import java.util.List;

@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

    @Autowired
    private PerfilService perfilService;

    // Mostrar todos los perfiles
    @GetMapping("/all")
    public ResponseEntity<List<PerfilDto>> getAllProfiles() {
        logger.info("Solicitud para obtener todos los perfiles de usuarios");
        try {
            List<PerfilDto> usuarios = perfilService.getAllProfiles();
            logger.info("Perfiles de usuarios obtenidos exitosamente, total: {}", usuarios.size());
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            logger.error("Error al obtener los perfiles de usuarios: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener perfil por ID de usuario
    @GetMapping("/{idUsuario}")
    public ResponseEntity<PerfilDto> getProfile(@PathVariable Long idUsuario) {
        logger.info("Solicitud para obtener el perfil del usuario con ID: {}", idUsuario);
        try {
            PerfilDto perfil = perfilService.getProfile(idUsuario);
            logger.info("Perfil obtenido exitosamente para el usuario con ID: {}", idUsuario);
            return ResponseEntity.ok(perfil);
        } catch (Exception e) {
            logger.error("Error al obtener el perfil del usuario con ID: {} - {}", idUsuario, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Actualizar perfil (incluyendo la opción de subir una imagen)
    @PutMapping("/{idUsuario}")
    public ResponseEntity<ActualizarPerfilDto> updateProfile(@PathVariable Long idUsuario,
                                                             @ModelAttribute ActualizarPerfilDto perfilDto) {
        logger.info("Solicitud para actualizar el perfil del usuario con ID: {}", idUsuario);
        try {
            ActualizarPerfilDto perfilActualizado = perfilService.updateProfile(idUsuario, perfilDto);
            logger.info("Perfil actualizado exitosamente para el usuario con ID: {}", idUsuario);
            return ResponseEntity.ok(perfilActualizado);
        } catch (Exception e) {
            logger.error("Error al actualizar el perfil del usuario con ID: {} - {}", idUsuario, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Borrado lógico del perfil
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long idUsuario) {
        logger.info("Solicitud para borrar lógicamente el perfil del usuario con ID: {}", idUsuario);
        try {
            perfilService.deleteProfile(idUsuario);
            logger.info("Perfil borrado lógicamente exitosamente para el usuario con ID: {}", idUsuario);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            logger.error("Error al borrar lógicamente el perfil del usuario con ID: {} - {}", idUsuario, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
