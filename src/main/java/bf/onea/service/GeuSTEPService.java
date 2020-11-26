package bf.onea.service;

import bf.onea.domain.GeuSTEP;
import bf.onea.repository.GeuSTEPRepository;
import bf.onea.service.dto.GeuSTEPDTO;
import bf.onea.service.mapper.GeuSTEPMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuSTEP}.
 */
@Service
@Transactional
public class GeuSTEPService {

    private final Logger log = LoggerFactory.getLogger(GeuSTEPService.class);

    private final GeuSTEPRepository geuSTEPRepository;

    private final GeuSTEPMapper geuSTEPMapper;

    public GeuSTEPService(GeuSTEPRepository geuSTEPRepository, GeuSTEPMapper geuSTEPMapper) {
        this.geuSTEPRepository = geuSTEPRepository;
        this.geuSTEPMapper = geuSTEPMapper;
    }

    /**
     * Save a geuSTEP.
     *
     * @param geuSTEPDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuSTEPDTO save(GeuSTEPDTO geuSTEPDTO) {
        log.debug("Request to save GeuSTEP : {}", geuSTEPDTO);
        GeuSTEP geuSTEP = geuSTEPMapper.toEntity(geuSTEPDTO);
        geuSTEP = geuSTEPRepository.save(geuSTEP);
        return geuSTEPMapper.toDto(geuSTEP);
    }

    /**
     * Get all the geuSTEPS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuSTEPDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuSTEPS");
        return geuSTEPRepository.findAll(pageable)
            .map(geuSTEPMapper::toDto);
    }


    /**
     * Get one geuSTEP by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuSTEPDTO> findOne(Long id) {
        log.debug("Request to get GeuSTEP : {}", id);
        return geuSTEPRepository.findById(id)
            .map(geuSTEPMapper::toDto);
    }

    /**
     * Delete the geuSTEP by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuSTEP : {}", id);
        geuSTEPRepository.deleteById(id);
    }
}
