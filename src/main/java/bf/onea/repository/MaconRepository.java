package bf.onea.repository;

import bf.onea.domain.Macon;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Macon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaconRepository extends JpaRepository<Macon, Long> {
}
