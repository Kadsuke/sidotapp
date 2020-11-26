package bf.onea.web.rest;

import bf.onea.service.GeuUsageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuUsageDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuUsage}.
 */
@RestController
@RequestMapping("/api")
public class GeuUsageResource {

    private final Logger log = LoggerFactory.getLogger(GeuUsageResource.class);

    private static final String ENTITY_NAME = "geuUsage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuUsageService geuUsageService;

    public GeuUsageResource(GeuUsageService geuUsageService) {
        this.geuUsageService = geuUsageService;
    }

    /**
     * {@code POST  /geu-usages} : Create a new geuUsage.
     *
     * @param geuUsageDTO the geuUsageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuUsageDTO, or with status {@code 400 (Bad Request)} if the geuUsage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-usages")
    public ResponseEntity<GeuUsageDTO> createGeuUsage(@RequestBody GeuUsageDTO geuUsageDTO) throws URISyntaxException {
        log.debug("REST request to save GeuUsage : {}", geuUsageDTO);
        if (geuUsageDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuUsage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuUsageDTO result = geuUsageService.save(geuUsageDTO);
        return ResponseEntity.created(new URI("/api/geu-usages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-usages} : Updates an existing geuUsage.
     *
     * @param geuUsageDTO the geuUsageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuUsageDTO,
     * or with status {@code 400 (Bad Request)} if the geuUsageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuUsageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-usages")
    public ResponseEntity<GeuUsageDTO> updateGeuUsage(@RequestBody GeuUsageDTO geuUsageDTO) throws URISyntaxException {
        log.debug("REST request to update GeuUsage : {}", geuUsageDTO);
        if (geuUsageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuUsageDTO result = geuUsageService.save(geuUsageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuUsageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-usages} : get all the geuUsages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuUsages in body.
     */
    @GetMapping("/geu-usages")
    public ResponseEntity<List<GeuUsageDTO>> getAllGeuUsages(Pageable pageable) {
        log.debug("REST request to get a page of GeuUsages");
        Page<GeuUsageDTO> page = geuUsageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-usages/:id} : get the "id" geuUsage.
     *
     * @param id the id of the geuUsageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuUsageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-usages/{id}")
    public ResponseEntity<GeuUsageDTO> getGeuUsage(@PathVariable Long id) {
        log.debug("REST request to get GeuUsage : {}", id);
        Optional<GeuUsageDTO> geuUsageDTO = geuUsageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuUsageDTO);
    }

    /**
     * {@code DELETE  /geu-usages/:id} : delete the "id" geuUsage.
     *
     * @param id the id of the geuUsageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-usages/{id}")
    public ResponseEntity<Void> deleteGeuUsage(@PathVariable Long id) {
        log.debug("REST request to delete GeuUsage : {}", id);
        geuUsageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
