package bf.onea.repository;

import bf.onea.domain.TypeIntervention;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeIntervention entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeInterventionRepository extends JpaRepository<TypeIntervention, Long> {
}
