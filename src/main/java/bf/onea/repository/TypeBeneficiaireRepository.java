package bf.onea.repository;

import bf.onea.domain.TypeBeneficiaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeBeneficiaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeBeneficiaireRepository extends JpaRepository<TypeBeneficiaire, Long> {
}
