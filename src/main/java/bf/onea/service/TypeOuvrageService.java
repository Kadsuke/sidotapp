package bf.onea.service;

import bf.onea.domain.TypeOuvrage;
import bf.onea.repository.TypeOuvrageRepository;
import bf.onea.service.dto.TypeOuvrageDTO;
import bf.onea.service.mapper.TypeOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeOuvrage}.
 */
@Service
@Transactional
public class TypeOuvrageService {

    private final Logger log = LoggerFactory.getLogger(TypeOuvrageService.class);

    private final TypeOuvrageRepository typeOuvrageRepository;

    private final TypeOuvrageMapper typeOuvrageMapper;

    public TypeOuvrageService(TypeOuvrageRepository typeOuvrageRepository, TypeOuvrageMapper typeOuvrageMapper) {
        this.typeOuvrageRepository = typeOuvrageRepository;
        this.typeOuvrageMapper = typeOuvrageMapper;
    }

    /**
     * Save a typeOuvrage.
     *
     * @param typeOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeOuvrageDTO save(TypeOuvrageDTO typeOuvrageDTO) {
        log.debug("Request to save TypeOuvrage : {}", typeOuvrageDTO);
        TypeOuvrage typeOuvrage = typeOuvrageMapper.toEntity(typeOuvrageDTO);
        typeOuvrage = typeOuvrageRepository.save(typeOuvrage);
        return typeOuvrageMapper.toDto(typeOuvrage);
    }

    /**
     * Get all the typeOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeOuvrages");
        return typeOuvrageRepository.findAll(pageable)
            .map(typeOuvrageMapper::toDto);
    }


    /**
     * Get one typeOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeOuvrageDTO> findOne(Long id) {
        log.debug("Request to get TypeOuvrage : {}", id);
        return typeOuvrageRepository.findById(id)
            .map(typeOuvrageMapper::toDto);
    }

    /**
     * Delete the typeOuvrage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeOuvrage : {}", id);
        typeOuvrageRepository.deleteById(id);
    }
}
