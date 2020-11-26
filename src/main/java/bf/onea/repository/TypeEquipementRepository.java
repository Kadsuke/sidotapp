package bf.onea.repository;

import bf.onea.domain.TypeEquipement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeEquipement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEquipementRepository extends JpaRepository<TypeEquipement, Long> {
}
