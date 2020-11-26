package bf.onea.repository;

import bf.onea.domain.GeuPrevisionSTEP;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuPrevisionSTEP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuPrevisionSTEPRepository extends JpaRepository<GeuPrevisionSTEP, Long> {
}
