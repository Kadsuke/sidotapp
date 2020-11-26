package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.SiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Site} and its DTO {@link SiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CentreMapper.class})
public interface SiteMapper extends EntityMapper<SiteDTO, Site> {

    @Mapping(source = "centre.id", target = "centreId")
    SiteDTO toDto(Site site);

    @Mapping(target = "agents", ignore = true)
    @Mapping(target = "removeAgent", ignore = true)
    @Mapping(source = "centreId", target = "centre")
    Site toEntity(SiteDTO siteDTO);

    default Site fromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
