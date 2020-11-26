package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.DirectionRegionaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DirectionRegionale} and its DTO {@link DirectionRegionaleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DirectionRegionaleMapper extends EntityMapper<DirectionRegionaleDTO, DirectionRegionale> {


    @Mapping(target = "centreRegroupements", ignore = true)
    @Mapping(target = "removeCentreRegroupement", ignore = true)
    DirectionRegionale toEntity(DirectionRegionaleDTO directionRegionaleDTO);

    default DirectionRegionale fromId(Long id) {
        if (id == null) {
            return null;
        }
        DirectionRegionale directionRegionale = new DirectionRegionale();
        directionRegionale.setId(id);
        return directionRegionale;
    }
}
