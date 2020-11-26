package bf.onea.service;

import bf.onea.domain.GeoProvince;
import bf.onea.repository.GeoProvinceRepository;
import bf.onea.service.dto.GeoProvinceDTO;
import bf.onea.service.mapper.GeoProvinceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoProvince}.
 */
@Service
@Transactional
public class GeoProvinceService {

    private final Logger log = LoggerFactory.getLogger(GeoProvinceService.class);

    private final GeoProvinceRepository geoProvinceRepository;

    private final GeoProvinceMapper geoProvinceMapper;

    public GeoProvinceService(GeoProvinceRepository geoProvinceRepository, GeoProvinceMapper geoProvinceMapper) {
        this.geoProvinceRepository = geoProvinceRepository;
        this.geoProvinceMapper = geoProvinceMapper;
    }

    /**
     * Save a geoProvince.
     *
     * @param geoProvinceDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoProvinceDTO save(GeoProvinceDTO geoProvinceDTO) {
        log.debug("Request to save GeoProvince : {}", geoProvinceDTO);
        GeoProvince geoProvince = geoProvinceMapper.toEntity(geoProvinceDTO);
        geoProvince = geoProvinceRepository.save(geoProvince);
        return geoProvinceMapper.toDto(geoProvince);
    }

    /**
     * Get all the geoProvinces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoProvinceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoProvinces");
        return geoProvinceRepository.findAll(pageable)
            .map(geoProvinceMapper::toDto);
    }


    /**
     * Get one geoProvince by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoProvinceDTO> findOne(Long id) {
        log.debug("Request to get GeoProvince : {}", id);
        return geoProvinceRepository.findById(id)
            .map(geoProvinceMapper::toDto);
    }

    /**
     * Delete the geoProvince by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoProvince : {}", id);
        geoProvinceRepository.deleteById(id);
    }
}
