package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.CategorieRessourcesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieRessources} and its DTO {@link CategorieRessourcesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieRessourcesMapper extends EntityMapper<CategorieRessourcesDTO, CategorieRessources> {


    @Mapping(target = "typeOuvrages", ignore = true)
    @Mapping(target = "removeTypeOuvrage", ignore = true)
    CategorieRessources toEntity(CategorieRessourcesDTO categorieRessourcesDTO);

    default CategorieRessources fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieRessources categorieRessources = new CategorieRessources();
        categorieRessources.setId(id);
        return categorieRessources;
    }
}
