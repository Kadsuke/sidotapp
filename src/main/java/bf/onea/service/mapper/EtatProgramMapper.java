package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.EtatProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatProgram} and its DTO {@link EtatProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatProgramMapper extends EntityMapper<EtatProgramDTO, EtatProgram> {



    default EtatProgram fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatProgram etatProgram = new EtatProgram();
        etatProgram.setId(id);
        return etatProgram;
    }
}
