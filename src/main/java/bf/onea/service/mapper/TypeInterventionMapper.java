package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeInterventionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeIntervention} and its DTO {@link TypeInterventionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeInterventionMapper extends EntityMapper<TypeInterventionDTO, TypeIntervention> {



    default TypeIntervention fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeIntervention typeIntervention = new TypeIntervention();
        typeIntervention.setId(id);
        return typeIntervention;
    }
}
