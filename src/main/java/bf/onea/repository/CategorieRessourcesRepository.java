package bf.onea.repository;

import bf.onea.domain.CategorieRessources;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategorieRessources entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieRessourcesRepository extends JpaRepository<CategorieRessources, Long> {
}
