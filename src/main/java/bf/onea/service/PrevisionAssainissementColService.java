package bf.onea.service;

import bf.onea.domain.PrevisionAssainissementCol;
import bf.onea.repository.PrevisionAssainissementColRepository;
import bf.onea.service.dto.PrevisionAssainissementColDTO;
import bf.onea.service.mapper.PrevisionAssainissementColMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PrevisionAssainissementCol}.
 */
@Service
@Transactional
public class PrevisionAssainissementColService {

    private final Logger log = LoggerFactory.getLogger(PrevisionAssainissementColService.class);

    private final PrevisionAssainissementColRepository previsionAssainissementColRepository;

    private final PrevisionAssainissementColMapper previsionAssainissementColMapper;

    public PrevisionAssainissementColService(PrevisionAssainissementColRepository previsionAssainissementColRepository, PrevisionAssainissementColMapper previsionAssainissementColMapper) {
        this.previsionAssainissementColRepository = previsionAssainissementColRepository;
        this.previsionAssainissementColMapper = previsionAssainissementColMapper;
    }

    /**
     * Save a previsionAssainissementCol.
     *
     * @param previsionAssainissementColDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisionAssainissementColDTO save(PrevisionAssainissementColDTO previsionAssainissementColDTO) {
        log.debug("Request to save PrevisionAssainissementCol : {}", previsionAssainissementColDTO);
        PrevisionAssainissementCol previsionAssainissementCol = previsionAssainissementColMapper.toEntity(previsionAssainissementColDTO);
        previsionAssainissementCol = previsionAssainissementColRepository.save(previsionAssainissementCol);
        return previsionAssainissementColMapper.toDto(previsionAssainissementCol);
    }

    /**
     * Get all the previsionAssainissementCols.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrevisionAssainissementColDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrevisionAssainissementCols");
        return previsionAssainissementColRepository.findAll(pageable)
            .map(previsionAssainissementColMapper::toDto);
    }


    /**
     * Get one previsionAssainissementCol by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisionAssainissementColDTO> findOne(Long id) {
        log.debug("Request to get PrevisionAssainissementCol : {}", id);
        return previsionAssainissementColRepository.findById(id)
            .map(previsionAssainissementColMapper::toDto);
    }

    /**
     * Delete the previsionAssainissementCol by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisionAssainissementCol : {}", id);
        previsionAssainissementColRepository.deleteById(id);
    }
}
