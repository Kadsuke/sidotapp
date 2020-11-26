package bf.onea.web.rest;

import bf.onea.service.GeuRealisationService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuRealisationDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuRealisation}.
 */
@RestController
@RequestMapping("/api")
public class GeuRealisationResource {

    private final Logger log = LoggerFactory.getLogger(GeuRealisationResource.class);

    private static final String ENTITY_NAME = "geuRealisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuRealisationService geuRealisationService;

    public GeuRealisationResource(GeuRealisationService geuRealisationService) {
        this.geuRealisationService = geuRealisationService;
    }

    /**
     * {@code POST  /geu-realisations} : Create a new geuRealisation.
     *
     * @param geuRealisationDTO the geuRealisationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuRealisationDTO, or with status {@code 400 (Bad Request)} if the geuRealisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-realisations")
    public ResponseEntity<GeuRealisationDTO> createGeuRealisation(@RequestBody GeuRealisationDTO geuRealisationDTO) throws URISyntaxException {
        log.debug("REST request to save GeuRealisation : {}", geuRealisationDTO);
        if (geuRealisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuRealisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuRealisationDTO result = geuRealisationService.save(geuRealisationDTO);
        return ResponseEntity.created(new URI("/api/geu-realisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-realisations} : Updates an existing geuRealisation.
     *
     * @param geuRealisationDTO the geuRealisationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuRealisationDTO,
     * or with status {@code 400 (Bad Request)} if the geuRealisationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuRealisationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-realisations")
    public ResponseEntity<GeuRealisationDTO> updateGeuRealisation(@RequestBody GeuRealisationDTO geuRealisationDTO) throws URISyntaxException {
        log.debug("REST request to update GeuRealisation : {}", geuRealisationDTO);
        if (geuRealisationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuRealisationDTO result = geuRealisationService.save(geuRealisationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuRealisationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-realisations} : get all the geuRealisations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuRealisations in body.
     */
    @GetMapping("/geu-realisations")
    public ResponseEntity<List<GeuRealisationDTO>> getAllGeuRealisations(Pageable pageable) {
        log.debug("REST request to get a page of GeuRealisations");
        Page<GeuRealisationDTO> page = geuRealisationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-realisations/:id} : get the "id" geuRealisation.
     *
     * @param id the id of the geuRealisationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuRealisationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-realisations/{id}")
    public ResponseEntity<GeuRealisationDTO> getGeuRealisation(@PathVariable Long id) {
        log.debug("REST request to get GeuRealisation : {}", id);
        Optional<GeuRealisationDTO> geuRealisationDTO = geuRealisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuRealisationDTO);
    }

    /**
     * {@code DELETE  /geu-realisations/:id} : delete the "id" geuRealisation.
     *
     * @param id the id of the geuRealisationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-realisations/{id}")
    public ResponseEntity<Void> deleteGeuRealisation(@PathVariable Long id) {
        log.debug("REST request to delete GeuRealisation : {}", id);
        geuRealisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
