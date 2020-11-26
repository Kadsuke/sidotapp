package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.RefSousDomaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RefSousDomaine} and its DTO {@link RefSousDomaineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefSousDomaineMapper extends EntityMapper<RefSousDomaineDTO, RefSousDomaine> {



    default RefSousDomaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        RefSousDomaine refSousDomaine = new RefSousDomaine();
        refSousDomaine.setId(id);
        return refSousDomaine;
    }
}
