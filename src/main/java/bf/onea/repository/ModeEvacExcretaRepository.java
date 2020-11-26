package bf.onea.repository;

import bf.onea.domain.ModeEvacExcreta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModeEvacExcreta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModeEvacExcretaRepository extends JpaRepository<ModeEvacExcreta, Long> {
}
