package bf.onea.service;

import bf.onea.domain.GeoParcelle;
import bf.onea.repository.GeoParcelleRepository;
import bf.onea.service.dto.GeoParcelleDTO;
import bf.onea.service.mapper.GeoParcelleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoParcelle}.
 */
@Service
@Transactional
public class GeoParcelleService {

    private final Logger log = LoggerFactory.getLogger(GeoParcelleService.class);

    private final GeoParcelleRepository geoParcelleRepository;

    private final GeoParcelleMapper geoParcelleMapper;

    public GeoParcelleService(GeoParcelleRepository geoParcelleRepository, GeoParcelleMapper geoParcelleMapper) {
        this.geoParcelleRepository = geoParcelleRepository;
        this.geoParcelleMapper = geoParcelleMapper;
    }

    /**
     * Save a geoParcelle.
     *
     * @param geoParcelleDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoParcelleDTO save(GeoParcelleDTO geoParcelleDTO) {
        log.debug("Request to save GeoParcelle : {}", geoParcelleDTO);
        GeoParcelle geoParcelle = geoParcelleMapper.toEntity(geoParcelleDTO);
        geoParcelle = geoParcelleRepository.save(geoParcelle);
        return geoParcelleMapper.toDto(geoParcelle);
    }

    /**
     * Get all the geoParcelles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoParcelleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoParcelles");
        return geoParcelleRepository.findAll(pageable)
            .map(geoParcelleMapper::toDto);
    }


    /**
     * Get one geoParcelle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoParcelleDTO> findOne(Long id) {
        log.debug("Request to get GeoParcelle : {}", id);
        return geoParcelleRepository.findById(id)
            .map(geoParcelleMapper::toDto);
    }

    /**
     * Delete the geoParcelle by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoParcelle : {}", id);
        geoParcelleRepository.deleteById(id);
    }
}
