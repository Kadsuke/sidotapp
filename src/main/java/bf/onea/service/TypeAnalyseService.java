package bf.onea.service;

import bf.onea.domain.TypeAnalyse;
import bf.onea.repository.TypeAnalyseRepository;
import bf.onea.service.dto.TypeAnalyseDTO;
import bf.onea.service.mapper.TypeAnalyseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeAnalyse}.
 */
@Service
@Transactional
public class TypeAnalyseService {

    private final Logger log = LoggerFactory.getLogger(TypeAnalyseService.class);

    private final TypeAnalyseRepository typeAnalyseRepository;

    private final TypeAnalyseMapper typeAnalyseMapper;

    public TypeAnalyseService(TypeAnalyseRepository typeAnalyseRepository, TypeAnalyseMapper typeAnalyseMapper) {
        this.typeAnalyseRepository = typeAnalyseRepository;
        this.typeAnalyseMapper = typeAnalyseMapper;
    }

    /**
     * Save a typeAnalyse.
     *
     * @param typeAnalyseDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeAnalyseDTO save(TypeAnalyseDTO typeAnalyseDTO) {
        log.debug("Request to save TypeAnalyse : {}", typeAnalyseDTO);
        TypeAnalyse typeAnalyse = typeAnalyseMapper.toEntity(typeAnalyseDTO);
        typeAnalyse = typeAnalyseRepository.save(typeAnalyse);
        return typeAnalyseMapper.toDto(typeAnalyse);
    }

    /**
     * Get all the typeAnalyses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeAnalyseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeAnalyses");
        return typeAnalyseRepository.findAll(pageable)
            .map(typeAnalyseMapper::toDto);
    }


    /**
     * Get one typeAnalyse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeAnalyseDTO> findOne(Long id) {
        log.debug("Request to get TypeAnalyse : {}", id);
        return typeAnalyseRepository.findById(id)
            .map(typeAnalyseMapper::toDto);
    }

    /**
     * Delete the typeAnalyse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeAnalyse : {}", id);
        typeAnalyseRepository.deleteById(id);
    }
}
