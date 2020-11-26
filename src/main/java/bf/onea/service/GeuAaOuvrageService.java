package bf.onea.service;

import bf.onea.domain.GeuAaOuvrage;
import bf.onea.repository.GeuAaOuvrageRepository;
import bf.onea.service.dto.GeuAaOuvrageDTO;
import bf.onea.service.mapper.GeuAaOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuAaOuvrage}.
 */
@Service
@Transactional
public class GeuAaOuvrageService {

    private final Logger log = LoggerFactory.getLogger(GeuAaOuvrageService.class);

    private final GeuAaOuvrageRepository geuAaOuvrageRepository;

    private final GeuAaOuvrageMapper geuAaOuvrageMapper;

    public GeuAaOuvrageService(GeuAaOuvrageRepository geuAaOuvrageRepository, GeuAaOuvrageMapper geuAaOuvrageMapper) {
        this.geuAaOuvrageRepository = geuAaOuvrageRepository;
        this.geuAaOuvrageMapper = geuAaOuvrageMapper;
    }

    /**
     * Save a geuAaOuvrage.
     *
     * @param geuAaOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuAaOuvrageDTO save(GeuAaOuvrageDTO geuAaOuvrageDTO) {
        log.debug("Request to save GeuAaOuvrage : {}", geuAaOuvrageDTO);
        GeuAaOuvrage geuAaOuvrage = geuAaOuvrageMapper.toEntity(geuAaOuvrageDTO);
        geuAaOuvrage = geuAaOuvrageRepository.save(geuAaOuvrage);
        return geuAaOuvrageMapper.toDto(geuAaOuvrage);
    }

    /**
     * Get all the geuAaOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuAaOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuAaOuvrages");
        return geuAaOuvrageRepository.findAll(pageable)
            .map(geuAaOuvrageMapper::toDto);
    }


    /**
     * Get one geuAaOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuAaOuvrageDTO> findOne(Long id) {
        log.debug("Request to get GeuAaOuvrage : {}", id);
        return geuAaOuvrageRepository.findById(id)
            .map(geuAaOuvrageMapper::toDto);
    }

    /**
     * Delete the geuAaOuvrage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuAaOuvrage : {}", id);
        geuAaOuvrageRepository.deleteById(id);
    }
}
