package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.CaracteristiqueOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaracteristiqueOuvrage} and its DTO {@link CaracteristiqueOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeOuvrageMapper.class})
public interface CaracteristiqueOuvrageMapper extends EntityMapper<CaracteristiqueOuvrageDTO, CaracteristiqueOuvrage> {

    @Mapping(source = "typeouvrage.id", target = "typeouvrageId")
    CaracteristiqueOuvrageDTO toDto(CaracteristiqueOuvrage caracteristiqueOuvrage);

    @Mapping(target = "typeOuvrages", ignore = true)
    @Mapping(target = "removeTypeOuvrage", ignore = true)
    @Mapping(source = "typeouvrageId", target = "typeouvrage")
    CaracteristiqueOuvrage toEntity(CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO);

    default CaracteristiqueOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaracteristiqueOuvrage caracteristiqueOuvrage = new CaracteristiqueOuvrage();
        caracteristiqueOuvrage.setId(id);
        return caracteristiqueOuvrage;
    }
}
