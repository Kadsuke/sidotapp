package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.PrestataireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prestataire} and its DTO {@link PrestataireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrestataireMapper extends EntityMapper<PrestataireDTO, Prestataire> {



    default Prestataire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prestataire prestataire = new Prestataire();
        prestataire.setId(id);
        return prestataire;
    }
}
