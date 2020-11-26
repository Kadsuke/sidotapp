package bf.onea.service;

import bf.onea.domain.GeuRealisationSTBV;
import bf.onea.repository.GeuRealisationSTBVRepository;
import bf.onea.service.dto.GeuRealisationSTBVDTO;
import bf.onea.service.mapper.GeuRealisationSTBVMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeuRealisationSTBV}.
 */
@Service
@Transactional
public class GeuRealisationSTBVService {

    private final Logger log = LoggerFactory.getLogger(GeuRealisationSTBVService.class);

    private final GeuRealisationSTBVRepository geuRealisationSTBVRepository;

    private final GeuRealisationSTBVMapper geuRealisationSTBVMapper;

    public GeuRealisationSTBVService(GeuRealisationSTBVRepository geuRealisationSTBVRepository, GeuRealisationSTBVMapper geuRealisationSTBVMapper) {
        this.geuRealisationSTBVRepository = geuRealisationSTBVRepository;
        this.geuRealisationSTBVMapper = geuRealisationSTBVMapper;
    }

    /**
     * Save a geuRealisationSTBV.
     *
     * @param geuRealisationSTBVDTO the entity to save.
     * @return the persisted entity.
     */
    public GeuRealisationSTBVDTO save(GeuRealisationSTBVDTO geuRealisationSTBVDTO) {
        log.debug("Request to save GeuRealisationSTBV : {}", geuRealisationSTBVDTO);
        GeuRealisationSTBV geuRealisationSTBV = geuRealisationSTBVMapper.toEntity(geuRealisationSTBVDTO);
        geuRealisationSTBV = geuRealisationSTBVRepository.save(geuRealisationSTBV);
        return geuRealisationSTBVMapper.toDto(geuRealisationSTBV);
    }

    /**
     * Get all the geuRealisationSTBVS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeuRealisationSTBVDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeuRealisationSTBVS");
        return geuRealisationSTBVRepository.findAll(pageable)
            .map(geuRealisationSTBVMapper::toDto);
    }


    /**
     * Get one geuRealisationSTBV by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeuRealisationSTBVDTO> findOne(Long id) {
        log.debug("Request to get GeuRealisationSTBV : {}", id);
        return geuRealisationSTBVRepository.findById(id)
            .map(geuRealisationSTBVMapper::toDto);
    }

    /**
     * Delete the geuRealisationSTBV by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeuRealisationSTBV : {}", id);
        geuRealisationSTBVRepository.deleteById(id);
    }
}
