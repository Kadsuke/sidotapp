package bf.onea.repository;

import bf.onea.domain.RefSousDomaine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefSousDomaine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefSousDomaineRepository extends JpaRepository<RefSousDomaine, Long> {
}
