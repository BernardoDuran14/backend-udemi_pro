package plaxi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plaxi.backend.dto.CategoriaDto;
import plaxi.backend.entity.Categoria;
import plaxi.backend.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva categoría (POST)
    public CategoriaDto createCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria(categoriaDto.getNombre(), categoriaDto.getDecripcion());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDto(categoria.getIdCategoria(), categoria.getNombre(), categoria.getDecripcion());
    }

    // Actualizar una categoría existente (PUT)
    public CategoriaDto updateCategoria(Long id, CategoriaDto categoriaDto) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(categoriaDto.getNombre());
            categoria.setDecripcion(categoriaDto.getDecripcion());
            categoriaRepository.save(categoria);
            return new CategoriaDto(categoria.getIdCategoria(), categoria.getNombre(), categoria.getDecripcion());
        }
        return null; // O lanzar una excepción personalizada como CategoriaNotFoundException
    }

    // Obtener todas las categorías (GET)
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por ID (GET)
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    // Eliminar una categoría por ID (DELETE)
    public boolean deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
