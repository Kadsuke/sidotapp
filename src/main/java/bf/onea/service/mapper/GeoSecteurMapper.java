package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.GeoSecteurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoSecteur} and its DTO {@link GeoSecteurDTO}.
 */
@Mapper(componentModel = "spring", uses = {GeoLocaliteMapper.class})
public interface GeoSecteurMapper extends EntityMapper<GeoSecteurDTO, GeoSecteur> {

    @Mapping(source = "geolocalite.id", target = "geolocaliteId")
    GeoSecteurDTO toDto(GeoSecteur geoSecteur);

    @Mapping(target = "geoSections", ignore = true)
    @Mapping(target = "removeGeoSection", ignore = true)
    @Mapping(source = "geolocaliteId", target = "geolocalite")
    GeoSecteur toEntity(GeoSecteurDTO geoSecteurDTO);

    default GeoSecteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        GeoSecteur geoSecteur = new GeoSecteur();
        geoSecteur.setId(id);
        return geoSecteur;
    }
}
