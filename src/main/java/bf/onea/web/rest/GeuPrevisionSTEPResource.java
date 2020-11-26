package bf.onea.web.rest;

import bf.onea.service.GeuPrevisionSTEPService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuPrevisionSTEPDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuPrevisionSTEP}.
 */
@RestController
@RequestMapping("/api")
public class GeuPrevisionSTEPResource {

    private final Logger log = LoggerFactory.getLogger(GeuPrevisionSTEPResource.class);

    private static final String ENTITY_NAME = "geuPrevisionSTEP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuPrevisionSTEPService geuPrevisionSTEPService;

    public GeuPrevisionSTEPResource(GeuPrevisionSTEPService geuPrevisionSTEPService) {
        this.geuPrevisionSTEPService = geuPrevisionSTEPService;
    }

    /**
     * {@code POST  /geu-prevision-steps} : Create a new geuPrevisionSTEP.
     *
     * @param geuPrevisionSTEPDTO the geuPrevisionSTEPDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuPrevisionSTEPDTO, or with status {@code 400 (Bad Request)} if the geuPrevisionSTEP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-prevision-steps")
    public ResponseEntity<GeuPrevisionSTEPDTO> createGeuPrevisionSTEP(@RequestBody GeuPrevisionSTEPDTO geuPrevisionSTEPDTO) throws URISyntaxException {
        log.debug("REST request to save GeuPrevisionSTEP : {}", geuPrevisionSTEPDTO);
        if (geuPrevisionSTEPDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuPrevisionSTEP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuPrevisionSTEPDTO result = geuPrevisionSTEPService.save(geuPrevisionSTEPDTO);
        return ResponseEntity.created(new URI("/api/geu-prevision-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-prevision-steps} : Updates an existing geuPrevisionSTEP.
     *
     * @param geuPrevisionSTEPDTO the geuPrevisionSTEPDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuPrevisionSTEPDTO,
     * or with status {@code 400 (Bad Request)} if the geuPrevisionSTEPDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuPrevisionSTEPDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-prevision-steps")
    public ResponseEntity<GeuPrevisionSTEPDTO> updateGeuPrevisionSTEP(@RequestBody GeuPrevisionSTEPDTO geuPrevisionSTEPDTO) throws URISyntaxException {
        log.debug("REST request to update GeuPrevisionSTEP : {}", geuPrevisionSTEPDTO);
        if (geuPrevisionSTEPDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuPrevisionSTEPDTO result = geuPrevisionSTEPService.save(geuPrevisionSTEPDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuPrevisionSTEPDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-prevision-steps} : get all the geuPrevisionSTEPS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuPrevisionSTEPS in body.
     */
    @GetMapping("/geu-prevision-steps")
    public ResponseEntity<List<GeuPrevisionSTEPDTO>> getAllGeuPrevisionSTEPS(Pageable pageable) {
        log.debug("REST request to get a page of GeuPrevisionSTEPS");
        Page<GeuPrevisionSTEPDTO> page = geuPrevisionSTEPService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-prevision-steps/:id} : get the "id" geuPrevisionSTEP.
     *
     * @param id the id of the geuPrevisionSTEPDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuPrevisionSTEPDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-prevision-steps/{id}")
    public ResponseEntity<GeuPrevisionSTEPDTO> getGeuPrevisionSTEP(@PathVariable Long id) {
        log.debug("REST request to get GeuPrevisionSTEP : {}", id);
        Optional<GeuPrevisionSTEPDTO> geuPrevisionSTEPDTO = geuPrevisionSTEPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuPrevisionSTEPDTO);
    }

    /**
     * {@code DELETE  /geu-prevision-steps/:id} : delete the "id" geuPrevisionSTEP.
     *
     * @param id the id of the geuPrevisionSTEPDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-prevision-steps/{id}")
    public ResponseEntity<Void> deleteGeuPrevisionSTEP(@PathVariable Long id) {
        log.debug("REST request to delete GeuPrevisionSTEP : {}", id);
        geuPrevisionSTEPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
