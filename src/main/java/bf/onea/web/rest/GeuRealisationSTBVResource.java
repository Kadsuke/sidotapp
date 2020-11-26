package bf.onea.web.rest;

import bf.onea.service.GeuRealisationSTBVService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuRealisationSTBVDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuRealisationSTBV}.
 */
@RestController
@RequestMapping("/api")
public class GeuRealisationSTBVResource {

    private final Logger log = LoggerFactory.getLogger(GeuRealisationSTBVResource.class);

    private static final String ENTITY_NAME = "geuRealisationSTBV";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuRealisationSTBVService geuRealisationSTBVService;

    public GeuRealisationSTBVResource(GeuRealisationSTBVService geuRealisationSTBVService) {
        this.geuRealisationSTBVService = geuRealisationSTBVService;
    }

    /**
     * {@code POST  /geu-realisation-stbvs} : Create a new geuRealisationSTBV.
     *
     * @param geuRealisationSTBVDTO the geuRealisationSTBVDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuRealisationSTBVDTO, or with status {@code 400 (Bad Request)} if the geuRealisationSTBV has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-realisation-stbvs")
    public ResponseEntity<GeuRealisationSTBVDTO> createGeuRealisationSTBV(@RequestBody GeuRealisationSTBVDTO geuRealisationSTBVDTO) throws URISyntaxException {
        log.debug("REST request to save GeuRealisationSTBV : {}", geuRealisationSTBVDTO);
        if (geuRealisationSTBVDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuRealisationSTBV cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuRealisationSTBVDTO result = geuRealisationSTBVService.save(geuRealisationSTBVDTO);
        return ResponseEntity.created(new URI("/api/geu-realisation-stbvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-realisation-stbvs} : Updates an existing geuRealisationSTBV.
     *
     * @param geuRealisationSTBVDTO the geuRealisationSTBVDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuRealisationSTBVDTO,
     * or with status {@code 400 (Bad Request)} if the geuRealisationSTBVDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuRealisationSTBVDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-realisation-stbvs")
    public ResponseEntity<GeuRealisationSTBVDTO> updateGeuRealisationSTBV(@RequestBody GeuRealisationSTBVDTO geuRealisationSTBVDTO) throws URISyntaxException {
        log.debug("REST request to update GeuRealisationSTBV : {}", geuRealisationSTBVDTO);
        if (geuRealisationSTBVDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuRealisationSTBVDTO result = geuRealisationSTBVService.save(geuRealisationSTBVDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuRealisationSTBVDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-realisation-stbvs} : get all the geuRealisationSTBVS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuRealisationSTBVS in body.
     */
    @GetMapping("/geu-realisation-stbvs")
    public ResponseEntity<List<GeuRealisationSTBVDTO>> getAllGeuRealisationSTBVS(Pageable pageable) {
        log.debug("REST request to get a page of GeuRealisationSTBVS");
        Page<GeuRealisationSTBVDTO> page = geuRealisationSTBVService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-realisation-stbvs/:id} : get the "id" geuRealisationSTBV.
     *
     * @param id the id of the geuRealisationSTBVDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuRealisationSTBVDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-realisation-stbvs/{id}")
    public ResponseEntity<GeuRealisationSTBVDTO> getGeuRealisationSTBV(@PathVariable Long id) {
        log.debug("REST request to get GeuRealisationSTBV : {}", id);
        Optional<GeuRealisationSTBVDTO> geuRealisationSTBVDTO = geuRealisationSTBVService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuRealisationSTBVDTO);
    }

    /**
     * {@code DELETE  /geu-realisation-stbvs/:id} : delete the "id" geuRealisationSTBV.
     *
     * @param id the id of the geuRealisationSTBVDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-realisation-stbvs/{id}")
    public ResponseEntity<Void> deleteGeuRealisationSTBV(@PathVariable Long id) {
        log.debug("REST request to delete GeuRealisationSTBV : {}", id);
        geuRealisationSTBVService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
