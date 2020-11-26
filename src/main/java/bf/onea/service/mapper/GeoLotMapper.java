package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoLotDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoLot} and its DTO {@link GeoLotDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoSectionMapper.class})
public interface GeoLotMapper extends EntityMapper<GeoLotDTO, GeoLot> {

    @Mapping(source = "geosection.id", target = "geosectionId")
    GeoLotDTO toDto(GeoLot geoLot);

    @Mapping(target = "geoParcelles", ignore = true)
    @Mapping(target = "removeGeoParcelle", ignore = true)
    @Mapping(source = "geosectionId", target = "geosection")
    GeoLot toEntity(GeoLotDTO geoLotDTO);

    default GeoLot fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoLot geoLot = new GeoLot();
        geoLot.setId(id);
        return geoLot;
    }
}
