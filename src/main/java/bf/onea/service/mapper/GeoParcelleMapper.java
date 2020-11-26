package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoParcelleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoParcelle} and its DTO {@link GeoParcelleDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoLotMapper.class})
public interface GeoParcelleMapper extends EntityMapper<GeoParcelleDTO, GeoParcelle> {

    @Mapping(source = "geolot.id", target = "geolotId")
    GeoParcelleDTO toDto(GeoParcelle geoParcelle);

    @Mapping(target = "geuAaOuvrages", ignore = true)
    @Mapping(target = "removeGeuAaOuvrage", ignore = true)
    @Mapping(target = "geuRaccordements", ignore = true)
    @Mapping(target = "removeGeuRaccordement", ignore = true)
    @Mapping(source = "geolotId", target = "geolot")
    GeoParcelle toEntity(GeoParcelleDTO geoParcelleDTO);

    default GeoParcelle fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoParcelle geoParcelle = new GeoParcelle();
        geoParcelle.setId(id);
        return geoParcelle;
    }
}
