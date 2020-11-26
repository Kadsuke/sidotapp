package bf.onea.repository;

import bf.onea.domain.GeoProvince;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoProvince entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoProvinceRepository extends JpaRepository<GeoProvince, Long> {
}
