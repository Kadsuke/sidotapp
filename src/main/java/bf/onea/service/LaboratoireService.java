package bf.onea.service;

import bf.onea.domain.Laboratoire;
import bf.onea.repository.LaboratoireRepository;
import bf.onea.service.dto.LaboratoireDTO;
import bf.onea.service.mapper.LaboratoireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Laboratoire}.
 */
@Service
@Transactional
public class LaboratoireService {

    private final Logger log = LoggerFactory.getLogger(LaboratoireService.class);

    private final LaboratoireRepository laboratoireRepository;

    private final LaboratoireMapper laboratoireMapper;

    public LaboratoireService(LaboratoireRepository laboratoireRepository, LaboratoireMapper laboratoireMapper) {
        this.laboratoireRepository = laboratoireRepository;
        this.laboratoireMapper = laboratoireMapper;
    }

    /**
     * Save a laboratoire.
     *
     * @param laboratoireDTO the entity to save.
     * @return the persisted entity.
     */
    public LaboratoireDTO save(LaboratoireDTO laboratoireDTO) {
        log.debug("Request to save Laboratoire : {}", laboratoireDTO);
        Laboratoire laboratoire = laboratoireMapper.toEntity(laboratoireDTO);
        laboratoire = laboratoireRepository.save(laboratoire);
        return laboratoireMapper.toDto(laboratoire);
    }

    /**
     * Get all the laboratoires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LaboratoireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Laboratoires");
        return laboratoireRepository.findAll(pageable)
            .map(laboratoireMapper::toDto);
    }


    /**
     * Get one laboratoire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LaboratoireDTO> findOne(Long id) {
        log.debug("Request to get Laboratoire : {}", id);
        return laboratoireRepository.findById(id)
            .map(laboratoireMapper::toDto);
    }

    /**
     * Delete the laboratoire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Laboratoire : {}", id);
        laboratoireRepository.deleteById(id);
    }
}
