package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.TypeBeneficiaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeBeneficiaire} and its DTO {@link TypeBeneficiaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeBeneficiaireMapper extends EntityMapper<TypeBeneficiaireDTO, TypeBeneficiaire> {



    default TypeBeneficiaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeBeneficiaire typeBeneficiaire = new TypeBeneficiaire();
        typeBeneficiaire.setId(id);
        return typeBeneficiaire;
    }
}
