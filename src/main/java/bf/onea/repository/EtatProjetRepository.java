package bf.onea.repository;

import bf.onea.domain.EtatProjet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatProjet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatProjetRepository extends JpaRepository<EtatProjet, Long> {
}
