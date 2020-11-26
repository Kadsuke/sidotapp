package bf.onea.repository;

import bf.onea.domain.GeuSTEP;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuSTEP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuSTEPRepository extends JpaRepository<GeuSTEP, Long> {
}
