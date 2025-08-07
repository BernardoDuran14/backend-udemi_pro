package plaxi.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plaxi.backend.dto.CategoriaDto;
import plaxi.backend.entity.Categoria;
import plaxi.backend.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Endpoint para crear una nueva categoría (POST)
    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto createdCategoria = categoriaService.createCategoria(categoriaDto);
        return ResponseEntity.ok(createdCategoria);
    }

    // Endpoint para actualizar una categoría existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategoria(
            @PathVariable("id") Long id,
            @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto updatedCategoria = categoriaService.updateCategoria(id, categoriaDto);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategoria);
    }

    // Endpoint para obtener todas las categorías (GET)
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Endpoint para obtener una categoría por su ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para eliminar una categoría por su ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable("id") Long id) {
        boolean deleted = categoriaService.deleteCategoria(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
