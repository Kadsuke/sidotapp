package bf.onea.repository;

import bf.onea.domain.EtatEquipement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatEquipement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatEquipementRepository extends JpaRepository<EtatEquipement, Long> {
}
