package bf.onea.repository;

import bf.onea.domain.PrevisionAssainissementCol;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisionAssainissementCol entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisionAssainissementColRepository extends JpaRepository<PrevisionAssainissementCol, Long> {
}
