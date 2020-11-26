package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuUsageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuUsage} and its DTO {@link GeuUsageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeuUsageMapper extends EntityMapper<GeuUsageDTO, GeuUsage> {


    @Mapping(target = "geuRaccordements", ignore = true)
    @Mapping(target = "removeGeuRaccordement", ignore = true)
    GeuUsage toEntity(GeuUsageDTO geuUsageDTO);

    default GeuUsage fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuUsage geuUsage = new GeuUsage();
        geuUsage.setId(id);
        return geuUsage;
    }
}
