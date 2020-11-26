package bf.onea.service;

import bf.onea.domain.CategorieRessources;
import bf.onea.repository.CategorieRessourcesRepository;
import bf.onea.service.dto.CategorieRessourcesDTO;
import bf.onea.service.mapper.CategorieRessourcesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieRessources}.
 */
@Service
@Transactional
public class CategorieRessourcesService {

    private final Logger log = LoggerFactory.getLogger(CategorieRessourcesService.class);

    private final CategorieRessourcesRepository categorieRessourcesRepository;

    private final CategorieRessourcesMapper categorieRessourcesMapper;

    public CategorieRessourcesService(CategorieRessourcesRepository categorieRessourcesRepository, CategorieRessourcesMapper categorieRessourcesMapper) {
        this.categorieRessourcesRepository = categorieRessourcesRepository;
        this.categorieRessourcesMapper = categorieRessourcesMapper;
    }

    /**
     * Save a categorieRessources.
     *
     * @param categorieRessourcesDTO the entity to save.
     * @return the persisted entity.
     */
    public CategorieRessourcesDTO save(CategorieRessourcesDTO categorieRessourcesDTO) {
        log.debug("Request to save CategorieRessources : {}", categorieRessourcesDTO);
        CategorieRessources categorieRessources = categorieRessourcesMapper.toEntity(categorieRessourcesDTO);
        categorieRessources = categorieRessourcesRepository.save(categorieRessources);
        return categorieRessourcesMapper.toDto(categorieRessources);
    }

    /**
     * Get all the categorieRessources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CategorieRessourcesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieRessources");
        return categorieRessourcesRepository.findAll(pageable)
            .map(categorieRessourcesMapper::toDto);
    }


    /**
     * Get one categorieRessources by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategorieRessourcesDTO> findOne(Long id) {
        log.debug("Request to get CategorieRessources : {}", id);
        return categorieRessourcesRepository.findById(id)
            .map(categorieRessourcesMapper::toDto);
    }

    /**
     * Delete the categorieRessources by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CategorieRessources : {}", id);
        categorieRessourcesRepository.deleteById(id);
    }
}
