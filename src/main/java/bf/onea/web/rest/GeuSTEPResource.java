package bf.onea.web.rest;

import bf.onea.service.GeuSTEPService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuSTEPDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuSTEP}.
 */
@RestController
@RequestMapping("/api")
public class GeuSTEPResource {

    private final Logger log = LoggerFactory.getLogger(GeuSTEPResource.class);

    private static final String ENTITY_NAME = "geuSTEP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuSTEPService geuSTEPService;

    public GeuSTEPResource(GeuSTEPService geuSTEPService) {
        this.geuSTEPService = geuSTEPService;
    }

    /**
     * {@code POST  /geu-steps} : Create a new geuSTEP.
     *
     * @param geuSTEPDTO the geuSTEPDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuSTEPDTO, or with status {@code 400 (Bad Request)} if the geuSTEP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-steps")
    public ResponseEntity<GeuSTEPDTO> createGeuSTEP(@RequestBody GeuSTEPDTO geuSTEPDTO) throws URISyntaxException {
        log.debug("REST request to save GeuSTEP : {}", geuSTEPDTO);
        if (geuSTEPDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuSTEP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuSTEPDTO result = geuSTEPService.save(geuSTEPDTO);
        return ResponseEntity.created(new URI("/api/geu-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-steps} : Updates an existing geuSTEP.
     *
     * @param geuSTEPDTO the geuSTEPDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuSTEPDTO,
     * or with status {@code 400 (Bad Request)} if the geuSTEPDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuSTEPDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-steps")
    public ResponseEntity<GeuSTEPDTO> updateGeuSTEP(@RequestBody GeuSTEPDTO geuSTEPDTO) throws URISyntaxException {
        log.debug("REST request to update GeuSTEP : {}", geuSTEPDTO);
        if (geuSTEPDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuSTEPDTO result = geuSTEPService.save(geuSTEPDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuSTEPDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-steps} : get all the geuSTEPS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuSTEPS in body.
     */
    @GetMapping("/geu-steps")
    public ResponseEntity<List<GeuSTEPDTO>> getAllGeuSTEPS(Pageable pageable) {
        log.debug("REST request to get a page of GeuSTEPS");
        Page<GeuSTEPDTO> page = geuSTEPService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-steps/:id} : get the "id" geuSTEP.
     *
     * @param id the id of the geuSTEPDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuSTEPDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-steps/{id}")
    public ResponseEntity<GeuSTEPDTO> getGeuSTEP(@PathVariable Long id) {
        log.debug("REST request to get GeuSTEP : {}", id);
        Optional<GeuSTEPDTO> geuSTEPDTO = geuSTEPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuSTEPDTO);
    }

    /**
     * {@code DELETE  /geu-steps/:id} : delete the "id" geuSTEP.
     *
     * @param id the id of the geuSTEPDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-steps/{id}")
    public ResponseEntity<Void> deleteGeuSTEP(@PathVariable Long id) {
        log.debug("REST request to delete GeuSTEP : {}", id);
        geuSTEPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
