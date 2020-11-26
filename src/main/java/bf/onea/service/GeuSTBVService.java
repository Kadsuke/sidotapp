package bf.onea.service;

import bf.onea.domain.GeuSTBV;
import bf.onea.repository.GeuSTBVRepository;
import bf.onea.service.dto.GeuSTBVDTO;
import bf.onea.service.mapper.GeuSTBVMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuSTBV}.
 */
@Service
@Transactional
public class GeuSTBVService {

    private final Logger log = LoggerFactory.getLogger(GeuSTBVService.class);

    private final GeuSTBVRepository geuSTBVRepository;

    private final GeuSTBVMapper geuSTBVMapper;

    public GeuSTBVService(GeuSTBVRepository geuSTBVRepository, GeuSTBVMapper geuSTBVMapper) {
        this.geuSTBVRepository = geuSTBVRepository;
        this.geuSTBVMapper = geuSTBVMapper;
    }

    /**
     * Save a geuSTBV.
     *
     * @param geuSTBVDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuSTBVDTO save(GeuSTBVDTO geuSTBVDTO) {
        log.debug("Request to save GeuSTBV : {}", geuSTBVDTO);
        GeuSTBV geuSTBV = geuSTBVMapper.toEntity(geuSTBVDTO);
        geuSTBV = geuSTBVRepository.save(geuSTBV);
        return geuSTBVMapper.toDto(geuSTBV);
    }

    /**
     * Get all the geuSTBVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuSTBVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuSTBVS");
        return geuSTBVRepository.findAll(pageable)
            .map(geuSTBVMapper::toDto);
    }


    /**
     * Get one geuSTBV by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuSTBVDTO> findOne(Long id) {
        log.debug("Request to get GeuSTBV : {}", id);
        return geuSTBVRepository.findById(id)
            .map(geuSTBVMapper::toDto);
    }

    /**
     * Delete the geuSTBV by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuSTBV : {}", id);
        geuSTBVRepository.deleteById(id);
    }
}
