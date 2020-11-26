package bf.onea.service;

import bf.onea.domain.Macon;
import bf.onea.repository.MaconRepository;
import bf.onea.service.dto.MaconDTO;
import bf.onea.service.mapper.MaconMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Macon}.
 */
@Service
@Transactional
public class MaconService {

    private final Logger log = LoggerFactory.getLogger(MaconService.class);

    private final MaconRepository maconRepository;

    private final MaconMapper maconMapper;

    public MaconService(MaconRepository maconRepository, MaconMapper maconMapper) {
        this.maconRepository = maconRepository;
        this.maconMapper = maconMapper;
    }

    /**
     * Save a macon.
     *
     * @param maconDTO the entity to save.
     * @return the persisted entity.
     */
    public MaconDTO save(MaconDTO maconDTO) {
        log.debug("Request to save Macon : {}", maconDTO);
        Macon macon = maconMapper.toEntity(maconDTO);
        macon = maconRepository.save(macon);
        return maconMapper.toDto(macon);
    }

    /**
     * Get all the macons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MaconDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Macons");
        return maconRepository.findAll(pageable)
            .map(maconMapper::toDto);
    }


    /**
     * Get one macon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MaconDTO> findOne(Long id) {
        log.debug("Request to get Macon : {}", id);
        return maconRepository.findById(id)
            .map(maconMapper::toDto);
    }

    /**
     * Delete the macon by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Macon : {}", id);
        maconRepository.deleteById(id);
    }
}
