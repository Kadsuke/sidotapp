package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.CentreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Centre} and its DTO {@link CentreDTO}.
 */
@Mapper(componentModel = "spring", uses = {CentreRegroupementMapper.class})
public interface CentreMapper extends EntityMapper<CentreDTO, Centre> {

    @Mapping(source = "centreregroupement.id", target = "centreregroupementId")
    CentreDTO toDto(Centre centre);

    @Mapping(target = "sites", ignore = true)
    @Mapping(target = "removeSite", ignore = true)
    @Mapping(source = "centreregroupementId", target = "centreregroupement")
    Centre toEntity(CentreDTO centreDTO);

    default Centre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Centre centre = new Centre();
        centre.setId(id);
        return centre;
    }
}
