package bf.onea.repository;

import bf.onea.domain.EtatProgram;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtatProgram entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatProgramRepository extends JpaRepository<EtatProgram, Long> {
}
