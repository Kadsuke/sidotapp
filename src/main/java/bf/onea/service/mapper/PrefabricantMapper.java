package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.PrefabricantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prefabricant} and its DTO {@link PrefabricantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrefabricantMapper extends EntityMapper<PrefabricantDTO, Prefabricant> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    Prefabricant toEntity(PrefabricantDTO prefabricantDTO);

    default Prefabricant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prefabricant prefabricant = new Prefabricant();
        prefabricant.setId(id);
        return prefabricant;
    }
}
