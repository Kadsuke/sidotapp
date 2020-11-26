package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeOuvrageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeOuvrage} and its DTO {@link TypeOuvrageDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieRessourcesMapper.class, CaracteristiqueOuvrageMapper.class})
public interface TypeOuvrageMapper extends EntityMapper<TypeOuvrageDTO, TypeOuvrage> {

    @Mapping(source = "categorieressources.id", target = "categorieressourcesId")
    @Mapping(source = "caracteristiqueouvrage.id", target = "caracteristiqueouvrageId")
    TypeOuvrageDTO toDto(TypeOuvrage typeOuvrage);

    @Mapping(target = "caracteristiqueOuvrages", ignore = true)
    @Mapping(target = "removeCaracteristiqueOuvrage", ignore = true)
    @Mapping(source = "categorieressourcesId", target = "categorieressources")
    @Mapping(source = "caracteristiqueouvrageId", target = "caracteristiqueouvrage")
    TypeOuvrage toEntity(TypeOuvrageDTO typeOuvrageDTO);

    default TypeOuvrage fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeOuvrage typeOuvrage = new TypeOuvrage();
        typeOuvrage.setId(id);
        return typeOuvrage;
    }
}
