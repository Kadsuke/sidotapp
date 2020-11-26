package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.ModeEvacuationEauUseeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModeEvacuationEauUsee} and its DTO {@link ModeEvacuationEauUseeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModeEvacuationEauUseeMapper extends EntityMapper<ModeEvacuationEauUseeDTO, ModeEvacuationEauUsee> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    ModeEvacuationEauUsee toEntity(ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO);

    default ModeEvacuationEauUsee fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModeEvacuationEauUsee modeEvacuationEauUsee = new ModeEvacuationEauUsee();
        modeEvacuationEauUsee.setId(id);
        return modeEvacuationEauUsee;
    }
}
