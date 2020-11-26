package bf.onea.repository;

import bf.onea.domain.EtatOuvrage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatOuvrage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatOuvrageRepository extends JpaRepository<EtatOuvrage, Long> {
}
