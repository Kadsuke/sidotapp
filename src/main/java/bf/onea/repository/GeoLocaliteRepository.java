package bf.onea.repository;

import bf.onea.domain.GeoLocalite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoLocalite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoLocaliteRepository extends JpaRepository<GeoLocalite, Long> {
}
