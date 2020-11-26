package bf.onea.repository;

import bf.onea.domain.GeuPSA;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuPSA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuPSARepository extends JpaRepository<GeuPSA, Long> {
}
