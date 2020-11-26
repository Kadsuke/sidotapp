package bf.onea.service;

import bf.onea.domain.RefPeriodicite;
import bf.onea.repository.RefPeriodiciteRepository;
import bf.onea.service.dto.RefPeriodiciteDTO;
import bf.onea.service.mapper.RefPeriodiciteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RefPeriodicite}.
 */
@Service
@Transactional
public class RefPeriodiciteService {

    private final Logger log = LoggerFactory.getLogger(RefPeriodiciteService.class);

    private final RefPeriodiciteRepository refPeriodiciteRepository;

    private final RefPeriodiciteMapper refPeriodiciteMapper;

    public RefPeriodiciteService(RefPeriodiciteRepository refPeriodiciteRepository, RefPeriodiciteMapper refPeriodiciteMapper) {
        this.refPeriodiciteRepository = refPeriodiciteRepository;
        this.refPeriodiciteMapper = refPeriodiciteMapper;
    }

    /**
     * Save a refPeriodicite.
     *
     * @param refPeriodiciteDTO the entity to save.
     * @return the persisted entity.
     */
    public RefPeriodiciteDTO save(RefPeriodiciteDTO refPeriodiciteDTO) {
        log.debug("Request to save RefPeriodicite : {}", refPeriodiciteDTO);
        RefPeriodicite refPeriodicite = refPeriodiciteMapper.toEntity(refPeriodiciteDTO);
        refPeriodicite = refPeriodiciteRepository.save(refPeriodicite);
        return refPeriodiciteMapper.toDto(refPeriodicite);
    }

    /**
     * Get all the refPeriodicites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RefPeriodiciteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefPeriodicites");
        return refPeriodiciteRepository.findAll(pageable)
            .map(refPeriodiciteMapper::toDto);
    }


    /**
     * Get one refPeriodicite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RefPeriodiciteDTO> findOne(Long id) {
        log.debug("Request to get RefPeriodicite : {}", id);
        return refPeriodiciteRepository.findById(id)
            .map(refPeriodiciteMapper::toDto);
    }

    /**
     * Delete the refPeriodicite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RefPeriodicite : {}", id);
        refPeriodiciteRepository.deleteById(id);
    }
}
