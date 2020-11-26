package bf.onea.web.rest;

import bf.onea.service.EtatOuvrageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.EtatOuvrageDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.onea.domain.EtatOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class EtatOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(EtatOuvrageResource.class);

    private static final String ENTITY_NAME = "etatOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatOuvrageService etatOuvrageService;

    public EtatOuvrageResource(EtatOuvrageService etatOuvrageService) {
        this.etatOuvrageService = etatOuvrageService;
    }

    /**
     * {@code POST  /etat-ouvrages} : Create a new etatOuvrage.
     *
     * @param etatOuvrageDTO the etatOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatOuvrageDTO, or with status {@code 400 (Bad Request)} if the etatOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-ouvrages")
    public ResponseEntity<EtatOuvrageDTO> createEtatOuvrage(@RequestBody EtatOuvrageDTO etatOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save EtatOuvrage : {}", etatOuvrageDTO);
        if (etatOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatOuvrageDTO result = etatOuvrageService.save(etatOuvrageDTO);
        return ResponseEntity.created(new URI("/api/etat-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-ouvrages} : Updates an existing etatOuvrage.
     *
     * @param etatOuvrageDTO the etatOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the etatOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-ouvrages")
    public ResponseEntity<EtatOuvrageDTO> updateEtatOuvrage(@RequestBody EtatOuvrageDTO etatOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update EtatOuvrage : {}", etatOuvrageDTO);
        if (etatOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatOuvrageDTO result = etatOuvrageService.save(etatOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-ouvrages} : get all the etatOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatOuvrages in body.
     */
    @GetMapping("/etat-ouvrages")
    public ResponseEntity<List<EtatOuvrageDTO>> getAllEtatOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of EtatOuvrages");
        Page<EtatOuvrageDTO> page = etatOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etat-ouvrages/:id} : get the "id" etatOuvrage.
     *
     * @param id the id of the etatOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-ouvrages/{id}")
    public ResponseEntity<EtatOuvrageDTO> getEtatOuvrage(@PathVariable Long id) {
        log.debug("REST request to get EtatOuvrage : {}", id);
        Optional<EtatOuvrageDTO> etatOuvrageDTO = etatOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatOuvrageDTO);
    }

    /**
     * {@code DELETE  /etat-ouvrages/:id} : delete the "id" etatOuvrage.
     *
     * @param id the id of the etatOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-ouvrages/{id}")
    public ResponseEntity<Void> deleteEtatOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete EtatOuvrage : {}", id);
        etatOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
