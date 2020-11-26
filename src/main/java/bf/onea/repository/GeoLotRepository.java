package bf.onea.repository;

import bf.onea.domain.GeoLot;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoLot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoLotRepository extends JpaRepository<GeoLot, Long> {
}
