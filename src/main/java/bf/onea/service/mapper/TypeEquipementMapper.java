package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeEquipementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeEquipement} and its DTO {@link TypeEquipementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeEquipementMapper extends EntityMapper<TypeEquipementDTO, TypeEquipement> {



    default TypeEquipement fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeEquipement typeEquipement = new TypeEquipement();
        typeEquipement.setId(id);
        return typeEquipement;
    }
}
