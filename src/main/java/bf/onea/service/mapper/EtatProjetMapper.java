package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.EtatProjetDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatProjet} and its DTO {@link EtatProjetDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatProjetMapper extends EntityMapper<EtatProjetDTO, EtatProjet> {



    default EtatProjet fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatProjet etatProjet = new EtatProjet();
        etatProjet.setId(id);
        return etatProjet;
    }
}
