package bf.onea.service;

import bf.onea.domain.GeuRealisation;
import bf.onea.repository.GeuRealisationRepository;
import bf.onea.service.dto.GeuRealisationDTO;
import bf.onea.service.mapper.GeuRealisationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuRealisation}.
 */
@Service
@Transactional
public class GeuRealisationService {

    private final Logger log = LoggerFactory.getLogger(GeuRealisationService.class);

    private final GeuRealisationRepository geuRealisationRepository;

    private final GeuRealisationMapper geuRealisationMapper;

    public GeuRealisationService(GeuRealisationRepository geuRealisationRepository, GeuRealisationMapper geuRealisationMapper) {
        this.geuRealisationRepository = geuRealisationRepository;
        this.geuRealisationMapper = geuRealisationMapper;
    }

    /**
     * Save a geuRealisation.
     *
     * @param geuRealisationDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuRealisationDTO save(GeuRealisationDTO geuRealisationDTO) {
        log.debug("Request to save GeuRealisation : {}", geuRealisationDTO);
        GeuRealisation geuRealisation = geuRealisationMapper.toEntity(geuRealisationDTO);
        geuRealisation = geuRealisationRepository.save(geuRealisation);
        return geuRealisationMapper.toDto(geuRealisation);
    }

    /**
     * Get all the geuRealisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuRealisationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuRealisations");
        return geuRealisationRepository.findAll(pageable)
            .map(geuRealisationMapper::toDto);
    }


    /**
     * Get one geuRealisation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuRealisationDTO> findOne(Long id) {
        log.debug("Request to get GeuRealisation : {}", id);
        return geuRealisationRepository.findById(id)
            .map(geuRealisationMapper::toDto);
    }

    /**
     * Delete the geuRealisation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuRealisation : {}", id);
        geuRealisationRepository.deleteById(id);
    }
}
