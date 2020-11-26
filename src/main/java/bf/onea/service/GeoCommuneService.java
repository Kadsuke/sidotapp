package bf.onea.service;

import bf.onea.domain.GeoCommune;
import bf.onea.repository.GeoCommuneRepository;
import bf.onea.service.dto.GeoCommuneDTO;
import bf.onea.service.mapper.GeoCommuneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoCommune}.
 */
@Service
@Transactional
public class GeoCommuneService {

    private final Logger log = LoggerFactory.getLogger(GeoCommuneService.class);

    private final GeoCommuneRepository geoCommuneRepository;

    private final GeoCommuneMapper geoCommuneMapper;

    public GeoCommuneService(GeoCommuneRepository geoCommuneRepository, GeoCommuneMapper geoCommuneMapper) {
        this.geoCommuneRepository = geoCommuneRepository;
        this.geoCommuneMapper = geoCommuneMapper;
    }

    /**
     * Save a geoCommune.
     *
     * @param geoCommuneDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoCommuneDTO save(GeoCommuneDTO geoCommuneDTO) {
        log.debug("Request to save GeoCommune : {}", geoCommuneDTO);
        GeoCommune geoCommune = geoCommuneMapper.toEntity(geoCommuneDTO);
        geoCommune = geoCommuneRepository.save(geoCommune);
        return geoCommuneMapper.toDto(geoCommune);
    }

    /**
     * Get all the geoCommunes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoCommuneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoCommunes");
        return geoCommuneRepository.findAll(pageable)
            .map(geoCommuneMapper::toDto);
    }


    /**
     * Get one geoCommune by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoCommuneDTO> findOne(Long id) {
        log.debug("Request to get GeoCommune : {}", id);
        return geoCommuneRepository.findById(id)
            .map(geoCommuneMapper::toDto);
    }

    /**
     * Delete the geoCommune by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoCommune : {}", id);
        geoCommuneRepository.deleteById(id);
    }
}
