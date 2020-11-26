package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.RefAnneeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefAnnee} and its DTO {@link RefAnneeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefAnneeMapper extends EntityMapper<RefAnneeDTO, RefAnnee> {



    default RefAnnee fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefAnnee refAnnee = new RefAnnee();
        refAnnee.setId(id);
        return refAnnee;
    }
}
