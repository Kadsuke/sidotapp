package bf.onea.repository;

import bf.onea.domain.TypeHabitation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeHabitation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeHabitationRepository extends JpaRepository<TypeHabitation, Long> {
}
