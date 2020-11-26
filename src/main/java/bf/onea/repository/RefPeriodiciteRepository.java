package bf.onea.repository;

import bf.onea.domain.RefPeriodicite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefPeriodicite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefPeriodiciteRepository extends JpaRepository<RefPeriodicite, Long> {
}
