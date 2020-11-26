package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.EtatOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatOuvrage} and its DTO {@link EtatOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatOuvrageMapper extends EntityMapper<EtatOuvrageDTO, EtatOuvrage> {



    default EtatOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatOuvrage etatOuvrage = new EtatOuvrage();
        etatOuvrage.setId(id);
        return etatOuvrage;
    }
}
