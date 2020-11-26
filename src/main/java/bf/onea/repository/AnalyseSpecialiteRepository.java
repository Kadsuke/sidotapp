package bf.onea.repository;

import bf.onea.domain.AnalyseSpecialite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AnalyseSpecialite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnalyseSpecialiteRepository extends JpaRepository<AnalyseSpecialite, Long> {
}
