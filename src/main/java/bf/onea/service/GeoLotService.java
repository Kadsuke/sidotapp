package bf.onea.service;

import bf.onea.domain.GeoLot;
import bf.onea.repository.GeoLotRepository;
import bf.onea.service.dto.GeoLotDTO;
import bf.onea.service.mapper.GeoLotMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoLot}.
 */
@Service
@Transactional
public class GeoLotService {

    private final Logger log = LoggerFactory.getLogger(GeoLotService.class);

    private final GeoLotRepository geoLotRepository;

    private final GeoLotMapper geoLotMapper;

    public GeoLotService(GeoLotRepository geoLotRepository, GeoLotMapper geoLotMapper) {
        this.geoLotRepository = geoLotRepository;
        this.geoLotMapper = geoLotMapper;
    }

    /**
     * Save a geoLot.
     *
     * @param geoLotDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoLotDTO save(GeoLotDTO geoLotDTO) {
        log.debug("Request to save GeoLot : {}", geoLotDTO);
        GeoLot geoLot = geoLotMapper.toEntity(geoLotDTO);
        geoLot = geoLotRepository.save(geoLot);
        return geoLotMapper.toDto(geoLot);
    }

    /**
     * Get all the geoLots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoLotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoLots");
        return geoLotRepository.findAll(pageable)
            .map(geoLotMapper::toDto);
    }


    /**
     * Get one geoLot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoLotDTO> findOne(Long id) {
        log.debug("Request to get GeoLot : {}", id);
        return geoLotRepository.findById(id)
            .map(geoLotMapper::toDto);
    }

    /**
     * Delete the geoLot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoLot : {}", id);
        geoLotRepository.deleteById(id);
    }
}
