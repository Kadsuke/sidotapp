package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TacheronsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tacherons} and its DTO {@link TacheronsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TacheronsMapper extends EntityMapper<TacheronsDTO, Tacherons> {


    @Mapping(target = "geuRaccordements", ignore = true)
    @Mapping(target = "removeGeuRaccordement", ignore = true)
    Tacherons toEntity(TacheronsDTO tacheronsDTO);

    default Tacherons fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tacherons tacherons = new Tacherons();
        tacherons.setId(id);
        return tacherons;
    }
}
