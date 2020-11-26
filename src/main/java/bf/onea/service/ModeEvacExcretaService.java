package bf.onea.service;

import bf.onea.domain.ModeEvacExcreta;
import bf.onea.repository.ModeEvacExcretaRepository;
import bf.onea.service.dto.ModeEvacExcretaDTO;
import bf.onea.service.mapper.ModeEvacExcretaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModeEvacExcreta}.
 */
@Service
@Transactional
public class ModeEvacExcretaService {

    private final Logger log = LoggerFactory.getLogger(ModeEvacExcretaService.class);

    private final ModeEvacExcretaRepository modeEvacExcretaRepository;

    private final ModeEvacExcretaMapper modeEvacExcretaMapper;

    public ModeEvacExcretaService(ModeEvacExcretaRepository modeEvacExcretaRepository, ModeEvacExcretaMapper modeEvacExcretaMapper) {
        this.modeEvacExcretaRepository = modeEvacExcretaRepository;
        this.modeEvacExcretaMapper = modeEvacExcretaMapper;
    }

    /**
     * Save a modeEvacExcreta.
     *
     * @param modeEvacExcretaDTO the entity to save.
     * @return the persisted entity.
     */
    public ModeEvacExcretaDTO save(ModeEvacExcretaDTO modeEvacExcretaDTO) {
        log.debug("Request to save ModeEvacExcreta : {}", modeEvacExcretaDTO);
        ModeEvacExcreta modeEvacExcreta = modeEvacExcretaMapper.toEntity(modeEvacExcretaDTO);
        modeEvacExcreta = modeEvacExcretaRepository.save(modeEvacExcreta);
        return modeEvacExcretaMapper.toDto(modeEvacExcreta);
    }

    /**
     * Get all the modeEvacExcretas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ModeEvacExcretaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ModeEvacExcretas");
        return modeEvacExcretaRepository.findAll(pageable)
            .map(modeEvacExcretaMapper::toDto);
    }


    /**
     * Get one modeEvacExcreta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ModeEvacExcretaDTO> findOne(Long id) {
        log.debug("Request to get ModeEvacExcreta : {}", id);
        return modeEvacExcretaRepository.findById(id)
            .map(modeEvacExcretaMapper::toDto);
    }

    /**
     * Delete the modeEvacExcreta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ModeEvacExcreta : {}", id);
        modeEvacExcretaRepository.deleteById(id);
    }
}
