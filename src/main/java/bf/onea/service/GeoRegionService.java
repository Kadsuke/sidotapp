package bf.onea.service;

import bf.onea.domain.GeoRegion;
import bf.onea.repository.GeoRegionRepository;
import bf.onea.service.dto.GeoRegionDTO;
import bf.onea.service.mapper.GeoRegionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoRegion}.
 */
@Service
@Transactional
public class GeoRegionService {

    private final Logger log = LoggerFactory.getLogger(GeoRegionService.class);

    private final GeoRegionRepository geoRegionRepository;

    private final GeoRegionMapper geoRegionMapper;

    public GeoRegionService(GeoRegionRepository geoRegionRepository, GeoRegionMapper geoRegionMapper) {
        this.geoRegionRepository = geoRegionRepository;
        this.geoRegionMapper = geoRegionMapper;
    }

    /**
     * Save a geoRegion.
     *
     * @param geoRegionDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoRegionDTO save(GeoRegionDTO geoRegionDTO) {
        log.debug("Request to save GeoRegion : {}", geoRegionDTO);
        GeoRegion geoRegion = geoRegionMapper.toEntity(geoRegionDTO);
        geoRegion = geoRegionRepository.save(geoRegion);
        return geoRegionMapper.toDto(geoRegion);
    }

    /**
     * Get all the geoRegions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoRegionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoRegions");
        return geoRegionRepository.findAll(pageable)
            .map(geoRegionMapper::toDto);
    }


    /**
     * Get one geoRegion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoRegionDTO> findOne(Long id) {
        log.debug("Request to get GeoRegion : {}", id);
        return geoRegionRepository.findById(id)
            .map(geoRegionMapper::toDto);
    }

    /**
     * Delete the geoRegion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoRegion : {}", id);
        geoRegionRepository.deleteById(id);
    }
}
