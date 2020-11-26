package bf.onea.web.rest;

import bf.onea.service.GeuRaccordementService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeuRaccordementDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeuRaccordement}.
 */
@RestController
@RequestMapping("/api")
public class GeuRaccordementResource {

    private final Logger log = LoggerFactory.getLogger(GeuRaccordementResource.class);

    private static final String ENTITY_NAME = "geuRaccordement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeuRaccordementService geuRaccordementService;

    public GeuRaccordementResource(GeuRaccordementService geuRaccordementService) {
        this.geuRaccordementService = geuRaccordementService;
    }

    /**
     * {@code POST  /geu-raccordements} : Create a new geuRaccordement.
     *
     * @param geuRaccordementDTO the geuRaccordementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geuRaccordementDTO, or with status {@code 400 (Bad Request)} if the geuRaccordement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geu-raccordements")
    public ResponseEntity<GeuRaccordementDTO> createGeuRaccordement(@RequestBody GeuRaccordementDTO geuRaccordementDTO) throws URISyntaxException {
        log.debug("REST request to save GeuRaccordement : {}", geuRaccordementDTO);
        if (geuRaccordementDTO.getId() != null) {
            throw new BadRequestAlertException("A new geuRaccordement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeuRaccordementDTO result = geuRaccordementService.save(geuRaccordementDTO);
        return ResponseEntity.created(new URI("/api/geu-raccordements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geu-raccordements} : Updates an existing geuRaccordement.
     *
     * @param geuRaccordementDTO the geuRaccordementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geuRaccordementDTO,
     * or with status {@code 400 (Bad Request)} if the geuRaccordementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geuRaccordementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geu-raccordements")
    public ResponseEntity<GeuRaccordementDTO> updateGeuRaccordement(@RequestBody GeuRaccordementDTO geuRaccordementDTO) throws URISyntaxException {
        log.debug("REST request to update GeuRaccordement : {}", geuRaccordementDTO);
        if (geuRaccordementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeuRaccordementDTO result = geuRaccordementService.save(geuRaccordementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geuRaccordementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geu-raccordements} : get all the geuRaccordements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geuRaccordements in body.
     */
    @GetMapping("/geu-raccordements")
    public ResponseEntity<List<GeuRaccordementDTO>> getAllGeuRaccordements(Pageable pageable) {
        log.debug("REST request to get a page of GeuRaccordements");
        Page<GeuRaccordementDTO> page = geuRaccordementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geu-raccordements/:id} : get the "id" geuRaccordement.
     *
     * @param id the id of the geuRaccordementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geuRaccordementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geu-raccordements/{id}")
    public ResponseEntity<GeuRaccordementDTO> getGeuRaccordement(@PathVariable Long id) {
        log.debug("REST request to get GeuRaccordement : {}", id);
        Optional<GeuRaccordementDTO> geuRaccordementDTO = geuRaccordementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geuRaccordementDTO);
    }

    /**
     * {@code DELETE  /geu-raccordements/:id} : delete the "id" geuRaccordement.
     *
     * @param id the id of the geuRaccordementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geu-raccordements/{id}")
    public ResponseEntity<Void> deleteGeuRaccordement(@PathVariable Long id) {
        log.debug("REST request to delete GeuRaccordement : {}", id);
        geuRaccordementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
