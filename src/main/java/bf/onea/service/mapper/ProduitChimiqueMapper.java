package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.ProduitChimiqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProduitChimique} and its DTO {@link ProduitChimiqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProduitChimiqueMapper extends EntityMapper<ProduitChimiqueDTO, ProduitChimique> {



    default ProduitChimique fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProduitChimique produitChimique = new ProduitChimique();
        produitChimique.setId(id);
        return produitChimique;
    }
}
