package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoTypeCommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoTypeCommune} and its DTO {@link GeoTypeCommuneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoTypeCommuneMapper extends EntityMapper<GeoTypeCommuneDTO, GeoTypeCommune> {


    @Mapping(target = "geoCommunes", ignore = true)
    @Mapping(target = "removeGeoCommune", ignore = true)
    GeoTypeCommune toEntity(GeoTypeCommuneDTO geoTypeCommuneDTO);

    default GeoTypeCommune fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoTypeCommune geoTypeCommune = new GeoTypeCommune();
        geoTypeCommune.setId(id);
        return geoTypeCommune;
    }
}
