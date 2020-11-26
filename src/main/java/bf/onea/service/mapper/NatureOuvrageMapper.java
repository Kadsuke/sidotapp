package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.NatureOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NatureOuvrage} and its DTO {@link NatureOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NatureOuvrageMapper extends EntityMapper<NatureOuvrageDTO, NatureOuvrage> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    NatureOuvrage toEntity(NatureOuvrageDTO natureOuvrageDTO);

    default NatureOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        NatureOuvrage natureOuvrage = new NatureOuvrage();
        natureOuvrage.setId(id);
        return natureOuvrage;
    }
}
