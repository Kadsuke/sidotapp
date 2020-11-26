package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.RefDomaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefDomaine} and its DTO {@link RefDomaineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefDomaineMapper extends EntityMapper<RefDomaineDTO, RefDomaine> {



    default RefDomaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefDomaine refDomaine = new RefDomaine();
        refDomaine.setId(id);
        return refDomaine;
    }
}
