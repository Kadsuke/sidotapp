package bf.onea.service;

import bf.onea.domain.EtatOuvrage;
import bf.onea.repository.EtatOuvrageRepository;
import bf.onea.service.dto.EtatOuvrageDTO;
import bf.onea.service.mapper.EtatOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtatOuvrage}.
 */
@Service
@Transactional
public class EtatOuvrageService {

    private final Logger log = LoggerFactory.getLogger(EtatOuvrageService.class);

    private final EtatOuvrageRepository etatOuvrageRepository;

    private final EtatOuvrageMapper etatOuvrageMapper;

    public EtatOuvrageService(EtatOuvrageRepository etatOuvrageRepository, EtatOuvrageMapper etatOuvrageMapper) {
        this.etatOuvrageRepository = etatOuvrageRepository;
        this.etatOuvrageMapper = etatOuvrageMapper;
    }

    /**
     * Save a etatOuvrage.
     *
     * @param etatOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    public EtatOuvrageDTO save(EtatOuvrageDTO etatOuvrageDTO) {
        log.debug("Request to save EtatOuvrage : {}", etatOuvrageDTO);
        EtatOuvrage etatOuvrage = etatOuvrageMapper.toEntity(etatOuvrageDTO);
        etatOuvrage = etatOuvrageRepository.save(etatOuvrage);
        return etatOuvrageMapper.toDto(etatOuvrage);
    }

    /**
     * Get all the etatOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtatOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtatOuvrages");
        return etatOuvrageRepository.findAll(pageable)
            .map(etatOuvrageMapper::toDto);
    }


    /**
     * Get one etatOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtatOuvrageDTO> findOne(Long id) {
        log.debug("Request to get EtatOuvrage : {}", id);
        return etatOuvrageRepository.findById(id)
            .map(etatOuvrageMapper::toDto);
    }

    /**
     * Delete the etatOuvrage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtatOuvrage : {}", id);
        etatOuvrageRepository.deleteById(id);
    }
}
