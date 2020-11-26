package bf.onea.repository;

import bf.onea.domain.RefMois;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RefMois entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefMoisRepository extends JpaRepository<RefMois, Long> {
}
