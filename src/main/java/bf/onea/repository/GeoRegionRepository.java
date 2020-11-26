package bf.onea.repository;

import bf.onea.domain.GeoRegion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoRegion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoRegionRepository extends JpaRepository<GeoRegion, Long> {
}
