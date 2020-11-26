package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuPSADTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuPSA} and its DTO {@link GeuPSADTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoCommuneMapper.class})
public interface GeuPSAMapper extends EntityMapper<GeuPSADTO, GeuPSA> {

    @Mapping(source = "geocommune.id", target = "geocommuneId")
    GeuPSADTO toDto(GeuPSA geuPSA);

    @Mapping(source = "geocommuneId", target = "geocommune")
    GeuPSA toEntity(GeuPSADTO geuPSADTO);

    default GeuPSA fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuPSA geuPSA = new GeuPSA();
        geuPSA.setId(id);
        return geuPSA;
    }
}
