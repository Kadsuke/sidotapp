package bf.onea.service;

import bf.onea.domain.GeuPSA;
import bf.onea.repository.GeuPSARepository;
import bf.onea.service.dto.GeuPSADTO;
import bf.onea.service.mapper.GeuPSAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuPSA}.
 */
@Service
@Transactional
public class GeuPSAService {

    private final Logger log = LoggerFactory.getLogger(GeuPSAService.class);

    private final GeuPSARepository geuPSARepository;

    private final GeuPSAMapper geuPSAMapper;

    public GeuPSAService(GeuPSARepository geuPSARepository, GeuPSAMapper geuPSAMapper) {
        this.geuPSARepository = geuPSARepository;
        this.geuPSAMapper = geuPSAMapper;
    }

    /**
     * Save a geuPSA.
     *
     * @param geuPSADTO the entity to save.
     * @return the persisted entity.
     */
    public GeuPSADTO save(GeuPSADTO geuPSADTO) {
        log.debug("Request to save GeuPSA : {}", geuPSADTO);
        GeuPSA geuPSA = geuPSAMapper.toEntity(geuPSADTO);
        geuPSA = geuPSARepository.save(geuPSA);
        return geuPSAMapper.toDto(geuPSA);
    }

    /**
     * Get all the geuPSAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuPSADTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuPSAS");
        return geuPSARepository.findAll(pageable)
            .map(geuPSAMapper::toDto);
    }


    /**
     * Get one geuPSA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuPSADTO> findOne(Long id) {
        log.debug("Request to get GeuPSA : {}", id);
        return geuPSARepository.findById(id)
            .map(geuPSAMapper::toDto);
    }

    /**
     * Delete the geuPSA by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuPSA : {}", id);
        geuPSARepository.deleteById(id);
    }
}
