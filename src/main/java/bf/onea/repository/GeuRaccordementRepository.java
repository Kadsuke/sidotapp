package bf.onea.repository;

import bf.onea.domain.GeuRaccordement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuRaccordement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuRaccordementRepository extends JpaRepository<GeuRaccordement, Long> {
}
