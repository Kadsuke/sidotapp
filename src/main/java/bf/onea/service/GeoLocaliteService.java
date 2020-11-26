package bf.onea.service;

import bf.onea.domain.GeoLocalite;
import bf.onea.repository.GeoLocaliteRepository;
import bf.onea.service.dto.GeoLocaliteDTO;
import bf.onea.service.mapper.GeoLocaliteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoLocalite}.
 */
@Service
@Transactional
public class GeoLocaliteService {

    private final Logger log = LoggerFactory.getLogger(GeoLocaliteService.class);

    private final GeoLocaliteRepository geoLocaliteRepository;

    private final GeoLocaliteMapper geoLocaliteMapper;

    public GeoLocaliteService(GeoLocaliteRepository geoLocaliteRepository, GeoLocaliteMapper geoLocaliteMapper) {
        this.geoLocaliteRepository = geoLocaliteRepository;
        this.geoLocaliteMapper = geoLocaliteMapper;
    }

    /**
     * Save a geoLocalite.
     *
     * @param geoLocaliteDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoLocaliteDTO save(GeoLocaliteDTO geoLocaliteDTO) {
        log.debug("Request to save GeoLocalite : {}", geoLocaliteDTO);
        GeoLocalite geoLocalite = geoLocaliteMapper.toEntity(geoLocaliteDTO);
        geoLocalite = geoLocaliteRepository.save(geoLocalite);
        return geoLocaliteMapper.toDto(geoLocalite);
    }

    /**
     * Get all the geoLocalites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoLocaliteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoLocalites");
        return geoLocaliteRepository.findAll(pageable)
            .map(geoLocaliteMapper::toDto);
    }


    /**
     * Get one geoLocalite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoLocaliteDTO> findOne(Long id) {
        log.debug("Request to get GeoLocalite : {}", id);
        return geoLocaliteRepository.findById(id)
            .map(geoLocaliteMapper::toDto);
    }

    /**
     * Delete the geoLocalite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoLocalite : {}", id);
        geoLocaliteRepository.deleteById(id);
    }
}
