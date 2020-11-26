package bf.onea.web.rest;

import bf.onea.service.GeuAaOuvrageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuAaOuvrageDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuAaOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class GeuAaOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(GeuAaOuvrageResource.class);

    private static final String ENTITY_NAME = "geuAaOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuAaOuvrageService geuAaOuvrageService;

    public GeuAaOuvrageResource(GeuAaOuvrageService geuAaOuvrageService) {
        this.geuAaOuvrageService = geuAaOuvrageService;
    }

    /**
     * {@code POST  /geu-aa-ouvrages} : Create a new geuAaOuvrage.
     *
     * @param geuAaOuvrageDTO the geuAaOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuAaOuvrageDTO, or with status {@code 400 (Bad Request)} if the geuAaOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-aa-ouvrages")
    public ResponseEntity<GeuAaOuvrageDTO> createGeuAaOuvrage(@RequestBody GeuAaOuvrageDTO geuAaOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save GeuAaOuvrage : {}", geuAaOuvrageDTO);
        if (geuAaOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuAaOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuAaOuvrageDTO result = geuAaOuvrageService.save(geuAaOuvrageDTO);
        return ResponseEntity.created(new URI("/api/geu-aa-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-aa-ouvrages} : Updates an existing geuAaOuvrage.
     *
     * @param geuAaOuvrageDTO the geuAaOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuAaOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the geuAaOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuAaOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-aa-ouvrages")
    public ResponseEntity<GeuAaOuvrageDTO> updateGeuAaOuvrage(@RequestBody GeuAaOuvrageDTO geuAaOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update GeuAaOuvrage : {}", geuAaOuvrageDTO);
        if (geuAaOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuAaOuvrageDTO result = geuAaOuvrageService.save(geuAaOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuAaOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-aa-ouvrages} : get all the geuAaOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuAaOuvrages in body.
     */
    @GetMapping("/geu-aa-ouvrages")
    public ResponseEntity<List<GeuAaOuvrageDTO>> getAllGeuAaOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of GeuAaOuvrages");
        Page<GeuAaOuvrageDTO> page = geuAaOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-aa-ouvrages/:id} : get the "id" geuAaOuvrage.
     *
     * @param id the id of the geuAaOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuAaOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-aa-ouvrages/{id}")
    public ResponseEntity<GeuAaOuvrageDTO> getGeuAaOuvrage(@PathVariable Long id) {
        log.debug("REST request to get GeuAaOuvrage : {}", id);
        Optional<GeuAaOuvrageDTO> geuAaOuvrageDTO = geuAaOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuAaOuvrageDTO);
    }

    /**
     * {@code DELETE  /geu-aa-ouvrages/:id} : delete the "id" geuAaOuvrage.
     *
     * @param id the id of the geuAaOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-aa-ouvrages/{id}")
    public ResponseEntity<Void> deleteGeuAaOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete GeuAaOuvrage : {}", id);
        geuAaOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
