package bf.onea.repository;

import bf.onea.domain.CaracteristiqueOuvrage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CaracteristiqueOuvrage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaracteristiqueOuvrageRepository extends JpaRepository<CaracteristiqueOuvrage, Long> {
}
