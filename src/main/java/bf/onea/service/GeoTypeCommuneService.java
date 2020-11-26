package bf.onea.service;

import bf.onea.domain.GeoTypeCommune;
import bf.onea.repository.GeoTypeCommuneRepository;
import bf.onea.service.dto.GeoTypeCommuneDTO;
import bf.onea.service.mapper.GeoTypeCommuneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoTypeCommune}.
 */
@Service
@Transactional
public class GeoTypeCommuneService {

    private final Logger log = LoggerFactory.getLogger(GeoTypeCommuneService.class);

    private final GeoTypeCommuneRepository geoTypeCommuneRepository;

    private final GeoTypeCommuneMapper geoTypeCommuneMapper;

    public GeoTypeCommuneService(GeoTypeCommuneRepository geoTypeCommuneRepository, GeoTypeCommuneMapper geoTypeCommuneMapper) {
        this.geoTypeCommuneRepository = geoTypeCommuneRepository;
        this.geoTypeCommuneMapper = geoTypeCommuneMapper;
    }

    /**
     * Save a geoTypeCommune.
     *
     * @param geoTypeCommuneDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoTypeCommuneDTO save(GeoTypeCommuneDTO geoTypeCommuneDTO) {
        log.debug("Request to save GeoTypeCommune : {}", geoTypeCommuneDTO);
        GeoTypeCommune geoTypeCommune = geoTypeCommuneMapper.toEntity(geoTypeCommuneDTO);
        geoTypeCommune = geoTypeCommuneRepository.save(geoTypeCommune);
        return geoTypeCommuneMapper.toDto(geoTypeCommune);
    }

    /**
     * Get all the geoTypeCommunes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoTypeCommuneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoTypeCommunes");
        return geoTypeCommuneRepository.findAll(pageable)
            .map(geoTypeCommuneMapper::toDto);
    }


    /**
     * Get one geoTypeCommune by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoTypeCommuneDTO> findOne(Long id) {
        log.debug("Request to get GeoTypeCommune : {}", id);
        return geoTypeCommuneRepository.findById(id)
            .map(geoTypeCommuneMapper::toDto);
    }

    /**
     * Delete the geoTypeCommune by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoTypeCommune : {}", id);
        geoTypeCommuneRepository.deleteById(id);
    }
}
