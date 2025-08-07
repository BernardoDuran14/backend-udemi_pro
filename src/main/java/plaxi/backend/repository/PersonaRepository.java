package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    
}
