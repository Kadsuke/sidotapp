package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypePlainteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypePlainte} and its DTO {@link TypePlainteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypePlainteMapper extends EntityMapper<TypePlainteDTO, TypePlainte> {



    default TypePlainte fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypePlainte typePlainte = new TypePlainte();
        typePlainte.setId(id);
        return typePlainte;
    }
}
