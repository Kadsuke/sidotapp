package bf.onea.repository;

import bf.onea.domain.CentreRegroupement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CentreRegroupement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CentreRegroupementRepository extends JpaRepository<CentreRegroupement, Long> {
}
