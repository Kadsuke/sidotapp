package bf.onea.repository;

import bf.onea.domain.GeuSTBV;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuSTBV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuSTBVRepository extends JpaRepository<GeuSTBV, Long> {
}
