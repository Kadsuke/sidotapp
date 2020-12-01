package bf.onea.service.mapper;

import bf.onea.domain.*;
import bf.onea.service.dto.PrevisionAssainissementAuDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisionAssainissementAu} and its DTO {@link PrevisionAssainissementAuDTO}.
 */
@Mapper(componentModel = "spring", uses = { RefAnneeMapper.class, CentreMapper.class })
public interface PrevisionAssainissementAuMapper extends EntityMapper<PrevisionAssainissementAuDTO, PrevisionAssainissementAu> {
    @Mapping(source = "refannee.id", target = "refanneeId")
    @Mapping(source = "centre.id", target = "centreId")
    PrevisionAssainissementAuDTO toDto(PrevisionAssainissementAu previsionAssainissementAu);

    @Mapping(source = "refanneeId", target = "refannee")
    @Mapping(source = "centreId", target = "centre")
    PrevisionAssainissementAu toEntity(PrevisionAssainissementAuDTO previsionAssainissementAuDTO);

    default PrevisionAssainissementAu fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisionAssainissementAu previsionAssainissementAu = new PrevisionAssainissementAu();
        previsionAssainissementAu.setId(id);
        return previsionAssainissementAu;
    }
}
