package bf.onea.service.mapper;


import bf.onea.domain.*;
import bf.onea.service.dto.PrevisionAssainissementColDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisionAssainissementCol} and its DTO {@link PrevisionAssainissementColDTO}.
 */
@Mapper(componentModel = "spring", uses = {RefAnneeMapper.class, CentreMapper.class})
public interface PrevisionAssainissementColMapper extends EntityMapper<PrevisionAssainissementColDTO, PrevisionAssainissementCol> {

    @Mapping(source = "refannee.id", target = "refanneeId")
    @Mapping(source = "centre.id", target = "centreId")
    PrevisionAssainissementColDTO toDto(PrevisionAssainissementCol previsionAssainissementCol);

    @Mapping(source = "refanneeId", target = "refannee")
    @Mapping(source = "centreId", target = "centre")
    PrevisionAssainissementCol toEntity(PrevisionAssainissementColDTO previsionAssainissementColDTO);

    default PrevisionAssainissementCol fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisionAssainissementCol previsionAssainissementCol = new PrevisionAssainissementCol();
        previsionAssainissementCol.setId(id);
        return previsionAssainissementCol;
    }
}
