package bf.onea.service;

import bf.onea.domain.PrevisionPsa;
import bf.onea.repository.PrevisionPsaRepository;
import bf.onea.service.dto.PrevisionPsaDTO;
import bf.onea.service.mapper.PrevisionPsaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PrevisionPsa}.
 */
@Service
@Transactional
public class PrevisionPsaService {

    private final Logger log = LoggerFactory.getLogger(PrevisionPsaService.class);

    private final PrevisionPsaRepository previsionPsaRepository;

    private final PrevisionPsaMapper previsionPsaMapper;

    public PrevisionPsaService(PrevisionPsaRepository previsionPsaRepository, PrevisionPsaMapper previsionPsaMapper) {
        this.previsionPsaRepository = previsionPsaRepository;
        this.previsionPsaMapper = previsionPsaMapper;
    }

    /**
     * Save a previsionPsa.
     *
     * @param previsionPsaDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisionPsaDTO save(PrevisionPsaDTO previsionPsaDTO) {
        log.debug("Request to save PrevisionPsa : {}", previsionPsaDTO);
        PrevisionPsa previsionPsa = previsionPsaMapper.toEntity(previsionPsaDTO);
        previsionPsa = previsionPsaRepository.save(previsionPsa);
        return previsionPsaMapper.toDto(previsionPsa);
    }

    /**
     * Get all the previsionPsas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrevisionPsaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PrevisionPsas");
        return previsionPsaRepository.findAll(pageable)
            .map(previsionPsaMapper::toDto);
    }


    /**
     * Get one previsionPsa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisionPsaDTO> findOne(Long id) {
        log.debug("Request to get PrevisionPsa : {}", id);
        return previsionPsaRepository.findById(id)
            .map(previsionPsaMapper::toDto);
    }

    /**
     * Delete the previsionPsa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisionPsa : {}", id);
        previsionPsaRepository.deleteById(id);
    }
}
