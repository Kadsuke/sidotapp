package bf.onea.service;

import bf.onea.domain.TypeEquipement;
import bf.onea.repository.TypeEquipementRepository;
import bf.onea.service.dto.TypeEquipementDTO;
import bf.onea.service.mapper.TypeEquipementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeEquipement}.
 */
@Service
@Transactional
public class TypeEquipementService {

    private final Logger log = LoggerFactory.getLogger(TypeEquipementService.class);

    private final TypeEquipementRepository typeEquipementRepository;

    private final TypeEquipementMapper typeEquipementMapper;

    public TypeEquipementService(TypeEquipementRepository typeEquipementRepository, TypeEquipementMapper typeEquipementMapper) {
        this.typeEquipementRepository = typeEquipementRepository;
        this.typeEquipementMapper = typeEquipementMapper;
    }

    /**
     * Save a typeEquipement.
     *
     * @param typeEquipementDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeEquipementDTO save(TypeEquipementDTO typeEquipementDTO) {
        log.debug("Request to save TypeEquipement : {}", typeEquipementDTO);
        TypeEquipement typeEquipement = typeEquipementMapper.toEntity(typeEquipementDTO);
        typeEquipement = typeEquipementRepository.save(typeEquipement);
        return typeEquipementMapper.toDto(typeEquipement);
    }

    /**
     * Get all the typeEquipements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeEquipementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeEquipements");
        return typeEquipementRepository.findAll(pageable)
            .map(typeEquipementMapper::toDto);
    }


    /**
     * Get one typeEquipement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeEquipementDTO> findOne(Long id) {
        log.debug("Request to get TypeEquipement : {}", id);
        return typeEquipementRepository.findById(id)
            .map(typeEquipementMapper::toDto);
    }

    /**
     * Delete the typeEquipement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeEquipement : {}", id);
        typeEquipementRepository.deleteById(id);
    }
}
