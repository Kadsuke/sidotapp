package bf.onea.repository;

import bf.onea.domain.DirectionRegionale;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DirectionRegionale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectionRegionaleRepository extends JpaRepository<DirectionRegionale, Long> {
}
