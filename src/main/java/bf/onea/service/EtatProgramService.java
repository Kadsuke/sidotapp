package bf.onea.service;

import bf.onea.domain.EtatProgram;
import bf.onea.repository.EtatProgramRepository;
import bf.onea.service.dto.EtatProgramDTO;
import bf.onea.service.mapper.EtatProgramMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtatProgram}.
 */
@Service
@Transactional
public class EtatProgramService {

    private final Logger log = LoggerFactory.getLogger(EtatProgramService.class);

    private final EtatProgramRepository etatProgramRepository;

    private final EtatProgramMapper etatProgramMapper;

    public EtatProgramService(EtatProgramRepository etatProgramRepository, EtatProgramMapper etatProgramMapper) {
        this.etatProgramRepository = etatProgramRepository;
        this.etatProgramMapper = etatProgramMapper;
    }

    /**
     * Save a etatProgram.
     *
     * @param etatProgramDTO the entity to save.
     * @return the persisted entity.
     */
    public EtatProgramDTO save(EtatProgramDTO etatProgramDTO) {
        log.debug("Request to save EtatProgram : {}", etatProgramDTO);
        EtatProgram etatProgram = etatProgramMapper.toEntity(etatProgramDTO);
        etatProgram = etatProgramRepository.save(etatProgram);
        return etatProgramMapper.toDto(etatProgram);
    }

    /**
     * Get all the etatPrograms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtatProgramDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtatPrograms");
        return etatProgramRepository.findAll(pageable)
            .map(etatProgramMapper::toDto);
    }


    /**
     * Get one etatProgram by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtatProgramDTO> findOne(Long id) {
        log.debug("Request to get EtatProgram : {}", id);
        return etatProgramRepository.findById(id)
            .map(etatProgramMapper::toDto);
    }

    /**
     * Delete the etatProgram by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtatProgram : {}", id);
        etatProgramRepository.deleteById(id);
    }
}
