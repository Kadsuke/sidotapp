package bf.onea.repository;

import bf.onea.domain.ProduitChimique;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProduitChimique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitChimiqueRepository extends JpaRepository<ProduitChimique, Long> {
}
