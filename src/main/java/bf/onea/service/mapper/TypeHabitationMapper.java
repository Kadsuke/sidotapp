package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeHabitationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeHabitation} and its DTO {@link TypeHabitationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeHabitationMapper extends EntityMapper<TypeHabitationDTO, TypeHabitation> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    TypeHabitation toEntity(TypeHabitationDTO typeHabitationDTO);

    default TypeHabitation fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeHabitation typeHabitation = new TypeHabitation();
        typeHabitation.setId(id);
        return typeHabitation;
    }
}
