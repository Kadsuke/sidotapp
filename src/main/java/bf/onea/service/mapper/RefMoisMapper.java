package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.RefMoisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefMois} and its DTO {@link RefMoisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefMoisMapper extends EntityMapper<RefMoisDTO, RefMois> {



    default RefMois fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefMois refMois = new RefMois();
        refMois.setId(id);
        return refMois;
    }
}
