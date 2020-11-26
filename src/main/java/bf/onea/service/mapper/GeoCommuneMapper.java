package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoCommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoCommune} and its DTO {@link GeoCommuneDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoProvinceMapper.class, GeoTypeCommuneMapper.class})
public interface GeoCommuneMapper extends EntityMapper<GeoCommuneDTO, GeoCommune> {

    @Mapping(source = "geoprovince.id", target = "geoprovinceId")
    @Mapping(source = "geotypecommune.id", target = "geotypecommuneId")
    GeoCommuneDTO toDto(GeoCommune geoCommune);

    @Mapping(target = "geoLocalites", ignore = true)
    @Mapping(target = "removeGeoLocalite", ignore = true)
    @Mapping(target = "geuPSAS", ignore = true)
    @Mapping(target = "removeGeuPSA", ignore = true)
    @Mapping(source = "geoprovinceId", target = "geoprovince")
    @Mapping(source = "geotypecommuneId", target = "geotypecommune")
    GeoCommune toEntity(GeoCommuneDTO geoCommuneDTO);

    default GeoCommune fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoCommune geoCommune = new GeoCommune();
        geoCommune.setId(id);
        return geoCommune;
    }
}
