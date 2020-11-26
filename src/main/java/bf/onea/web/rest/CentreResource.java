package bf.onea.web.rest;

import bf.onea.service.CentreService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.CentreDTO;

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
 * REST controller for managing {@link bf.onea.domain.Centre}.
 */
@RestController
@RequestMapping("/api")
public class CentreResource {

    private final Logger log = LoggerFactory.getLogger(CentreResource.class);

    private static final String ENTITY_NAME = "centre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CentreService centreService;

    public CentreResource(CentreService centreService) {
        this.centreService = centreService;
    }

    /**
     * {@code POST  /centres} : Create a new centre.
     *
     * @param centreDTO the centreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centreDTO, or with status {@code 400 (Bad Request)} if the centre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/centres")
    public ResponseEntity<CentreDTO> createCentre(@RequestBody CentreDTO centreDTO) throws URISyntaxException {
        log.debug("REST request to save Centre : {}", centreDTO);
        if (centreDTO.getId() != null) {
            throw new BadRequestAlertException("A new centre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentreDTO result = centreService.save(centreDTO);
        return ResponseEntity.created(new URI("/api/centres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /centres} : Updates an existing centre.
     *
     * @param centreDTO the centreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centreDTO,
     * or with status {@code 400 (Bad Request)} if the centreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/centres")
    public ResponseEntity<CentreDTO> updateCentre(@RequestBody CentreDTO centreDTO) throws URISyntaxException {
        log.debug("REST request to update Centre : {}", centreDTO);
        if (centreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentreDTO result = centreService.save(centreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /centres} : get all the centres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centres in body.
     */
    @GetMapping("/centres")
    public ResponseEntity<List<CentreDTO>> getAllCentres(Pageable pageable) {
        log.debug("REST request to get a page of Centres");
        Page<CentreDTO> page = centreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /centres/:id} : get the "id" centre.
     *
     * @param id the id of the centreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/centres/{id}")
    public ResponseEntity<CentreDTO> getCentre(@PathVariable Long id) {
        log.debug("REST request to get Centre : {}", id);
        Optional<CentreDTO> centreDTO = centreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centreDTO);
    }

    /**
     * {@code DELETE  /centres/:id} : delete the "id" centre.
     *
     * @param id the id of the centreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/centres/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        log.debug("REST request to delete Centre : {}", id);
        centreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
