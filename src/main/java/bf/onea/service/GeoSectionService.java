package bf.onea.service;

import bf.onea.domain.GeoSection;
import bf.onea.repository.GeoSectionRepository;
import bf.onea.service.dto.GeoSectionDTO;
import bf.onea.service.mapper.GeoSectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoSection}.
 */
@Service
@Transactional
public class GeoSectionService {

    private final Logger log = LoggerFactory.getLogger(GeoSectionService.class);

    private final GeoSectionRepository geoSectionRepository;

    private final GeoSectionMapper geoSectionMapper;

    public GeoSectionService(GeoSectionRepository geoSectionRepository, GeoSectionMapper geoSectionMapper) {
        this.geoSectionRepository = geoSectionRepository;
        this.geoSectionMapper = geoSectionMapper;
    }

    /**
     * Save a geoSection.
     *
     * @param geoSectionDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoSectionDTO save(GeoSectionDTO geoSectionDTO) {
        log.debug("Request to save GeoSection : {}", geoSectionDTO);
        GeoSection geoSection = geoSectionMapper.toEntity(geoSectionDTO);
        geoSection = geoSectionRepository.save(geoSection);
        return geoSectionMapper.toDto(geoSection);
    }

    /**
     * Get all the geoSections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoSectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoSections");
        return geoSectionRepository.findAll(pageable)
            .map(geoSectionMapper::toDto);
    }


    /**
     * Get one geoSection by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoSectionDTO> findOne(Long id) {
        log.debug("Request to get GeoSection : {}", id);
        return geoSectionRepository.findById(id)
            .map(geoSectionMapper::toDto);
    }

    /**
     * Delete the geoSection by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoSection : {}", id);
        geoSectionRepository.deleteById(id);
    }
}
