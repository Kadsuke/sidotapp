package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.RefPeriodiciteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefPeriodicite} and its DTO {@link RefPeriodiciteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefPeriodiciteMapper extends EntityMapper<RefPeriodiciteDTO, RefPeriodicite> {



    default RefPeriodicite fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefPeriodicite refPeriodicite = new RefPeriodicite();
        refPeriodicite.setId(id);
        return refPeriodicite;
    }
}
