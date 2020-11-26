package bf.onea.service;

import bf.onea.domain.TypePlainte;
import bf.onea.repository.TypePlainteRepository;
import bf.onea.service.dto.TypePlainteDTO;
import bf.onea.service.mapper.TypePlainteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypePlainte}.
 */
@Service
@Transactional
public class TypePlainteService {

    private final Logger log = LoggerFactory.getLogger(TypePlainteService.class);

    private final TypePlainteRepository typePlainteRepository;

    private final TypePlainteMapper typePlainteMapper;

    public TypePlainteService(TypePlainteRepository typePlainteRepository, TypePlainteMapper typePlainteMapper) {
        this.typePlainteRepository = typePlainteRepository;
        this.typePlainteMapper = typePlainteMapper;
    }

    /**
     * Save a typePlainte.
     *
     * @param typePlainteDTO the entity to save.
     * @return the persisted entity.
     */
    public TypePlainteDTO save(TypePlainteDTO typePlainteDTO) {
        log.debug("Request to save TypePlainte : {}", typePlainteDTO);
        TypePlainte typePlainte = typePlainteMapper.toEntity(typePlainteDTO);
        typePlainte = typePlainteRepository.save(typePlainte);
        return typePlainteMapper.toDto(typePlainte);
    }

    /**
     * Get all the typePlaintes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypePlainteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypePlaintes");
        return typePlainteRepository.findAll(pageable)
            .map(typePlainteMapper::toDto);
    }


    /**
     * Get one typePlainte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypePlainteDTO> findOne(Long id) {
        log.debug("Request to get TypePlainte : {}", id);
        return typePlainteRepository.findById(id)
            .map(typePlainteMapper::toDto);
    }

    /**
     * Delete the typePlainte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypePlainte : {}", id);
        typePlainteRepository.deleteById(id);
    }
}
