package davidmateus.com.alugacasa.repository;

import davidmateus.com.alugacasa.model.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {
}
