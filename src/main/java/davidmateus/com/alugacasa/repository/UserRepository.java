package davidmateus.com.alugacasa.repository;

import davidmateus.com.alugacasa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    // Usando JPQL(Java persistence query Language) User ai na query faz referencia a entidade.JPQL opera com base em entidades
    // @Query("SELECT u.username, u.id FROM User u")
    // List<String> findAllNames();
    Optional<User> findByUserName(String username);
}
