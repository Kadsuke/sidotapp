package bf.onea.service;

import bf.onea.domain.EtatEquipement;
import bf.onea.repository.EtatEquipementRepository;
import bf.onea.service.dto.EtatEquipementDTO;
import bf.onea.service.mapper.EtatEquipementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtatEquipement}.
 */
@Service
@Transactional
public class EtatEquipementService {

    private final Logger log = LoggerFactory.getLogger(EtatEquipementService.class);

    private final EtatEquipementRepository etatEquipementRepository;

    private final EtatEquipementMapper etatEquipementMapper;

    public EtatEquipementService(EtatEquipementRepository etatEquipementRepository, EtatEquipementMapper etatEquipementMapper) {
        this.etatEquipementRepository = etatEquipementRepository;
        this.etatEquipementMapper = etatEquipementMapper;
    }

    /**
     * Save a etatEquipement.
     *
     * @param etatEquipementDTO the entity to save.
     * @return the persisted entity.
     */
    public EtatEquipementDTO save(EtatEquipementDTO etatEquipementDTO) {
        log.debug("Request to save EtatEquipement : {}", etatEquipementDTO);
        EtatEquipement etatEquipement = etatEquipementMapper.toEntity(etatEquipementDTO);
        etatEquipement = etatEquipementRepository.save(etatEquipement);
        return etatEquipementMapper.toDto(etatEquipement);
    }

    /**
     * Get all the etatEquipements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtatEquipementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtatEquipements");
        return etatEquipementRepository.findAll(pageable)
            .map(etatEquipementMapper::toDto);
    }


    /**
     * Get one etatEquipement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtatEquipementDTO> findOne(Long id) {
        log.debug("Request to get EtatEquipement : {}", id);
        return etatEquipementRepository.findById(id)
            .map(etatEquipementMapper::toDto);
    }

    /**
     * Delete the etatEquipement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtatEquipement : {}", id);
        etatEquipementRepository.deleteById(id);
    }
}
