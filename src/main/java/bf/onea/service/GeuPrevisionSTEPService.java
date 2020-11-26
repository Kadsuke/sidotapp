package bf.onea.service;

import bf.onea.domain.GeuPrevisionSTEP;
import bf.onea.repository.GeuPrevisionSTEPRepository;
import bf.onea.service.dto.GeuPrevisionSTEPDTO;
import bf.onea.service.mapper.GeuPrevisionSTEPMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuPrevisionSTEP}.
 */
@Service
@Transactional
public class GeuPrevisionSTEPService {

    private final Logger log = LoggerFactory.getLogger(GeuPrevisionSTEPService.class);

    private final GeuPrevisionSTEPRepository geuPrevisionSTEPRepository;

    private final GeuPrevisionSTEPMapper geuPrevisionSTEPMapper;

    public GeuPrevisionSTEPService(GeuPrevisionSTEPRepository geuPrevisionSTEPRepository, GeuPrevisionSTEPMapper geuPrevisionSTEPMapper) {
        this.geuPrevisionSTEPRepository = geuPrevisionSTEPRepository;
        this.geuPrevisionSTEPMapper = geuPrevisionSTEPMapper;
    }

    /**
     * Save a geuPrevisionSTEP.
     *
     * @param geuPrevisionSTEPDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuPrevisionSTEPDTO save(GeuPrevisionSTEPDTO geuPrevisionSTEPDTO) {
        log.debug("Request to save GeuPrevisionSTEP : {}", geuPrevisionSTEPDTO);
        GeuPrevisionSTEP geuPrevisionSTEP = geuPrevisionSTEPMapper.toEntity(geuPrevisionSTEPDTO);
        geuPrevisionSTEP = geuPrevisionSTEPRepository.save(geuPrevisionSTEP);
        return geuPrevisionSTEPMapper.toDto(geuPrevisionSTEP);
    }

    /**
     * Get all the geuPrevisionSTEPS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuPrevisionSTEPDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuPrevisionSTEPS");
        return geuPrevisionSTEPRepository.findAll(pageable)
            .map(geuPrevisionSTEPMapper::toDto);
    }


    /**
     * Get one geuPrevisionSTEP by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuPrevisionSTEPDTO> findOne(Long id) {
        log.debug("Request to get GeuPrevisionSTEP : {}", id);
        return geuPrevisionSTEPRepository.findById(id)
            .map(geuPrevisionSTEPMapper::toDto);
    }

    /**
     * Delete the geuPrevisionSTEP by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuPrevisionSTEP : {}", id);
        geuPrevisionSTEPRepository.deleteById(id);
    }
}
