package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuSTEPDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuSTEP} and its DTO {@link GeuSTEPDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeuSTEPMapper extends EntityMapper<GeuSTEPDTO, GeuSTEP> {


    @Mapping(target = "geuPrevisionSTEPS", ignore = true)
    @Mapping(target = "removeGeuPrevisionSTEP", ignore = true)
    GeuSTEP toEntity(GeuSTEPDTO geuSTEPDTO);

    default GeuSTEP fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuSTEP geuSTEP = new GeuSTEP();
        geuSTEP.setId(id);
        return geuSTEP;
    }
}
