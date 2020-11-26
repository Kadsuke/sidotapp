package bf.onea.service;

import bf.onea.domain.ModeEvacuationEauUsee;
import bf.onea.repository.ModeEvacuationEauUseeRepository;
import bf.onea.service.dto.ModeEvacuationEauUseeDTO;
import bf.onea.service.mapper.ModeEvacuationEauUseeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModeEvacuationEauUsee}.
 */
@Service
@Transactional
public class ModeEvacuationEauUseeService {

    private final Logger log = LoggerFactory.getLogger(ModeEvacuationEauUseeService.class);

    private final ModeEvacuationEauUseeRepository modeEvacuationEauUseeRepository;

    private final ModeEvacuationEauUseeMapper modeEvacuationEauUseeMapper;

    public ModeEvacuationEauUseeService(ModeEvacuationEauUseeRepository modeEvacuationEauUseeRepository, ModeEvacuationEauUseeMapper modeEvacuationEauUseeMapper) {
        this.modeEvacuationEauUseeRepository = modeEvacuationEauUseeRepository;
        this.modeEvacuationEauUseeMapper = modeEvacuationEauUseeMapper;
    }

    /**
     * Save a modeEvacuationEauUsee.
     *
     * @param modeEvacuationEauUseeDTO the entity to save.
     * @return the persisted entity.
     */
    public ModeEvacuationEauUseeDTO save(ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO) {
        log.debug("Request to save ModeEvacuationEauUsee : {}", modeEvacuationEauUseeDTO);
        ModeEvacuationEauUsee modeEvacuationEauUsee = modeEvacuationEauUseeMapper.toEntity(modeEvacuationEauUseeDTO);
        modeEvacuationEauUsee = modeEvacuationEauUseeRepository.save(modeEvacuationEauUsee);
        return modeEvacuationEauUseeMapper.toDto(modeEvacuationEauUsee);
    }

    /**
     * Get all the modeEvacuationEauUsees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ModeEvacuationEauUseeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ModeEvacuationEauUsees");
        return modeEvacuationEauUseeRepository.findAll(pageable)
            .map(modeEvacuationEauUseeMapper::toDto);
    }


    /**
     * Get one modeEvacuationEauUsee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ModeEvacuationEauUseeDTO> findOne(Long id) {
        log.debug("Request to get ModeEvacuationEauUsee : {}", id);
        return modeEvacuationEauUseeRepository.findById(id)
            .map(modeEvacuationEauUseeMapper::toDto);
    }

    /**
     * Delete the modeEvacuationEauUsee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ModeEvacuationEauUsee : {}", id);
        modeEvacuationEauUseeRepository.deleteById(id);
    }
}
