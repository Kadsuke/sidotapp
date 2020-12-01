package bf.onea.web.rest;

import bf.onea.service.PrevisionAssainissementColService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.PrevisionAssainissementColDTO;

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
 * REST controller for managing {@link bf.onea.domain.PrevisionAssainissementCol}.
 */
@RestController
@RequestMapping("/api")
public class PrevisionAssainissementColResource {

    private final Logger log = LoggerFactory.getLogger(PrevisionAssainissementColResource.class);

    private static final String ENTITY_NAME = "previsionAssainissementCol";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisionAssainissementColService previsionAssainissementColService;

    public PrevisionAssainissementColResource(PrevisionAssainissementColService previsionAssainissementColService) {
        this.previsionAssainissementColService = previsionAssainissementColService;
    }

    /**
     * {@code POST  /prevision-assainissement-cols} : Create a new previsionAssainissementCol.
     *
     * @param previsionAssainissementColDTO the previsionAssainissementColDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsionAssainissementColDTO, or with status {@code 400 (Bad Request)} if the previsionAssainissementCol has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prevision-assainissement-cols")
    public ResponseEntity<PrevisionAssainissementColDTO> createPrevisionAssainissementCol(@RequestBody PrevisionAssainissementColDTO previsionAssainissementColDTO) throws URISyntaxException {
        log.debug("REST request to save PrevisionAssainissementCol : {}", previsionAssainissementColDTO);
        if (previsionAssainissementColDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsionAssainissementCol cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisionAssainissementColDTO result = previsionAssainissementColService.save(previsionAssainissementColDTO);
        return ResponseEntity.created(new URI("/api/prevision-assainissement-cols/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prevision-assainissement-cols} : Updates an existing previsionAssainissementCol.
     *
     * @param previsionAssainissementColDTO the previsionAssainissementColDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsionAssainissementColDTO,
     * or with status {@code 400 (Bad Request)} if the previsionAssainissementColDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsionAssainissementColDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prevision-assainissement-cols")
    public ResponseEntity<PrevisionAssainissementColDTO> updatePrevisionAssainissementCol(@RequestBody PrevisionAssainissementColDTO previsionAssainissementColDTO) throws URISyntaxException {
        log.debug("REST request to update PrevisionAssainissementCol : {}", previsionAssainissementColDTO);
        if (previsionAssainissementColDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisionAssainissementColDTO result = previsionAssainissementColService.save(previsionAssainissementColDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsionAssainissementColDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prevision-assainissement-cols} : get all the previsionAssainissementCols.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsionAssainissementCols in body.
     */
    @GetMapping("/prevision-assainissement-cols")
    public ResponseEntity<List<PrevisionAssainissementColDTO>> getAllPrevisionAssainissementCols(Pageable pageable) {
        log.debug("REST request to get a page of PrevisionAssainissementCols");
        Page<PrevisionAssainissementColDTO> page = previsionAssainissementColService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prevision-assainissement-cols/:id} : get the "id" previsionAssainissementCol.
     *
     * @param id the id of the previsionAssainissementColDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsionAssainissementColDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prevision-assainissement-cols/{id}")
    public ResponseEntity<PrevisionAssainissementColDTO> getPrevisionAssainissementCol(@PathVariable Long id) {
        log.debug("REST request to get PrevisionAssainissementCol : {}", id);
        Optional<PrevisionAssainissementColDTO> previsionAssainissementColDTO = previsionAssainissementColService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsionAssainissementColDTO);
    }

    /**
     * {@code DELETE  /prevision-assainissement-cols/:id} : delete the "id" previsionAssainissementCol.
     *
     * @param id the id of the previsionAssainissementColDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prevision-assainissement-cols/{id}")
    public ResponseEntity<Void> deletePrevisionAssainissementCol(@PathVariable Long id) {
        log.debug("REST request to delete PrevisionAssainissementCol : {}", id);
        previsionAssainissementColService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
