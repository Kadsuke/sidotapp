package bf.onea.repository;

import bf.onea.domain.AnalyseParametre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AnalyseParametre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnalyseParametreRepository extends JpaRepository<AnalyseParametre, Long> {
}
