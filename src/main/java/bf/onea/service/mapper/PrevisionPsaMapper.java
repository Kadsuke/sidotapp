package bf.onea.service.mapper;

import bf.onea.domain.*;
import bf.onea.service.dto.PrevisionPsaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisionPsa} and its DTO {@link PrevisionPsaDTO}.
 */
@Mapper(componentModel = "spring", uses = { CentreMapper.class, RefAnneeMapper.class })
public interface PrevisionPsaMapper extends EntityMapper<PrevisionPsaDTO, PrevisionPsa> {
    @Mapping(source = "centre.id", target = "centreId")
    @Mapping(source = "refAnnee.id", target = "refAnneeId")
    PrevisionPsaDTO toDto(PrevisionPsa previsionPsa);

    @Mapping(source = "centreId", target = "centre")
    @Mapping(source = "refAnneeId", target = "refAnnee")
    PrevisionPsa toEntity(PrevisionPsaDTO previsionPsaDTO);

    default PrevisionPsa fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisionPsa previsionPsa = new PrevisionPsa();
        previsionPsa.setId(id);
        return previsionPsa;
    }
}
