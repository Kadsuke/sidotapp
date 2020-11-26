package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuRaccordementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuRaccordement} and its DTO {@link GeuRaccordementDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoParcelleMapper.class, AgentMapper.class, TacheronsMapper.class, GeuUsageMapper.class})
public interface GeuRaccordementMapper extends EntityMapper<GeuRaccordementDTO, GeuRaccordement> {

    @Mapping(source = "geoparcelle.id", target = "geoparcelleId")
    @Mapping(source = "agent.id", target = "agentId")
    @Mapping(source = "tacherons.id", target = "tacheronsId")
    @Mapping(source = "geuusage.id", target = "geuusageId")
    GeuRaccordementDTO toDto(GeuRaccordement geuRaccordement);

    @Mapping(source = "geoparcelleId", target = "geoparcelle")
    @Mapping(source = "agentId", target = "agent")
    @Mapping(source = "tacheronsId", target = "tacherons")
    @Mapping(source = "geuusageId", target = "geuusage")
    GeuRaccordement toEntity(GeuRaccordementDTO geuRaccordementDTO);

    default GeuRaccordement fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuRaccordement geuRaccordement = new GeuRaccordement();
        geuRaccordement.setId(id);
        return geuRaccordement;
    }
}
