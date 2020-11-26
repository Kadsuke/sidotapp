package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuRealisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuRealisation} and its DTO {@link GeuRealisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeuAaOuvrageMapper.class})
public interface GeuRealisationMapper extends EntityMapper<GeuRealisationDTO, GeuRealisation> {

    @Mapping(source = "geuaaouvrage.id", target = "geuaaouvrageId")
    GeuRealisationDTO toDto(GeuRealisation geuRealisation);

    @Mapping(source = "geuaaouvrageId", target = "geuaaouvrage")
    GeuRealisation toEntity(GeuRealisationDTO geuRealisationDTO);

    default GeuRealisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuRealisation geuRealisation = new GeuRealisation();
        geuRealisation.setId(id);
        return geuRealisation;
    }
}
