package bf.onea.service;

import bf.onea.domain.Centre;
import bf.onea.repository.CentreRepository;
import bf.onea.service.dto.CentreDTO;
import bf.onea.service.mapper.CentreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Centre}.
 */
@Service
@Transactional
public class CentreService {

    private final Logger log = LoggerFactory.getLogger(CentreService.class);

    private final CentreRepository centreRepository;

    private final CentreMapper centreMapper;

    public CentreService(CentreRepository centreRepository, CentreMapper centreMapper) {
        this.centreRepository = centreRepository;
        this.centreMapper = centreMapper;
    }

    /**
     * Save a centre.
     *
     * @param centreDTO the entity to save.
     * @return the persisted entity.
     */
    public CentreDTO save(CentreDTO centreDTO) {
        log.debug("Request to save Centre : {}", centreDTO);
        Centre centre = centreMapper.toEntity(centreDTO);
        centre = centreRepository.save(centre);
        return centreMapper.toDto(centre);
    }

    /**
     * Get all the centres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CentreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Centres");
        return centreRepository.findAll(pageable)
            .map(centreMapper::toDto);
    }



    /**
     *  Get all the centres where PrevisionAssainissementAu is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CentreDTO> findAllWherePrevisionAssainissementAuIsNull() {
        log.debug("Request to get all centres where PrevisionAssainissementAu is null");
        return StreamSupport
            .stream(centreRepository.findAll().spliterator(), false)
            .filter(centre -> centre.getPrevisionAssainissementAu() == null)
            .map(centreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the centres where PrevisionAssainissementCol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CentreDTO> findAllWherePrevisionAssainissementColIsNull() {
        log.debug("Request to get all centres where PrevisionAssainissementCol is null");
        return StreamSupport
            .stream(centreRepository.findAll().spliterator(), false)
            .filter(centre -> centre.getPrevisionAssainissementCol() == null)
            .map(centreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the centres where PrevisionPsa is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CentreDTO> findAllWherePrevisionPsaIsNull() {
        log.debug("Request to get all centres where PrevisionPsa is null");
        return StreamSupport
            .stream(centreRepository.findAll().spliterator(), false)
            .filter(centre -> centre.getPrevisionPsa() == null)
            .map(centreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one centre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CentreDTO> findOne(Long id) {
        log.debug("Request to get Centre : {}", id);
        return centreRepository.findById(id)
            .map(centreMapper::toDto);
    }

    /**
     * Delete the centre by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Centre : {}", id);
        centreRepository.deleteById(id);
    }
}
