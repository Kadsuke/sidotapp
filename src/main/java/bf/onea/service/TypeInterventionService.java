package bf.onea.service;

import bf.onea.domain.TypeIntervention;
import bf.onea.repository.TypeInterventionRepository;
import bf.onea.service.dto.TypeInterventionDTO;
import bf.onea.service.mapper.TypeInterventionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeIntervention}.
 */
@Service
@Transactional
public class TypeInterventionService {

    private final Logger log = LoggerFactory.getLogger(TypeInterventionService.class);

    private final TypeInterventionRepository typeInterventionRepository;

    private final TypeInterventionMapper typeInterventionMapper;

    public TypeInterventionService(TypeInterventionRepository typeInterventionRepository, TypeInterventionMapper typeInterventionMapper) {
        this.typeInterventionRepository = typeInterventionRepository;
        this.typeInterventionMapper = typeInterventionMapper;
    }

    /**
     * Save a typeIntervention.
     *
     * @param typeInterventionDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeInterventionDTO save(TypeInterventionDTO typeInterventionDTO) {
        log.debug("Request to save TypeIntervention : {}", typeInterventionDTO);
        TypeIntervention typeIntervention = typeInterventionMapper.toEntity(typeInterventionDTO);
        typeIntervention = typeInterventionRepository.save(typeIntervention);
        return typeInterventionMapper.toDto(typeIntervention);
    }

    /**
     * Get all the typeInterventions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeInterventionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeInterventions");
        return typeInterventionRepository.findAll(pageable)
            .map(typeInterventionMapper::toDto);
    }


    /**
     * Get one typeIntervention by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeInterventionDTO> findOne(Long id) {
        log.debug("Request to get TypeIntervention : {}", id);
        return typeInterventionRepository.findById(id)
            .map(typeInterventionMapper::toDto);
    }

    /**
     * Delete the typeIntervention by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeIntervention : {}", id);
        typeInterventionRepository.deleteById(id);
    }
}
