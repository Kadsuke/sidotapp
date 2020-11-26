package bf.onea.service;

import bf.onea.domain.GeoSecteur;
import bf.onea.repository.GeoSecteurRepository;
import bf.onea.service.dto.GeoSecteurDTO;
import bf.onea.service.mapper.GeoSecteurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GeoSecteur}.
 */
@Service
@Transactional
public class GeoSecteurService {

    private final Logger log = LoggerFactory.getLogger(GeoSecteurService.class);

    private final GeoSecteurRepository geoSecteurRepository;

    private final GeoSecteurMapper geoSecteurMapper;

    public GeoSecteurService(GeoSecteurRepository geoSecteurRepository, GeoSecteurMapper geoSecteurMapper) {
        this.geoSecteurRepository = geoSecteurRepository;
        this.geoSecteurMapper = geoSecteurMapper;
    }

    /**
     * Save a geoSecteur.
     *
     * @param geoSecteurDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoSecteurDTO save(GeoSecteurDTO geoSecteurDTO) {
        log.debug("Request to save GeoSecteur : {}", geoSecteurDTO);
        GeoSecteur geoSecteur = geoSecteurMapper.toEntity(geoSecteurDTO);
        geoSecteur = geoSecteurRepository.save(geoSecteur);
        return geoSecteurMapper.toDto(geoSecteur);
    }

    /**
     * Get all the geoSecteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GeoSecteurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GeoSecteurs");
        return geoSecteurRepository.findAll(pageable)
            .map(geoSecteurMapper::toDto);
    }


    /**
     * Get one geoSecteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoSecteurDTO> findOne(Long id) {
        log.debug("Request to get GeoSecteur : {}", id);
        return geoSecteurRepository.findById(id)
            .map(geoSecteurMapper::toDto);
    }

    /**
     * Delete the geoSecteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoSecteur : {}", id);
        geoSecteurRepository.deleteById(id);
    }
}
