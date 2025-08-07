package plaxi.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.RegisterRequestDto;
import plaxi.backend.dto.ResponseDto;
import plaxi.backend.dto.UsuarioDto;
import plaxi.backend.entity.Usuario;
import plaxi.backend.service.AuthService;
import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Usuario>> registerUser(@RequestBody RegisterRequestDto registerRequest) {
        logger.info("Solicitud de registro recibida para el usuario: {}", registerRequest.getUsuario().getUsername());

        try {
            Usuario usuarioCreado = authService.registerUser(registerRequest.getUsuario(), registerRequest.getPersona());
            logger.info("Registro exitoso para el usuario: {}", registerRequest.getUsuario().getUsername());
            logger.info(null, usuarioCreado);
            ResponseDto<Usuario> response = new ResponseDto<>("Usuario registrado exitosamente", null, true);
            return ResponseEntity.ok(response);

        } catch (AuthException e) {
            logger.error("Error en el registro del usuario: {} - {}", registerRequest.getUsuario().getUsername(), e.getMessage());
            ResponseDto<Usuario> response = new ResponseDto<>(e.getMessage(), null, false);
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            logger.error("Error inesperado en el registro: {}", e.getMessage());
            ResponseDto<Usuario> response = new ResponseDto<>("Error inesperado", null, false);
            return ResponseEntity.status(500).body(response);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto<UsuarioDto>> loginUser(@RequestBody UsuarioDto usuarioDto) {
        logger.info("Solicitud de inicio de sesión recibida para: {}", usuarioDto.getUsername());

        try {
            Usuario usuario = authService.loginUser(usuarioDto.getUsername(), usuarioDto.getPassword());
            String successMessage = "Inicio de sesión exitoso para el usuario: " + usuario.getUsername();
            logger.info("Inicio de sesión exitoso para el usuario: {}", usuario.getUsername());

            UsuarioDto usuarioResponse = new UsuarioDto(
                    usuario.getIdUsuario(),
                    usuario.getUsername(),
                    null,
                    usuario.getGmail(),
                    usuario.isStatus(),
                    usuario.getPersonaId().getIdPersona(),
                    usuario.getRolId().getIdRol()
            );

            ResponseDto<UsuarioDto> response = new ResponseDto<>(successMessage, usuarioResponse, true);
            return ResponseEntity.ok(response);

        } catch (AuthException e) {
            logger.error("Error en el inicio de sesión para: {} - {}", usuarioDto.getUsername(), e.getMessage());
            ResponseDto<UsuarioDto> response = new ResponseDto<>(e.getMessage(), null, false);
            return ResponseEntity.status(401).body(response);
        } catch (Exception e) {
            logger.error("Error inesperado en el inicio de sesión: {}", e.getMessage());
            ResponseDto<UsuarioDto> response = new ResponseDto<>("Error inesperado", null, false);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto<Void>> resetPassword(@RequestParam String email) {
        try {
            authService.resetPassword(email);
            return ResponseEntity.ok(new ResponseDto<>("La nueva contraseña ha sido enviada a tu correo", null, true));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseDto<>(e.getMessage(), null, false));
        }
    }
}

