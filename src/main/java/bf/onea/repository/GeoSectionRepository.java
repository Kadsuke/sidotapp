package bf.onea.repository;

import bf.onea.domain.GeoSection;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoSection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoSectionRepository extends JpaRepository<GeoSection, Long> {
}
