package bf.onea.repository;

import bf.onea.domain.GeoParcelle;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoParcelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoParcelleRepository extends JpaRepository<GeoParcelle, Long> {
}
