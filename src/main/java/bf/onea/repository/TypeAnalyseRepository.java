package bf.onea.repository;

import bf.onea.domain.TypeAnalyse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeAnalyse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAnalyseRepository extends JpaRepository<TypeAnalyse, Long> {
}
