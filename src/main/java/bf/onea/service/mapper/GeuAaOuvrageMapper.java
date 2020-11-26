package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeuAaOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeuAaOuvrage} and its DTO {@link GeuAaOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoParcelleMapper.class, NatureOuvrageMapper.class, TypeHabitationMapper.class, SourceApprovEpMapper.class, ModeEvacuationEauUseeMapper.class, ModeEvacExcretaMapper.class, MaconMapper.class, PrefabricantMapper.class})
public interface GeuAaOuvrageMapper extends EntityMapper<GeuAaOuvrageDTO, GeuAaOuvrage> {

    @Mapping(source = "geoparcelle.id", target = "geoparcelleId")
    @Mapping(source = "natureouvrage.id", target = "natureouvrageId")
    @Mapping(source = "typehabitation.id", target = "typehabitationId")
    @Mapping(source = "sourceapprovep.id", target = "sourceapprovepId")
    @Mapping(source = "modeevacuationeauusee.id", target = "modeevacuationeauuseeId")
    @Mapping(source = "modeevacexcreta.id", target = "modeevacexcretaId")
    @Mapping(source = "macon.id", target = "maconId")
    @Mapping(source = "prefabricant.id", target = "prefabricantId")
    GeuAaOuvrageDTO toDto(GeuAaOuvrage geuAaOuvrage);

    @Mapping(target = "geuRealisations", ignore = true)
    @Mapping(target = "removeGeuRealisation", ignore = true)
    @Mapping(source = "geoparcelleId", target = "geoparcelle")
    @Mapping(source = "natureouvrageId", target = "natureouvrage")
    @Mapping(source = "typehabitationId", target = "typehabitation")
    @Mapping(source = "sourceapprovepId", target = "sourceapprovep")
    @Mapping(source = "modeevacuationeauuseeId", target = "modeevacuationeauusee")
    @Mapping(source = "modeevacexcretaId", target = "modeevacexcreta")
    @Mapping(source = "maconId", target = "macon")
    @Mapping(source = "prefabricantId", target = "prefabricant")
    GeuAaOuvrage toEntity(GeuAaOuvrageDTO geuAaOuvrageDTO);

    default GeuAaOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeuAaOuvrage geuAaOuvrage = new GeuAaOuvrage();
        geuAaOuvrage.setId(id);
        return geuAaOuvrage;
    }
}
