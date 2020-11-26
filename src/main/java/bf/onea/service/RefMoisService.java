package bf.onea.service;

import bf.onea.domain.RefMois;
import bf.onea.repository.RefMoisRepository;
import bf.onea.service.dto.RefMoisDTO;
import bf.onea.service.mapper.RefMoisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RefMois}.
 */
@Service
@Transactional
public class RefMoisService {

    private final Logger log = LoggerFactory.getLogger(RefMoisService.class);

    private final RefMoisRepository refMoisRepository;

    private final RefMoisMapper refMoisMapper;

    public RefMoisService(RefMoisRepository refMoisRepository, RefMoisMapper refMoisMapper) {
        this.refMoisRepository = refMoisRepository;
        this.refMoisMapper = refMoisMapper;
    }

    /**
     * Save a refMois.
     *
     * @param refMoisDTO the entity to save.
     * @return the persisted entity.
     */
    public RefMoisDTO save(RefMoisDTO refMoisDTO) {
        log.debug("Request to save RefMois : {}", refMoisDTO);
        RefMois refMois = refMoisMapper.toEntity(refMoisDTO);
        refMois = refMoisRepository.save(refMois);
        return refMoisMapper.toDto(refMois);
    }

    /**
     * Get all the refMois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RefMoisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefMois");
        return refMoisRepository.findAll(pageable)
            .map(refMoisMapper::toDto);
    }


    /**
     * Get one refMois by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RefMoisDTO> findOne(Long id) {
        log.debug("Request to get RefMois : {}", id);
        return refMoisRepository.findById(id)
            .map(refMoisMapper::toDto);
    }

    /**
     * Delete the refMois by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RefMois : {}", id);
        refMoisRepository.deleteById(id);
    }
}
