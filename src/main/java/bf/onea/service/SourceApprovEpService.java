package bf.onea.service;

import bf.onea.domain.SourceApprovEp;
import bf.onea.repository.SourceApprovEpRepository;
import bf.onea.service.dto.SourceApprovEpDTO;
import bf.onea.service.mapper.SourceApprovEpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SourceApprovEp}.
 */
@Service
@Transactional
public class SourceApprovEpService {

    private final Logger log = LoggerFactory.getLogger(SourceApprovEpService.class);

    private final SourceApprovEpRepository sourceApprovEpRepository;

    private final SourceApprovEpMapper sourceApprovEpMapper;

    public SourceApprovEpService(SourceApprovEpRepository sourceApprovEpRepository, SourceApprovEpMapper sourceApprovEpMapper) {
        this.sourceApprovEpRepository = sourceApprovEpRepository;
        this.sourceApprovEpMapper = sourceApprovEpMapper;
    }

    /**
     * Save a sourceApprovEp.
     *
     * @param sourceApprovEpDTO the entity to save.
     * @return the persisted entity.
     */
    public SourceApprovEpDTO save(SourceApprovEpDTO sourceApprovEpDTO) {
        log.debug("Request to save SourceApprovEp : {}", sourceApprovEpDTO);
        SourceApprovEp sourceApprovEp = sourceApprovEpMapper.toEntity(sourceApprovEpDTO);
        sourceApprovEp = sourceApprovEpRepository.save(sourceApprovEp);
        return sourceApprovEpMapper.toDto(sourceApprovEp);
    }

    /**
     * Get all the sourceApprovEps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SourceApprovEpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SourceApprovEps");
        return sourceApprovEpRepository.findAll(pageable)
            .map(sourceApprovEpMapper::toDto);
    }


    /**
     * Get one sourceApprovEp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SourceApprovEpDTO> findOne(Long id) {
        log.debug("Request to get SourceApprovEp : {}", id);
        return sourceApprovEpRepository.findById(id)
            .map(sourceApprovEpMapper::toDto);
    }

    /**
     * Delete the sourceApprovEp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SourceApprovEp : {}", id);
        sourceApprovEpRepository.deleteById(id);
    }
}
