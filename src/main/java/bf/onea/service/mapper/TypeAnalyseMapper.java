package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeAnalyseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeAnalyse} and its DTO {@link TypeAnalyseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeAnalyseMapper extends EntityMapper<TypeAnalyseDTO, TypeAnalyse> {



    default TypeAnalyse fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeAnalyse typeAnalyse = new TypeAnalyse();
        typeAnalyse.setId(id);
        return typeAnalyse;
    }
}
