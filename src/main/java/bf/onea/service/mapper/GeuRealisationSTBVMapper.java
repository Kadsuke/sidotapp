package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuRealisationSTBVDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuRealisationSTBV} and its DTO {@link GeuRealisationSTBVDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeuSTBVMapper.class})
public interface GeuRealisationSTBVMapper extends EntityMapper<GeuRealisationSTBVDTO, GeuRealisationSTBV> {

    @Mapping(source = "geustbv.id", target = "geustbvId")
    GeuRealisationSTBVDTO toDto(GeuRealisationSTBV geuRealisationSTBV);

    @Mapping(source = "geustbvId", target = "geustbv")
    GeuRealisationSTBV toEntity(GeuRealisationSTBVDTO geuRealisationSTBVDTO);

    default GeuRealisationSTBV fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuRealisationSTBV geuRealisationSTBV = new GeuRealisationSTBV();
        geuRealisationSTBV.setId(id);
        return geuRealisationSTBV;
    }
}
