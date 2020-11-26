package bf.onea.repository;

import bf.onea.domain.ModeEvacuationEauUsee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModeEvacuationEauUsee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModeEvacuationEauUseeRepository extends JpaRepository<ModeEvacuationEauUsee, Long> {
}
