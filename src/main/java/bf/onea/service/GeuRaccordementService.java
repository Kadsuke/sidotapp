package bf.onea.service;

import bf.onea.domain.GeuRaccordement;
import bf.onea.repository.GeuRaccordementRepository;
import bf.onea.service.dto.GeuRaccordementDTO;
import bf.onea.service.mapper.GeuRaccordementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuRaccordement}.
 */
@Service
@Transactional
public class GeuRaccordementService {

    private final Logger log = LoggerFactory.getLogger(GeuRaccordementService.class);

    private final GeuRaccordementRepository geuRaccordementRepository;

    private final GeuRaccordementMapper geuRaccordementMapper;

    public GeuRaccordementService(GeuRaccordementRepository geuRaccordementRepository, GeuRaccordementMapper geuRaccordementMapper) {
        this.geuRaccordementRepository = geuRaccordementRepository;
        this.geuRaccordementMapper = geuRaccordementMapper;
    }

    /**
     * Save a geuRaccordement.
     *
     * @param geuRaccordementDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuRaccordementDTO save(GeuRaccordementDTO geuRaccordementDTO) {
        log.debug("Request to save GeuRaccordement : {}", geuRaccordementDTO);
        GeuRaccordement geuRaccordement = geuRaccordementMapper.toEntity(geuRaccordementDTO);
        geuRaccordement = geuRaccordementRepository.save(geuRaccordement);
        return geuRaccordementMapper.toDto(geuRaccordement);
    }

    /**
     * Get all the geuRaccordements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuRaccordementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuRaccordements");
        return geuRaccordementRepository.findAll(pageable)
            .map(geuRaccordementMapper::toDto);
    }


    /**
     * Get one geuRaccordement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuRaccordementDTO> findOne(Long id) {
        log.debug("Request to get GeuRaccordement : {}", id);
        return geuRaccordementRepository.findById(id)
            .map(geuRaccordementMapper::toDto);
    }

    /**
     * Delete the geuRaccordement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuRaccordement : {}", id);
        geuRaccordementRepository.deleteById(id);
    }
}
