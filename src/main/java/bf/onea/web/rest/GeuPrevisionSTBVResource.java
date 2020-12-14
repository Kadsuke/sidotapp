package bf.onea.web.rest;

import bf.onea.service.GeuPrevisionSTBVService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuPrevisionSTBVDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuPrevisionSTBV}.
 */
@RestController
@RequestMapping("/api")
public class GeuPrevisionSTBVResource {

    private final Logger log = LoggerFactory.getLogger(GeuPrevisionSTBVResource.class);

    private static final String ENTITY_NAME = "geuPrevisionSTBV";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuPrevisionSTBVService geuPrevisionSTBVService;

    public GeuPrevisionSTBVResource(GeuPrevisionSTBVService geuPrevisionSTBVService) {
        this.geuPrevisionSTBVService = geuPrevisionSTBVService;
    }

    /**
     * {@code POST  /geu-prevision-stbvs} : Create a new geuPrevisionSTBV.
     *
     * @param geuPrevisionSTBVDTO the geuPrevisionSTBVDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuPrevisionSTBVDTO, or with status {@code 400 (Bad Request)} if the geuPrevisionSTBV has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-prevision-stbvs")
    public ResponseEntity<GeuPrevisionSTBVDTO> createGeuPrevisionSTBV(@Valid @RequestBody GeuPrevisionSTBVDTO geuPrevisionSTBVDTO) throws URISyntaxException {
        log.debug("REST request to save GeuPrevisionSTBV : {}", geuPrevisionSTBVDTO);
        if (geuPrevisionSTBVDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuPrevisionSTBV cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuPrevisionSTBVDTO result = geuPrevisionSTBVService.save(geuPrevisionSTBVDTO);
        return ResponseEntity.created(new URI("/api/geu-prevision-stbvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-prevision-stbvs} : Updates an existing geuPrevisionSTBV.
     *
     * @param geuPrevisionSTBVDTO the geuPrevisionSTBVDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuPrevisionSTBVDTO,
     * or with status {@code 400 (Bad Request)} if the geuPrevisionSTBVDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuPrevisionSTBVDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-prevision-stbvs")
    public ResponseEntity<GeuPrevisionSTBVDTO> updateGeuPrevisionSTBV(@Valid @RequestBody GeuPrevisionSTBVDTO geuPrevisionSTBVDTO) throws URISyntaxException {
        log.debug("REST request to update GeuPrevisionSTBV : {}", geuPrevisionSTBVDTO);
        if (geuPrevisionSTBVDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuPrevisionSTBVDTO result = geuPrevisionSTBVService.save(geuPrevisionSTBVDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuPrevisionSTBVDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-prevision-stbvs} : get all the geuPrevisionSTBVS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuPrevisionSTBVS in body.
     */
    @GetMapping("/geu-prevision-stbvs")
    public ResponseEntity<List<GeuPrevisionSTBVDTO>> getAllGeuPrevisionSTBVS(Pageable pageable) {
        log.debug("REST request to get a page of GeuPrevisionSTBVS");
        Page<GeuPrevisionSTBVDTO> page = geuPrevisionSTBVService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-prevision-stbvs/:id} : get the "id" geuPrevisionSTBV.
     *
     * @param id the id of the geuPrevisionSTBVDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuPrevisionSTBVDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-prevision-stbvs/{id}")
    public ResponseEntity<GeuPrevisionSTBVDTO> getGeuPrevisionSTBV(@PathVariable Long id) {
        log.debug("REST request to get GeuPrevisionSTBV : {}", id);
        Optional<GeuPrevisionSTBVDTO> geuPrevisionSTBVDTO = geuPrevisionSTBVService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuPrevisionSTBVDTO);
    }

    /**
     * {@code DELETE  /geu-prevision-stbvs/:id} : delete the "id" geuPrevisionSTBV.
     *
     * @param id the id of the geuPrevisionSTBVDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-prevision-stbvs/{id}")
    public ResponseEntity<Void> deleteGeuPrevisionSTBV(@PathVariable Long id) {
        log.debug("REST request to delete GeuPrevisionSTBV : {}", id);
        geuPrevisionSTBVService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
