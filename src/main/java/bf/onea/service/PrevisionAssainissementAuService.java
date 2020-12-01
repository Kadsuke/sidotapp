package bf.onea.service;

import bf.onea.domain.PrevisionAssainissementAu;
import bf.onea.repository.PrevisionAssainissementAuRepository;
import bf.onea.service.dto.PrevisionAssainissementAuDTO;
import bf.onea.service.mapper.PrevisionAssainissementAuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PrevisionAssainissementAu}.
 */
@Service
@Transactional
public class PrevisionAssainissementAuService {

    private final Logger log = LoggerFactory.getLogger(PrevisionAssainissementAuService.class);

    private final PrevisionAssainissementAuRepository previsionAssainissementAuRepository;

    private final PrevisionAssainissementAuMapper previsionAssainissementAuMapper;

    public PrevisionAssainissementAuService(PrevisionAssainissementAuRepository previsionAssainissementAuRepository, PrevisionAssainissementAuMapper previsionAssainissementAuMapper) {
        this.previsionAssainissementAuRepository = previsionAssainissementAuRepository;
        this.previsionAssainissementAuMapper = previsionAssainissementAuMapper;
    }

    /**
     * Save a previsionAssainissementAu.
     *
     * @param previsionAssainissementAuDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisionAssainissementAuDTO save(PrevisionAssainissementAuDTO previsionAssainissementAuDTO) {
        log.debug("Request to save PrevisionAssainissementAu : {}", previsionAssainissementAuDTO);
        PrevisionAssainissementAu previsionAssainissementAu = previsionAssainissementAuMapper.toEntity(previsionAssainissementAuDTO);
        previsionAssainissementAu = previsionAssainissementAuRepository.save(previsionAssainissementAu);
        return previsionAssainissementAuMapper.toDto(previsionAssainissementAu);
    }

    /**
     * Get all the previsionAssainissementAus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrevisionAssainissementAuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrevisionAssainissementAus");
        return previsionAssainissementAuRepository.findAll(pageable)
            .map(previsionAssainissementAuMapper::toDto);
    }


    /**
     * Get one previsionAssainissementAu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisionAssainissementAuDTO> findOne(Long id) {
        log.debug("Request to get PrevisionAssainissementAu : {}", id);
        return previsionAssainissementAuRepository.findById(id)
            .map(previsionAssainissementAuMapper::toDto);
    }

    /**
     * Delete the previsionAssainissementAu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisionAssainissementAu : {}", id);
        previsionAssainissementAuRepository.deleteById(id);
    }
}
