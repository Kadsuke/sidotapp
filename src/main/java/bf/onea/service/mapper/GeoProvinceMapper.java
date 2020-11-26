package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoProvinceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoProvince} and its DTO {@link GeoProvinceDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoRegionMapper.class})
public interface GeoProvinceMapper extends EntityMapper<GeoProvinceDTO, GeoProvince> {

    @Mapping(source = "georegion.id", target = "georegionId")
    GeoProvinceDTO toDto(GeoProvince geoProvince);

    @Mapping(target = "geoCommunes", ignore = true)
    @Mapping(target = "removeGeoCommune", ignore = true)
    @Mapping(source = "georegionId", target = "georegion")
    GeoProvince toEntity(GeoProvinceDTO geoProvinceDTO);

    default GeoProvince fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoProvince geoProvince = new GeoProvince();
        geoProvince.setId(id);
        return geoProvince;
    }
}
