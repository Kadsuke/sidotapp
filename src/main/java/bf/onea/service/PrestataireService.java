package bf.onea.service;

import bf.onea.domain.Prestataire;
import bf.onea.repository.PrestataireRepository;
import bf.onea.service.dto.PrestataireDTO;
import bf.onea.service.mapper.PrestataireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Prestataire}.
 */
@Service
@Transactional
public class PrestataireService {

    private final Logger log = LoggerFactory.getLogger(PrestataireService.class);

    private final PrestataireRepository prestataireRepository;

    private final PrestataireMapper prestataireMapper;

    public PrestataireService(PrestataireRepository prestataireRepository, PrestataireMapper prestataireMapper) {
        this.prestataireRepository = prestataireRepository;
        this.prestataireMapper = prestataireMapper;
    }

    /**
     * Save a prestataire.
     *
     * @param prestataireDTO the entity to save.
     * @return the persisted entity.
     */
    public PrestataireDTO save(PrestataireDTO prestataireDTO) {
        log.debug("Request to save Prestataire : {}", prestataireDTO);
        Prestataire prestataire = prestataireMapper.toEntity(prestataireDTO);
        prestataire = prestataireRepository.save(prestataire);
        return prestataireMapper.toDto(prestataire);
    }

    /**
     * Get all the prestataires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrestataireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prestataires");
        return prestataireRepository.findAll(pageable)
            .map(prestataireMapper::toDto);
    }


    /**
     * Get one prestataire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrestataireDTO> findOne(Long id) {
        log.debug("Request to get Prestataire : {}", id);
        return prestataireRepository.findById(id)
            .map(prestataireMapper::toDto);
    }

    /**
     * Delete the prestataire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Prestataire : {}", id);
        prestataireRepository.deleteById(id);
    }
}
