package bf.onea.service;

import bf.onea.domain.TypeHabitation;
import bf.onea.repository.TypeHabitationRepository;
import bf.onea.service.dto.TypeHabitationDTO;
import bf.onea.service.mapper.TypeHabitationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeHabitation}.
 */
@Service
@Transactional
public class TypeHabitationService {

    private final Logger log = LoggerFactory.getLogger(TypeHabitationService.class);

    private final TypeHabitationRepository typeHabitationRepository;

    private final TypeHabitationMapper typeHabitationMapper;

    public TypeHabitationService(TypeHabitationRepository typeHabitationRepository, TypeHabitationMapper typeHabitationMapper) {
        this.typeHabitationRepository = typeHabitationRepository;
        this.typeHabitationMapper = typeHabitationMapper;
    }

    /**
     * Save a typeHabitation.
     *
     * @param typeHabitationDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeHabitationDTO save(TypeHabitationDTO typeHabitationDTO) {
        log.debug("Request to save TypeHabitation : {}", typeHabitationDTO);
        TypeHabitation typeHabitation = typeHabitationMapper.toEntity(typeHabitationDTO);
        typeHabitation = typeHabitationRepository.save(typeHabitation);
        return typeHabitationMapper.toDto(typeHabitation);
    }

    /**
     * Get all the typeHabitations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeHabitationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeHabitations");
        return typeHabitationRepository.findAll(pageable)
            .map(typeHabitationMapper::toDto);
    }


    /**
     * Get one typeHabitation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeHabitationDTO> findOne(Long id) {
        log.debug("Request to get TypeHabitation : {}", id);
        return typeHabitationRepository.findById(id)
            .map(typeHabitationMapper::toDto);
    }

    /**
     * Delete the typeHabitation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeHabitation : {}", id);
        typeHabitationRepository.deleteById(id);
    }
}
