package bf.onea.service;

import bf.onea.domain.ProduitChimique;
import bf.onea.repository.ProduitChimiqueRepository;
import bf.onea.service.dto.ProduitChimiqueDTO;
import bf.onea.service.mapper.ProduitChimiqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProduitChimique}.
 */
@Service
@Transactional
public class ProduitChimiqueService {

    private final Logger log = LoggerFactory.getLogger(ProduitChimiqueService.class);

    private final ProduitChimiqueRepository produitChimiqueRepository;

    private final ProduitChimiqueMapper produitChimiqueMapper;

    public ProduitChimiqueService(ProduitChimiqueRepository produitChimiqueRepository, ProduitChimiqueMapper produitChimiqueMapper) {
        this.produitChimiqueRepository = produitChimiqueRepository;
        this.produitChimiqueMapper = produitChimiqueMapper;
    }

    /**
     * Save a produitChimique.
     *
     * @param produitChimiqueDTO the entity to save.
     * @return the persisted entity.
     */
    public ProduitChimiqueDTO save(ProduitChimiqueDTO produitChimiqueDTO) {
        log.debug("Request to save ProduitChimique : {}", produitChimiqueDTO);
        ProduitChimique produitChimique = produitChimiqueMapper.toEntity(produitChimiqueDTO);
        produitChimique = produitChimiqueRepository.save(produitChimique);
        return produitChimiqueMapper.toDto(produitChimique);
    }

    /**
     * Get all the produitChimiques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProduitChimiqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProduitChimiques");
        return produitChimiqueRepository.findAll(pageable)
            .map(produitChimiqueMapper::toDto);
    }


    /**
     * Get one produitChimique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProduitChimiqueDTO> findOne(Long id) {
        log.debug("Request to get ProduitChimique : {}", id);
        return produitChimiqueRepository.findById(id)
            .map(produitChimiqueMapper::toDto);
    }

    /**
     * Delete the produitChimique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProduitChimique : {}", id);
        produitChimiqueRepository.deleteById(id);
    }
}
