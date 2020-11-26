package bf.onea.repository;

import bf.onea.domain.RefAnnee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefAnnee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefAnneeRepository extends JpaRepository<RefAnnee, Long> {
}
