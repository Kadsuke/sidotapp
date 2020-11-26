package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.AnalyseSpecialiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnalyseSpecialite} and its DTO {@link AnalyseSpecialiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnalyseSpecialiteMapper extends EntityMapper<AnalyseSpecialiteDTO, AnalyseSpecialite> {


    @Mapping(target = "analyseParametres", ignore = true)
    @Mapping(target = "removeAnalyseParametre", ignore = true)
    AnalyseSpecialite toEntity(AnalyseSpecialiteDTO analyseSpecialiteDTO);

    default AnalyseSpecialite fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnalyseSpecialite analyseSpecialite = new AnalyseSpecialite();
        analyseSpecialite.setId(id);
        return analyseSpecialite;
    }
}
