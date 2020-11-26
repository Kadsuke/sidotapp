package bf.onea.repository;

import bf.onea.domain.GeuRealisationSTBV;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuRealisationSTBV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuRealisationSTBVRepository extends JpaRepository<GeuRealisationSTBV, Long> {
}
