package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.EtatEquipementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtatEquipement} and its DTO {@link EtatEquipementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtatEquipementMapper extends EntityMapper<EtatEquipementDTO, EtatEquipement> {



    default EtatEquipement fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtatEquipement etatEquipement = new EtatEquipement();
        etatEquipement.setId(id);
        return etatEquipement;
    }
}
