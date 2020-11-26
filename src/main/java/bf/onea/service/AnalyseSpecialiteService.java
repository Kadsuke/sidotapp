package bf.onea.service;

import bf.onea.domain.AnalyseSpecialite;
import bf.onea.repository.AnalyseSpecialiteRepository;
import bf.onea.service.dto.AnalyseSpecialiteDTO;
import bf.onea.service.mapper.AnalyseSpecialiteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnalyseSpecialite}.
 */
@Service
@Transactional
public class AnalyseSpecialiteService {

    private final Logger log = LoggerFactory.getLogger(AnalyseSpecialiteService.class);

    private final AnalyseSpecialiteRepository analyseSpecialiteRepository;

    private final AnalyseSpecialiteMapper analyseSpecialiteMapper;

    public AnalyseSpecialiteService(AnalyseSpecialiteRepository analyseSpecialiteRepository, AnalyseSpecialiteMapper analyseSpecialiteMapper) {
        this.analyseSpecialiteRepository = analyseSpecialiteRepository;
        this.analyseSpecialiteMapper = analyseSpecialiteMapper;
    }

    /**
     * Save a analyseSpecialite.
     *
     * @param analyseSpecialiteDTO the entity to save.
     * @return the persisted entity.
     */
    public AnalyseSpecialiteDTO save(AnalyseSpecialiteDTO analyseSpecialiteDTO) {
        log.debug("Request to save AnalyseSpecialite : {}", analyseSpecialiteDTO);
        AnalyseSpecialite analyseSpecialite = analyseSpecialiteMapper.toEntity(analyseSpecialiteDTO);
        analyseSpecialite = analyseSpecialiteRepository.save(analyseSpecialite);
        return analyseSpecialiteMapper.toDto(analyseSpecialite);
    }

    /**
     * Get all the analyseSpecialites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AnalyseSpecialiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnalyseSpecialites");
        return analyseSpecialiteRepository.findAll(pageable)
            .map(analyseSpecialiteMapper::toDto);
    }


    /**
     * Get one analyseSpecialite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnalyseSpecialiteDTO> findOne(Long id) {
        log.debug("Request to get AnalyseSpecialite : {}", id);
        return analyseSpecialiteRepository.findById(id)
            .map(analyseSpecialiteMapper::toDto);
    }

    /**
     * Delete the analyseSpecialite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AnalyseSpecialite : {}", id);
        analyseSpecialiteRepository.deleteById(id);
    }
}
