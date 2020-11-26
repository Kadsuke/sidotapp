package bf.onea.repository;

import bf.onea.domain.Tacherons;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tacherons entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TacheronsRepository extends JpaRepository<Tacherons, Long> {
}
