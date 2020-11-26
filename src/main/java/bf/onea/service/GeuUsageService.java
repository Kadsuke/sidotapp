package bf.onea.service;

import bf.onea.domain.GeuUsage;
import bf.onea.repository.GeuUsageRepository;
import bf.onea.service.dto.GeuUsageDTO;
import bf.onea.service.mapper.GeuUsageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuUsage}.
 */
@Service
@Transactional
public class GeuUsageService {

    private final Logger log = LoggerFactory.getLogger(GeuUsageService.class);

    private final GeuUsageRepository geuUsageRepository;

    private final GeuUsageMapper geuUsageMapper;

    public GeuUsageService(GeuUsageRepository geuUsageRepository, GeuUsageMapper geuUsageMapper) {
        this.geuUsageRepository = geuUsageRepository;
        this.geuUsageMapper = geuUsageMapper;
    }

    /**
     * Save a geuUsage.
     *
     * @param geuUsageDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuUsageDTO save(GeuUsageDTO geuUsageDTO) {
        log.debug("Request to save GeuUsage : {}", geuUsageDTO);
        GeuUsage geuUsage = geuUsageMapper.toEntity(geuUsageDTO);
        geuUsage = geuUsageRepository.save(geuUsage);
        return geuUsageMapper.toDto(geuUsage);
    }

    /**
     * Get all the geuUsages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuUsageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuUsages");
        return geuUsageRepository.findAll(pageable)
            .map(geuUsageMapper::toDto);
    }


    /**
     * Get one geuUsage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuUsageDTO> findOne(Long id) {
        log.debug("Request to get GeuUsage : {}", id);
        return geuUsageRepository.findById(id)
            .map(geuUsageMapper::toDto);
    }

    /**
     * Delete the geuUsage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuUsage : {}", id);
        geuUsageRepository.deleteById(id);
    }
}
