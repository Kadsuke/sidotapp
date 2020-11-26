package bf.onea.repository;

import bf.onea.domain.GeuRealisation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuRealisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuRealisationRepository extends JpaRepository<GeuRealisation, Long> {
}
