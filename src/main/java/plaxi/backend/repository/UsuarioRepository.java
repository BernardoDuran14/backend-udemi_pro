package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plaxi.backend.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByGmail(String gmail);
    Optional<Usuario> findByUsernameOrGmail(String username, String gmail);

    List<Usuario> findAllByStatusTrue();
}
