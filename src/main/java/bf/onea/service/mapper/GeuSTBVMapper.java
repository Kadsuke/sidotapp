package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuSTBVDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuSTBV} and its DTO {@link GeuSTBVDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeuSTBVMapper extends EntityMapper<GeuSTBVDTO, GeuSTBV> {


    @Mapping(target = "geuRealisationSTBVS", ignore = true)
    @Mapping(target = "removeGeuRealisationSTBV", ignore = true)
    @Mapping(target = "geuPrevisionSTBVS", ignore = true)
    @Mapping(target = "removeGeuPrevisionSTBV", ignore = true)
    GeuSTBV toEntity(GeuSTBVDTO geuSTBVDTO);

    default GeuSTBV fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuSTBV geuSTBV = new GeuSTBV();
        geuSTBV.setId(id);
        return geuSTBV;
    }
}
