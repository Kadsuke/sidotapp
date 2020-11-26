package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.MaconDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Macon} and its DTO {@link MaconDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaconMapper extends EntityMapper<MaconDTO, Macon> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    Macon toEntity(MaconDTO maconDTO);

    default Macon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Macon macon = new Macon();
        macon.setId(id);
        return macon;
    }
}
