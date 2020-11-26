package bf.onea.repository;

import bf.onea.domain.GeuPrevisionSTBV;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuPrevisionSTBV entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuPrevisionSTBVRepository extends JpaRepository<GeuPrevisionSTBV, Long> {
}
