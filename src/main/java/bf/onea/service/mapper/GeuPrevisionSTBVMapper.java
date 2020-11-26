package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuPrevisionSTBVDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuPrevisionSTBV} and its DTO {@link GeuPrevisionSTBVDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeuSTBVMapper.class})
public interface GeuPrevisionSTBVMapper extends EntityMapper<GeuPrevisionSTBVDTO, GeuPrevisionSTBV> {

    @Mapping(source = "geustbv.id", target = "geustbvId")
    GeuPrevisionSTBVDTO toDto(GeuPrevisionSTBV geuPrevisionSTBV);

    @Mapping(source = "geustbvId", target = "geustbv")
    GeuPrevisionSTBV toEntity(GeuPrevisionSTBVDTO geuPrevisionSTBVDTO);

    default GeuPrevisionSTBV fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuPrevisionSTBV geuPrevisionSTBV = new GeuPrevisionSTBV();
        geuPrevisionSTBV.setId(id);
        return geuPrevisionSTBV;
    }
}
