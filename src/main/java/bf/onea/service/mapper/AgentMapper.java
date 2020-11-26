package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.AgentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agent} and its DTO {@link AgentDTO}.
 */
@Mapper(componentModel = "spring", uses = {SiteMapper.class})
public interface AgentMapper extends EntityMapper<AgentDTO, Agent> {

    @Mapping(source = "site.id", target = "siteId")
    AgentDTO toDto(Agent agent);

    @Mapping(target = "geuRaccordements", ignore = true)
    @Mapping(target = "removeGeuRaccordement", ignore = true)
    @Mapping(source = "siteId", target = "site")
    Agent toEntity(AgentDTO agentDTO);

    default Agent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agent agent = new Agent();
        agent.setId(id);
        return agent;
    }
}
