package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.SourceApprovEpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SourceApprovEp} and its DTO {@link SourceApprovEpDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SourceApprovEpMapper extends EntityMapper<SourceApprovEpDTO, SourceApprovEp> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    SourceApprovEp toEntity(SourceApprovEpDTO sourceApprovEpDTO);

    default SourceApprovEp fromId(Long id) {
        if (id == null) {
            return null;
        }
        SourceApprovEp sourceApprovEp = new SourceApprovEp();
        sourceApprovEp.setId(id);
        return sourceApprovEp;
    }
}
