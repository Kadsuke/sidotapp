package bf.onea.repository;

import bf.onea.domain.GeoSecteur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeoSecteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoSecteurRepository extends JpaRepository<GeoSecteur, Long> {
}
