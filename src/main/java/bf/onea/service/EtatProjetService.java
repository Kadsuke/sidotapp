package bf.onea.service;

import bf.onea.domain.EtatProjet;
import bf.onea.repository.EtatProjetRepository;
import bf.onea.service.dto.EtatProjetDTO;
import bf.onea.service.mapper.EtatProjetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtatProjet}.
 */
@Service
@Transactional
public class EtatProjetService {

    private final Logger log = LoggerFactory.getLogger(EtatProjetService.class);

    private final EtatProjetRepository etatProjetRepository;

    private final EtatProjetMapper etatProjetMapper;

    public EtatProjetService(EtatProjetRepository etatProjetRepository, EtatProjetMapper etatProjetMapper) {
        this.etatProjetRepository = etatProjetRepository;
        this.etatProjetMapper = etatProjetMapper;
    }

    /**
     * Save a etatProjet.
     *
     * @param etatProjetDTO the entity to save.
     * @return the persisted entity.
     */
    public EtatProjetDTO save(EtatProjetDTO etatProjetDTO) {
        log.debug("Request to save EtatProjet : {}", etatProjetDTO);
        EtatProjet etatProjet = etatProjetMapper.toEntity(etatProjetDTO);
        etatProjet = etatProjetRepository.save(etatProjet);
        return etatProjetMapper.toDto(etatProjet);
    }

    /**
     * Get all the etatProjets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtatProjetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtatProjets");
        return etatProjetRepository.findAll(pageable)
            .map(etatProjetMapper::toDto);
    }


    /**
     * Get one etatProjet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtatProjetDTO> findOne(Long id) {
        log.debug("Request to get EtatProjet : {}", id);
        return etatProjetRepository.findById(id)
            .map(etatProjetMapper::toDto);
    }

    /**
     * Delete the etatProjet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtatProjet : {}", id);
        etatProjetRepository.deleteById(id);
    }
}
