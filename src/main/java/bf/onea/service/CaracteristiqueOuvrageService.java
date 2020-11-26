package bf.onea.service;

import bf.onea.domain.CaracteristiqueOuvrage;
import bf.onea.repository.CaracteristiqueOuvrageRepository;
import bf.onea.service.dto.CaracteristiqueOuvrageDTO;
import bf.onea.service.mapper.CaracteristiqueOuvrageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaracteristiqueOuvrage}.
 */
@Service
@Transactional
public class CaracteristiqueOuvrageService {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueOuvrageService.class);

    private final CaracteristiqueOuvrageRepository caracteristiqueOuvrageRepository;

    private final CaracteristiqueOuvrageMapper caracteristiqueOuvrageMapper;

    public CaracteristiqueOuvrageService(CaracteristiqueOuvrageRepository caracteristiqueOuvrageRepository, CaracteristiqueOuvrageMapper caracteristiqueOuvrageMapper) {
        this.caracteristiqueOuvrageRepository = caracteristiqueOuvrageRepository;
        this.caracteristiqueOuvrageMapper = caracteristiqueOuvrageMapper;
    }

    /**
     * Save a caracteristiqueOuvrage.
     *
     * @param caracteristiqueOuvrageDTO the entity to save.
     * @return the persisted entity.
     */
    public CaracteristiqueOuvrageDTO save(CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO) {
        log.debug("Request to save CaracteristiqueOuvrage : {}", caracteristiqueOuvrageDTO);
        CaracteristiqueOuvrage caracteristiqueOuvrage = caracteristiqueOuvrageMapper.toEntity(caracteristiqueOuvrageDTO);
        caracteristiqueOuvrage = caracteristiqueOuvrageRepository.save(caracteristiqueOuvrage);
        return caracteristiqueOuvrageMapper.toDto(caracteristiqueOuvrage);
    }

    /**
     * Get all the caracteristiqueOuvrages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CaracteristiqueOuvrageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaracteristiqueOuvrages");
        return caracteristiqueOuvrageRepository.findAll(pageable)
            .map(caracteristiqueOuvrageMapper::toDto);
    }


    /**
     * Get one caracteristiqueOuvrage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CaracteristiqueOuvrageDTO> findOne(Long id) {
        log.debug("Request to get CaracteristiqueOuvrage : {}", id);
        return caracteristiqueOuvrageRepository.findById(id)
            .map(caracteristiqueOuvrageMapper::toDto);
    }

    /**
     * Delete the caracteristiqueOuvrage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CaracteristiqueOuvrage : {}", id);
        caracteristiqueOuvrageRepository.deleteById(id);
    }
}
