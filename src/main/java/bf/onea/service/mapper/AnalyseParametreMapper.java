package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.AnalyseParametreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnalyseParametre} and its DTO {@link AnalyseParametreDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnalyseSpecialiteMapper.class})
public interface AnalyseParametreMapper extends EntityMapper<AnalyseParametreDTO, AnalyseParametre> {

    @Mapping(source = "analysespecialite.id", target = "analysespecialiteId")
    AnalyseParametreDTO toDto(AnalyseParametre analyseParametre);

    @Mapping(source = "analysespecialiteId", target = "analysespecialite")
    AnalyseParametre toEntity(AnalyseParametreDTO analyseParametreDTO);

    default AnalyseParametre fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnalyseParametre analyseParametre = new AnalyseParametre();
        analyseParametre.setId(id);
        return analyseParametre;
    }
}
