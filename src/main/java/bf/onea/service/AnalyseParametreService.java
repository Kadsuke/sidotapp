package bf.onea.service;

import bf.onea.domain.AnalyseParametre;
import bf.onea.repository.AnalyseParametreRepository;
import bf.onea.service.dto.AnalyseParametreDTO;
import bf.onea.service.mapper.AnalyseParametreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnalyseParametre}.
 */
@Service
@Transactional
public class AnalyseParametreService {

    private final Logger log = LoggerFactory.getLogger(AnalyseParametreService.class);

    private final AnalyseParametreRepository analyseParametreRepository;

    private final AnalyseParametreMapper analyseParametreMapper;

    public AnalyseParametreService(AnalyseParametreRepository analyseParametreRepository, AnalyseParametreMapper analyseParametreMapper) {
        this.analyseParametreRepository = analyseParametreRepository;
        this.analyseParametreMapper = analyseParametreMapper;
    }

    /**
     * Save a analyseParametre.
     *
     * @param analyseParametreDTO the entity to save.
     * @return the persisted entity.
     */
    public AnalyseParametreDTO save(AnalyseParametreDTO analyseParametreDTO) {
        log.debug("Request to save AnalyseParametre : {}", analyseParametreDTO);
        AnalyseParametre analyseParametre = analyseParametreMapper.toEntity(analyseParametreDTO);
        analyseParametre = analyseParametreRepository.save(analyseParametre);
        return analyseParametreMapper.toDto(analyseParametre);
    }

    /**
     * Get all the analyseParametres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AnalyseParametreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyseParametres");
        return analyseParametreRepository.findAll(pageable)
            .map(analyseParametreMapper::toDto);
    }


    /**
     * Get one analyseParametre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnalyseParametreDTO> findOne(Long id) {
        log.debug("Request to get AnalyseParametre : {}", id);
        return analyseParametreRepository.findById(id)
            .map(analyseParametreMapper::toDto);
    }

    /**
     * Delete the analyseParametre by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnalyseParametre : {}", id);
        analyseParametreRepository.deleteById(id);
    }
}
