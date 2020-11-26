package bf.onea.repository;

import bf.onea.domain.TypeOuvrage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeOuvrage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeOuvrageRepository extends JpaRepository<TypeOuvrage, Long> {
}
