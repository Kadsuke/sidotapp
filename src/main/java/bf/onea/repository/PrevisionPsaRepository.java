package bf.onea.repository;

import bf.onea.domain.PrevisionPsa;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisionPsa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisionPsaRepository extends JpaRepository<PrevisionPsa, Long> {
}
