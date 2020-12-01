package bf.onea.repository;

import bf.onea.domain.PrevisionAssainissementAu;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisionAssainissementAu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisionAssainissementAuRepository extends JpaRepository<PrevisionAssainissementAu, Long> {
}
