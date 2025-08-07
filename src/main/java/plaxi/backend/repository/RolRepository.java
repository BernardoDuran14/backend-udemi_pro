package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
