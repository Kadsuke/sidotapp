package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoSectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoSection} and its DTO {@link GeoSectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoSecteurMapper.class})
public interface GeoSectionMapper extends EntityMapper<GeoSectionDTO, GeoSection> {

    @Mapping(source = "geosecteur.id", target = "geosecteurId")
    GeoSectionDTO toDto(GeoSection geoSection);

    @Mapping(target = "geoLots", ignore = true)
    @Mapping(target = "removeGeoLot", ignore = true)
    @Mapping(source = "geosecteurId", target = "geosecteur")
    GeoSection toEntity(GeoSectionDTO geoSectionDTO);

    default GeoSection fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoSection geoSection = new GeoSection();
        geoSection.setId(id);
        return geoSection;
    }
}
