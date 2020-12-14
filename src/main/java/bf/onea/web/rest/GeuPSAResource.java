package bf.onea.web.rest;

import bf.onea.service.GeuPSAService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuPSADTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuPSA}.
 */
@RestController
@RequestMapping("/api")
public class GeuPSAResource {

    private final Logger log = LoggerFactory.getLogger(GeuPSAResource.class);

    private static final String ENTITY_NAME = "geuPSA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuPSAService geuPSAService;

    public GeuPSAResource(GeuPSAService geuPSAService) {
        this.geuPSAService = geuPSAService;
    }

    /**
     * {@code POST  /geu-psas} : Create a new geuPSA.
     *
     * @param geuPSADTO the geuPSADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuPSADTO, or with status {@code 400 (Bad Request)} if the geuPSA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-psas")
    public ResponseEntity<GeuPSADTO> createGeuPSA(@Valid @RequestBody GeuPSADTO geuPSADTO) throws URISyntaxException {
        log.debug("REST request to save GeuPSA : {}", geuPSADTO);
        if (geuPSADTO.getId() != null) {
            throw new BadRequestAlertException("A new geuPSA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuPSADTO result = geuPSAService.save(geuPSADTO);
        return ResponseEntity.created(new URI("/api/geu-psas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-psas} : Updates an existing geuPSA.
     *
     * @param geuPSADTO the geuPSADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuPSADTO,
     * or with status {@code 400 (Bad Request)} if the geuPSADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuPSADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-psas")
    public ResponseEntity<GeuPSADTO> updateGeuPSA(@Valid @RequestBody GeuPSADTO geuPSADTO) throws URISyntaxException {
        log.debug("REST request to update GeuPSA : {}", geuPSADTO);
        if (geuPSADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuPSADTO result = geuPSAService.save(geuPSADTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuPSADTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-psas} : get all the geuPSAS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuPSAS in body.
     */
    @GetMapping("/geu-psas")
    public ResponseEntity<List<GeuPSADTO>> getAllGeuPSAS(Pageable pageable) {
        log.debug("REST request to get a page of GeuPSAS");
        Page<GeuPSADTO> page = geuPSAService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-psas/:id} : get the "id" geuPSA.
     *
     * @param id the id of the geuPSADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuPSADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-psas/{id}")
    public ResponseEntity<GeuPSADTO> getGeuPSA(@PathVariable Long id) {
        log.debug("REST request to get GeuPSA : {}", id);
        Optional<GeuPSADTO> geuPSADTO = geuPSAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuPSADTO);
    }

    /**
     * {@code DELETE  /geu-psas/:id} : delete the "id" geuPSA.
     *
     * @param id the id of the geuPSADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-psas/{id}")
    public ResponseEntity<Void> deleteGeuPSA(@PathVariable Long id) {
        log.debug("REST request to delete GeuPSA : {}", id);
        geuPSAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
