package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoLocaliteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoLocalite} and its DTO {@link GeoLocaliteDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoCommuneMapper.class})
public interface GeoLocaliteMapper extends EntityMapper<GeoLocaliteDTO, GeoLocalite> {

    @Mapping(source = "geocommune.id", target = "geocommuneId")
    GeoLocaliteDTO toDto(GeoLocalite geoLocalite);

    @Mapping(target = "geoSecteurs", ignore = true)
    @Mapping(target = "removeGeoSecteur", ignore = true)
    @Mapping(source = "geocommuneId", target = "geocommune")
    GeoLocalite toEntity(GeoLocaliteDTO geoLocaliteDTO);

    default GeoLocalite fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoLocalite geoLocalite = new GeoLocalite();
        geoLocalite.setId(id);
        return geoLocalite;
    }
}
