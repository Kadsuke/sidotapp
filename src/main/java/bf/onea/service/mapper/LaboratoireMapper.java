package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.LaboratoireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Laboratoire} and its DTO {@link LaboratoireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LaboratoireMapper extends EntityMapper<LaboratoireDTO, Laboratoire> {



    default Laboratoire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Laboratoire laboratoire = new Laboratoire();
        laboratoire.setId(id);
        return laboratoire;
    }
}
