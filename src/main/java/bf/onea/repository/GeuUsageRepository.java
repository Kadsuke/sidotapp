package bf.onea.repository;

import bf.onea.domain.GeuUsage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuUsage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuUsageRepository extends JpaRepository<GeuUsage, Long> {
}
