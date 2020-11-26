package bf.onea.repository;

import bf.onea.domain.GeoCommune;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoCommune entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoCommuneRepository extends JpaRepository<GeoCommune, Long> {
}
