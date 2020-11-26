package bf.onea.repository;

import bf.onea.domain.TypePlainte;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypePlainte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePlainteRepository extends JpaRepository<TypePlainte, Long> {
}
