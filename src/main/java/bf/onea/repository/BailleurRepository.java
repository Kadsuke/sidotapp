package bf.onea.repository;

import bf.onea.domain.Bailleur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bailleur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BailleurRepository extends JpaRepository<Bailleur, Long> {
}
