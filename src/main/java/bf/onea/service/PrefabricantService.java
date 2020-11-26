package bf.onea.service;

import bf.onea.domain.Prefabricant;
import bf.onea.repository.PrefabricantRepository;
import bf.onea.service.dto.PrefabricantDTO;
import bf.onea.service.mapper.PrefabricantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Prefabricant}.
 */
@Service
@Transactional
public class PrefabricantService {

    private final Logger log = LoggerFactory.getLogger(PrefabricantService.class);

    private final PrefabricantRepository prefabricantRepository;

    private final PrefabricantMapper prefabricantMapper;

    public PrefabricantService(PrefabricantRepository prefabricantRepository, PrefabricantMapper prefabricantMapper) {
        this.prefabricantRepository = prefabricantRepository;
        this.prefabricantMapper = prefabricantMapper;
    }

    /**
     * Save a prefabricant.
     *
     * @param prefabricantDTO the entity to save.
     * @return the persisted entity.
     */
    public PrefabricantDTO save(PrefabricantDTO prefabricantDTO) {
        log.debug("Request to save Prefabricant : {}", prefabricantDTO);
        Prefabricant prefabricant = prefabricantMapper.toEntity(prefabricantDTO);
        prefabricant = prefabricantRepository.save(prefabricant);
        return prefabricantMapper.toDto(prefabricant);
    }

    /**
     * Get all the prefabricants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrefabricantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prefabricants");
        return prefabricantRepository.findAll(pageable)
            .map(prefabricantMapper::toDto);
    }


    /**
     * Get one prefabricant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrefabricantDTO> findOne(Long id) {
        log.debug("Request to get Prefabricant : {}", id);
        return prefabricantRepository.findById(id)
            .map(prefabricantMapper::toDto);
    }

    /**
     * Delete the prefabricant by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Prefabricant : {}", id);
        prefabricantRepository.deleteById(id);
    }
}
