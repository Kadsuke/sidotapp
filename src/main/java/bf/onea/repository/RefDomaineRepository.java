package bf.onea.repository;

import bf.onea.domain.RefDomaine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefDomaine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefDomaineRepository extends JpaRepository<RefDomaine, Long> {
}
