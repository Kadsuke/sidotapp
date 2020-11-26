package bf.onea.service;

import bf.onea.domain.NatureOuvrage;
import bf.onea.repository.NatureOuvrageRepository;
import bf.onea.service.dto.NatureOuvrageDTO;
import bf.onea.service.mapper.NatureOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NatureOuvrage}.
 */
@Service
@Transactional
public class NatureOuvrageService {

    private final Logger log = LoggerFactory.getLogger(NatureOuvrageService.class);

    private final NatureOuvrageRepository natureOuvrageRepository;

    private final NatureOuvrageMapper natureOuvrageMapper;

    public NatureOuvrageService(NatureOuvrageRepository natureOuvrageRepository, NatureOuvrageMapper natureOuvrageMapper) {
        this.natureOuvrageRepository = natureOuvrageRepository;
        this.natureOuvrageMapper = natureOuvrageMapper;
    }

    /**
     * Save a natureOuvrage.
     *
     * @param natureOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    public NatureOuvrageDTO save(NatureOuvrageDTO natureOuvrageDTO) {
        log.debug("Request to save NatureOuvrage : {}", natureOuvrageDTO);
        NatureOuvrage natureOuvrage = natureOuvrageMapper.toEntity(natureOuvrageDTO);
        natureOuvrage = natureOuvrageRepository.save(natureOuvrage);
        return natureOuvrageMapper.toDto(natureOuvrage);
    }

    /**
     * Get all the natureOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NatureOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NatureOuvrages");
        return natureOuvrageRepository.findAll(pageable)
            .map(natureOuvrageMapper::toDto);
    }


    /**
     * Get one natureOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NatureOuvrageDTO> findOne(Long id) {
        log.debug("Request to get NatureOuvrage : {}", id);
        return natureOuvrageRepository.findById(id)
            .map(natureOuvrageMapper::toDto);
    }

    /**
     * Delete the natureOuvrage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NatureOuvrage : {}", id);
        natureOuvrageRepository.deleteById(id);
    }
}
