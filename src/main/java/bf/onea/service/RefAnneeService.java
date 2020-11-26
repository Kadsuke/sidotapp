package bf.onea.service;

import bf.onea.domain.RefAnnee;
import bf.onea.repository.RefAnneeRepository;
import bf.onea.service.dto.RefAnneeDTO;
import bf.onea.service.mapper.RefAnneeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RefAnnee}.
 */
@Service
@Transactional
public class RefAnneeService {

    private final Logger log = LoggerFactory.getLogger(RefAnneeService.class);

    private final RefAnneeRepository refAnneeRepository;

    private final RefAnneeMapper refAnneeMapper;

    public RefAnneeService(RefAnneeRepository refAnneeRepository, RefAnneeMapper refAnneeMapper) {
        this.refAnneeRepository = refAnneeRepository;
        this.refAnneeMapper = refAnneeMapper;
    }

    /**
     * Save a refAnnee.
     *
     * @param refAnneeDTO the entity to save.
     * @return the persisted entity.
     */
    public RefAnneeDTO save(RefAnneeDTO refAnneeDTO) {
        log.debug("Request to save RefAnnee : {}", refAnneeDTO);
        RefAnnee refAnnee = refAnneeMapper.toEntity(refAnneeDTO);
        refAnnee = refAnneeRepository.save(refAnnee);
        return refAnneeMapper.toDto(refAnnee);
    }

    /**
     * Get all the refAnnees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RefAnneeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefAnnees");
        return refAnneeRepository.findAll(pageable)
            .map(refAnneeMapper::toDto);
    }


    /**
     * Get one refAnnee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RefAnneeDTO> findOne(Long id) {
        log.debug("Request to get RefAnnee : {}", id);
        return refAnneeRepository.findById(id)
            .map(refAnneeMapper::toDto);
    }

    /**
     * Delete the refAnnee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RefAnnee : {}", id);
        refAnneeRepository.deleteById(id);
    }
}
