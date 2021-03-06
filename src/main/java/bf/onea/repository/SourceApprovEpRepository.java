package bf.onea.repository;

import bf.onea.domain.SourceApprovEp;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SourceApprovEp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SourceApprovEpRepository extends JpaRepository<SourceApprovEp, Long> {
}
