package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.ModeEvacExcretaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModeEvacExcreta} and its DTO {@link ModeEvacExcretaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModeEvacExcretaMapper extends EntityMapper<ModeEvacExcretaDTO, ModeEvacExcreta> {


    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    ModeEvacExcreta toEntity(ModeEvacExcretaDTO modeEvacExcretaDTO);

    default ModeEvacExcreta fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModeEvacExcreta modeEvacExcreta = new ModeEvacExcreta();
        modeEvacExcreta.setId(id);
        return modeEvacExcreta;
    }
}
