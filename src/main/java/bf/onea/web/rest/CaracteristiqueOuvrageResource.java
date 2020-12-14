package bf.onea.web.rest;

import bf.onea.service.CaracteristiqueOuvrageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.CaracteristiqueOuvrageDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.onea.domain.CaracteristiqueOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class CaracteristiqueOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueOuvrageResource.class);

    private static final String ENTITY_NAME = "caracteristiqueOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaracteristiqueOuvrageService caracteristiqueOuvrageService;

    public CaracteristiqueOuvrageResource(CaracteristiqueOuvrageService caracteristiqueOuvrageService) {
        this.caracteristiqueOuvrageService = caracteristiqueOuvrageService;
    }

    /**
     * {@code POST  /caracteristique-ouvrages} : Create a new caracteristiqueOuvrage.
     *
     * @param caracteristiqueOuvrageDTO the caracteristiqueOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caracteristiqueOuvrageDTO, or with status {@code 400 (Bad Request)} if the caracteristiqueOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caracteristique-ouvrages")
    public ResponseEntity<CaracteristiqueOuvrageDTO> createCaracteristiqueOuvrage(@Valid @RequestBody CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save CaracteristiqueOuvrage : {}", caracteristiqueOuvrageDTO);
        if (caracteristiqueOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new caracteristiqueOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaracteristiqueOuvrageDTO result = caracteristiqueOuvrageService.save(caracteristiqueOuvrageDTO);
        return ResponseEntity.created(new URI("/api/caracteristique-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caracteristique-ouvrages} : Updates an existing caracteristiqueOuvrage.
     *
     * @param caracteristiqueOuvrageDTO the caracteristiqueOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristiqueOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the caracteristiqueOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caracteristiqueOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caracteristique-ouvrages")
    public ResponseEntity<CaracteristiqueOuvrageDTO> updateCaracteristiqueOuvrage(@Valid @RequestBody CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update CaracteristiqueOuvrage : {}", caracteristiqueOuvrageDTO);
        if (caracteristiqueOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaracteristiqueOuvrageDTO result = caracteristiqueOuvrageService.save(caracteristiqueOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caracteristiqueOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caracteristique-ouvrages} : get all the caracteristiqueOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caracteristiqueOuvrages in body.
     */
    @GetMapping("/caracteristique-ouvrages")
    public ResponseEntity<List<CaracteristiqueOuvrageDTO>> getAllCaracteristiqueOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of CaracteristiqueOuvrages");
        Page<CaracteristiqueOuvrageDTO> page = caracteristiqueOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /caracteristique-ouvrages/:id} : get the "id" caracteristiqueOuvrage.
     *
     * @param id the id of the caracteristiqueOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caracteristiqueOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caracteristique-ouvrages/{id}")
    public ResponseEntity<CaracteristiqueOuvrageDTO> getCaracteristiqueOuvrage(@PathVariable Long id) {
        log.debug("REST request to get CaracteristiqueOuvrage : {}", id);
        Optional<CaracteristiqueOuvrageDTO> caracteristiqueOuvrageDTO = caracteristiqueOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caracteristiqueOuvrageDTO);
    }

    /**
     * {@code DELETE  /caracteristique-ouvrages/:id} : delete the "id" caracteristiqueOuvrage.
     *
     * @param id the id of the caracteristiqueOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caracteristique-ouvrages/{id}")
    public ResponseEntity<Void> deleteCaracteristiqueOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete CaracteristiqueOuvrage : {}", id);
        caracteristiqueOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
