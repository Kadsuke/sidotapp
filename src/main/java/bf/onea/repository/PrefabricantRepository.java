package bf.onea.repository;

import bf.onea.domain.Prefabricant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Prefabricant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrefabricantRepository extends JpaRepository<Prefabricant, Long> {
}
