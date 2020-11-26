package bf.onea.service;

import bf.onea.domain.RefSousDomaine;
import bf.onea.repository.RefSousDomaineRepository;
import bf.onea.service.dto.RefSousDomaineDTO;
import bf.onea.service.mapper.RefSousDomaineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RefSousDomaine}.
 */
@Service
@Transactional
public class RefSousDomaineService {

    private final Logger log = LoggerFactory.getLogger(RefSousDomaineService.class);

    private final RefSousDomaineRepository refSousDomaineRepository;

    private final RefSousDomaineMapper refSousDomaineMapper;

    public RefSousDomaineService(RefSousDomaineRepository refSousDomaineRepository, RefSousDomaineMapper refSousDomaineMapper) {
        this.refSousDomaineRepository = refSousDomaineRepository;
        this.refSousDomaineMapper = refSousDomaineMapper;
    }

    /**
     * Save a refSousDomaine.
     *
     * @param refSousDomaineDTO the entity to save.
     * @return the persisted entity.
     */
    public RefSousDomaineDTO save(RefSousDomaineDTO refSousDomaineDTO) {
        log.debug("Request to save RefSousDomaine : {}", refSousDomaineDTO);
        RefSousDomaine refSousDomaine = refSousDomaineMapper.toEntity(refSousDomaineDTO);
        refSousDomaine = refSousDomaineRepository.save(refSousDomaine);
        return refSousDomaineMapper.toDto(refSousDomaine);
    }

    /**
     * Get all the refSousDomaines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RefSousDomaineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RefSousDomaines");
        return refSousDomaineRepository.findAll(pageable)
            .map(refSousDomaineMapper::toDto);
    }


    /**
     * Get one refSousDomaine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RefSousDomaineDTO> findOne(Long id) {
        log.debug("Request to get RefSousDomaine : {}", id);
        return refSousDomaineRepository.findById(id)
            .map(refSousDomaineMapper::toDto);
    }

    /**
     * Delete the refSousDomaine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RefSousDomaine : {}", id);
        refSousDomaineRepository.deleteById(id);
    }
}
