package bf.onea.service;

import bf.onea.domain.TypeBeneficiaire;
import bf.onea.repository.TypeBeneficiaireRepository;
import bf.onea.service.dto.TypeBeneficiaireDTO;
import bf.onea.service.mapper.TypeBeneficiaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeBeneficiaire}.
 */
@Service
@Transactional
public class TypeBeneficiaireService {

    private final Logger log = LoggerFactory.getLogger(TypeBeneficiaireService.class);

    private final TypeBeneficiaireRepository typeBeneficiaireRepository;

    private final TypeBeneficiaireMapper typeBeneficiaireMapper;

    public TypeBeneficiaireService(TypeBeneficiaireRepository typeBeneficiaireRepository, TypeBeneficiaireMapper typeBeneficiaireMapper) {
        this.typeBeneficiaireRepository = typeBeneficiaireRepository;
        this.typeBeneficiaireMapper = typeBeneficiaireMapper;
    }

    /**
     * Save a typeBeneficiaire.
     *
     * @param typeBeneficiaireDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeBeneficiaireDTO save(TypeBeneficiaireDTO typeBeneficiaireDTO) {
        log.debug("Request to save TypeBeneficiaire : {}", typeBeneficiaireDTO);
        TypeBeneficiaire typeBeneficiaire = typeBeneficiaireMapper.toEntity(typeBeneficiaireDTO);
        typeBeneficiaire = typeBeneficiaireRepository.save(typeBeneficiaire);
        return typeBeneficiaireMapper.toDto(typeBeneficiaire);
    }

    /**
     * Get all the typeBeneficiaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeBeneficiaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeBeneficiaires");
        return typeBeneficiaireRepository.findAll(pageable)
            .map(typeBeneficiaireMapper::toDto);
    }


    /**
     * Get one typeBeneficiaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeBeneficiaireDTO> findOne(Long id) {
        log.debug("Request to get TypeBeneficiaire : {}", id);
        return typeBeneficiaireRepository.findById(id)
            .map(typeBeneficiaireMapper::toDto);
    }

    /**
     * Delete the typeBeneficiaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeBeneficiaire : {}", id);
        typeBeneficiaireRepository.deleteById(id);
    }
}
