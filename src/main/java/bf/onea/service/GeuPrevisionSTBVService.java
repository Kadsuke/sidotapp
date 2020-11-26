package bf.onea.service;

import bf.onea.domain.GeuPrevisionSTBV;
import bf.onea.repository.GeuPrevisionSTBVRepository;
import bf.onea.service.dto.GeuPrevisionSTBVDTO;
import bf.onea.service.mapper.GeuPrevisionSTBVMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuPrevisionSTBV}.
 */
@Service
@Transactional
public class GeuPrevisionSTBVService {

    private final Logger log = LoggerFactory.getLogger(GeuPrevisionSTBVService.class);

    private final GeuPrevisionSTBVRepository geuPrevisionSTBVRepository;

    private final GeuPrevisionSTBVMapper geuPrevisionSTBVMapper;

    public GeuPrevisionSTBVService(GeuPrevisionSTBVRepository geuPrevisionSTBVRepository, GeuPrevisionSTBVMapper geuPrevisionSTBVMapper) {
        this.geuPrevisionSTBVRepository = geuPrevisionSTBVRepository;
        this.geuPrevisionSTBVMapper = geuPrevisionSTBVMapper;
    }

    /**
     * Save a geuPrevisionSTBV.
     *
     * @param geuPrevisionSTBVDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuPrevisionSTBVDTO save(GeuPrevisionSTBVDTO geuPrevisionSTBVDTO) {
        log.debug("Request to save GeuPrevisionSTBV : {}", geuPrevisionSTBVDTO);
        GeuPrevisionSTBV geuPrevisionSTBV = geuPrevisionSTBVMapper.toEntity(geuPrevisionSTBVDTO);
        geuPrevisionSTBV = geuPrevisionSTBVRepository.save(geuPrevisionSTBV);
        return geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);
    }

    /**
     * Get all the geuPrevisionSTBVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuPrevisionSTBVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuPrevisionSTBVS");
        return geuPrevisionSTBVRepository.findAll(pageable)
            .map(geuPrevisionSTBVMapper::toDto);
    }


    /**
     * Get one geuPrevisionSTBV by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuPrevisionSTBVDTO> findOne(Long id) {
        log.debug("Request to get GeuPrevisionSTBV : {}", id);
        return geuPrevisionSTBVRepository.findById(id)
            .map(geuPrevisionSTBVMapper::toDto);
    }

    /**
     * Delete the geuPrevisionSTBV by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuPrevisionSTBV : {}", id);
        geuPrevisionSTBVRepository.deleteById(id);
    }
}
