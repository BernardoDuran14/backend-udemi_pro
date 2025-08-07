package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plaxi.backend.entity.Curso;
import plaxi.backend.entity.Inscripcion;
import plaxi.backend.entity.Usuario;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {


    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Inscripcion i WHERE i.usuario = :usuario AND i.curso = :curso")
    Boolean findByUsuarioAndCurso(Usuario usuario, Curso curso);
}