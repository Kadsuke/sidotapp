package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoRegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoRegion} and its DTO {@link GeoRegionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoRegionMapper extends EntityMapper<GeoRegionDTO, GeoRegion> {


    @Mapping(target = "geoProvinces", ignore = true)
    @Mapping(target = "removeGeoProvince", ignore = true)
    GeoRegion toEntity(GeoRegionDTO geoRegionDTO);

    default GeoRegion fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoRegion geoRegion = new GeoRegion();
        geoRegion.setId(id);
        return geoRegion;
    }
}
