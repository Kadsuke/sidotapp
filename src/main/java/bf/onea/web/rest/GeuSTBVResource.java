package bf.onea.web.rest;

import bf.onea.service.GeuSTBVService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuSTBVDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuSTBV}.
 */
@RestController
@RequestMapping("/api")
public class GeuSTBVResource {

    private final Logger log = LoggerFactory.getLogger(GeuSTBVResource.class);

    private static final String ENTITY_NAME = "geuSTBV";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuSTBVService geuSTBVService;

    public GeuSTBVResource(GeuSTBVService geuSTBVService) {
        this.geuSTBVService = geuSTBVService;
    }

    /**
     * {@code POST  /geu-stbvs} : Create a new geuSTBV.
     *
     * @param geuSTBVDTO the geuSTBVDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuSTBVDTO, or with status {@code 400 (Bad Request)} if the geuSTBV has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-stbvs")
    public ResponseEntity<GeuSTBVDTO> createGeuSTBV(@RequestBody GeuSTBVDTO geuSTBVDTO) throws URISyntaxException {
        log.debug("REST request to save GeuSTBV : {}", geuSTBVDTO);
        if (geuSTBVDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuSTBV cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuSTBVDTO result = geuSTBVService.save(geuSTBVDTO);
        return ResponseEntity.created(new URI("/api/geu-stbvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-stbvs} : Updates an existing geuSTBV.
     *
     * @param geuSTBVDTO the geuSTBVDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuSTBVDTO,
     * or with status {@code 400 (Bad Request)} if the geuSTBVDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuSTBVDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-stbvs")
    public ResponseEntity<GeuSTBVDTO> updateGeuSTBV(@RequestBody GeuSTBVDTO geuSTBVDTO) throws URISyntaxException {
        log.debug("REST request to update GeuSTBV : {}", geuSTBVDTO);
        if (geuSTBVDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuSTBVDTO result = geuSTBVService.save(geuSTBVDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuSTBVDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-stbvs} : get all the geuSTBVS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuSTBVS in body.
     */
    @GetMapping("/geu-stbvs")
    public ResponseEntity<List<GeuSTBVDTO>> getAllGeuSTBVS(Pageable pageable) {
        log.debug("REST request to get a page of GeuSTBVS");
        Page<GeuSTBVDTO> page = geuSTBVService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-stbvs/:id} : get the "id" geuSTBV.
     *
     * @param id the id of the geuSTBVDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuSTBVDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-stbvs/{id}")
    public ResponseEntity<GeuSTBVDTO> getGeuSTBV(@PathVariable Long id) {
        log.debug("REST request to get GeuSTBV : {}", id);
        Optional<GeuSTBVDTO> geuSTBVDTO = geuSTBVService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuSTBVDTO);
    }

    /**
     * {@code DELETE  /geu-stbvs/:id} : delete the "id" geuSTBV.
     *
     * @param id the id of the geuSTBVDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-stbvs/{id}")
    public ResponseEntity<Void> deleteGeuSTBV(@PathVariable Long id) {
        log.debug("REST request to delete GeuSTBV : {}", id);
        geuSTBVService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
