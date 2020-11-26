package bf.onea.service;

import bf.onea.domain.Tacherons;
import bf.onea.repository.TacheronsRepository;
import bf.onea.service.dto.TacheronsDTO;
import bf.onea.service.mapper.TacheronsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tacherons}.
 */
@Service
@Transactional
public class TacheronsService {

    private final Logger log = LoggerFactory.getLogger(TacheronsService.class);

    private final TacheronsRepository tacheronsRepository;

    private final TacheronsMapper tacheronsMapper;

    public TacheronsService(TacheronsRepository tacheronsRepository, TacheronsMapper tacheronsMapper) {
        this.tacheronsRepository = tacheronsRepository;
        this.tacheronsMapper = tacheronsMapper;
    }

    /**
     * Save a tacherons.
     *
     * @param tacheronsDTO the entity to save.
     * @return the persisted entity.
     */
    public TacheronsDTO save(TacheronsDTO tacheronsDTO) {
        log.debug("Request to save Tacherons : {}", tacheronsDTO);
        Tacherons tacherons = tacheronsMapper.toEntity(tacheronsDTO);
        tacherons = tacheronsRepository.save(tacherons);
        return tacheronsMapper.toDto(tacherons);
    }

    /**
     * Get all the tacherons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TacheronsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tacherons");
        return tacheronsRepository.findAll(pageable)
            .map(tacheronsMapper::toDto);
    }


    /**
     * Get one tacherons by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TacheronsDTO> findOne(Long id) {
        log.debug("Request to get Tacherons : {}", id);
        return tacheronsRepository.findById(id)
            .map(tacheronsMapper::toDto);
    }

    /**
     * Delete the tacherons by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tacherons : {}", id);
        tacheronsRepository.deleteById(id);
    }
}
