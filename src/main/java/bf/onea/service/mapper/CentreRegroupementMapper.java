package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.CentreRegroupementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CentreRegroupement} and its DTO {@link CentreRegroupementDTO}.
 */
@Mapper(componentModel = "spring", uses = {DirectionRegionaleMapper.class})
public interface CentreRegroupementMapper extends EntityMapper<CentreRegroupementDTO, CentreRegroupement> {

    @Mapping(source = "directionregionale.id", target = "directionregionaleId")
    CentreRegroupementDTO toDto(CentreRegroupement centreRegroupement);

    @Mapping(target = "centres", ignore = true)
    @Mapping(target = "removeCentre", ignore = true)
    @Mapping(source = "directionregionaleId", target = "directionregionale")
    CentreRegroupement toEntity(CentreRegroupementDTO centreRegroupementDTO);

    default CentreRegroupement fromId(Long id) {
        if (id == null) {
            return null;
        }
        CentreRegroupement centreRegroupement = new CentreRegroupement();
        centreRegroupement.setId(id);
        return centreRegroupement;
    }
}
