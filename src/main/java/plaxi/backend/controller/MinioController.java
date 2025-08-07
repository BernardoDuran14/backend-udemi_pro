package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plaxi.backend.dto.S3ObjectDto;
import plaxi.backend.service.MinioService;


import java.util.List;

@RestController
@RequestMapping("/api/files")
public class MinioController {

    @Autowired
    private MinioService minioService;

    // Endpoint para subir un archivo y guardar sus detalles en la base de datos
    @PostMapping("/upload")
    public ResponseEntity<S3ObjectDto> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Subir archivo y guardar detalles en la base de datos
            S3ObjectDto s3ObjectDto = minioService.uploadFile(file);
            return ResponseEntity.ok(s3ObjectDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);  // Devolver un error en caso de excepción
        }
    }

    // Endpoint para listar los archivos subidos
    @GetMapping("/list")
    public ResponseEntity<List<S3ObjectDto>> listFiles() {
        try {
            List<S3ObjectDto> fileList = minioService.listUploadedFiles();
            return ResponseEntity.ok(fileList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
