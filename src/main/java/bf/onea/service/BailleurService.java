package bf.onea.service;

import bf.onea.domain.Bailleur;
import bf.onea.repository.BailleurRepository;
import bf.onea.service.dto.BailleurDTO;
import bf.onea.service.mapper.BailleurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bailleur}.
 */
@Service
@Transactional
public class BailleurService {

    private final Logger log = LoggerFactory.getLogger(BailleurService.class);

    private final BailleurRepository bailleurRepository;

    private final BailleurMapper bailleurMapper;

    public BailleurService(BailleurRepository bailleurRepository, BailleurMapper bailleurMapper) {
        this.bailleurRepository = bailleurRepository;
        this.bailleurMapper = bailleurMapper;
    }

    /**
     * Save a bailleur.
     *
     * @param bailleurDTO the entity to save.
     * @return the persisted entity.
     */
    public BailleurDTO save(BailleurDTO bailleurDTO) {
        log.debug("Request to save Bailleur : {}", bailleurDTO);
        Bailleur bailleur = bailleurMapper.toEntity(bailleurDTO);
        bailleur = bailleurRepository.save(bailleur);
        return bailleurMapper.toDto(bailleur);
    }

    /**
     * Get all the bailleurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BailleurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bailleurs");
        return bailleurRepository.findAll(pageable)
            .map(bailleurMapper::toDto);
    }


    /**
     * Get one bailleur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BailleurDTO> findOne(Long id) {
        log.debug("Request to get Bailleur : {}", id);
        return bailleurRepository.findById(id)
            .map(bailleurMapper::toDto);
    }

    /**
     * Delete the bailleur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bailleur : {}", id);
        bailleurRepository.deleteById(id);
    }
}
