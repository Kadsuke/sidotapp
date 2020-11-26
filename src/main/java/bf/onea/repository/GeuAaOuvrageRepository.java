package bf.onea.repository;

import bf.onea.domain.GeuAaOuvrage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeuAaOuvrage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeuAaOuvrageRepository extends JpaRepository<GeuAaOuvrage, Long> {
}
