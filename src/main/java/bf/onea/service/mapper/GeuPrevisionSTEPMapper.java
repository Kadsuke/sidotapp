package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuPrevisionSTEPDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuPrevisionSTEP} and its DTO {@link GeuPrevisionSTEPDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeuSTEPMapper.class})
public interface GeuPrevisionSTEPMapper extends EntityMapper<GeuPrevisionSTEPDTO, GeuPrevisionSTEP> {

    @Mapping(source = "geustep.id", target = "geustepId")
    GeuPrevisionSTEPDTO toDto(GeuPrevisionSTEP geuPrevisionSTEP);

    @Mapping(source = "geustepId", target = "geustep")
    GeuPrevisionSTEP toEntity(GeuPrevisionSTEPDTO geuPrevisionSTEPDTO);

    default GeuPrevisionSTEP fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuPrevisionSTEP geuPrevisionSTEP = new GeuPrevisionSTEP();
        geuPrevisionSTEP.setId(id);
        return geuPrevisionSTEP;
    }
}
