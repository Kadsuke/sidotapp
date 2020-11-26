package bf.onea.repository;

import bf.onea.domain.NatureOuvrage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NatureOuvrage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureOuvrageRepository extends JpaRepository<NatureOuvrage, Long> {
}
