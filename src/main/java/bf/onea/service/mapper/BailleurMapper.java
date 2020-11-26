package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.BailleurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bailleur} and its DTO {@link BailleurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BailleurMapper extends EntityMapper<BailleurDTO, Bailleur> {



    default Bailleur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bailleur bailleur = new Bailleur();
        bailleur.setId(id);
        return bailleur;
    }
}
