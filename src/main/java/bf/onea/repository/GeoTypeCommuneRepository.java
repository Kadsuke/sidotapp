package bf.onea.repository;

import bf.onea.domain.GeoTypeCommune;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoTypeCommune entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoTypeCommuneRepository extends JpaRepository<GeoTypeCommune, Long> {
}
