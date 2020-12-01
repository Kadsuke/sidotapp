package bf.onea.web.rest;

import bf.onea.service.PrevisionAssainissementAuService;
import bf.onea.service.dto.PrevisionAssainissementAuDTO;
import bf.onea.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link bf.onea.domain.PrevisionAssainissementAu}.
 */
@RestController
@RequestMapping("/api")
public class PrevisionAssainissementAuResource {
    private final Logger log = LoggerFactory.getLogger(PrevisionAssainissementAuResource.class);

    private static final String ENTITY_NAME = "previsionAssainissementAu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisionAssainissementAuService previsionAssainissementAuService;

    public PrevisionAssainissementAuResource(PrevisionAssainissementAuService previsionAssainissementAuService) {
        this.previsionAssainissementAuService = previsionAssainissementAuService;
    }

    /**
     * {@code POST  /prevision-assainissement-aus} : Create a new previsionAssainissementAu.
     *
     * @param previsionAssainissementAuDTO the previsionAssainissementAuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsionAssainissementAuDTO, or with status {@code 400 (Bad Request)} if the previsionAssainissementAu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prevision-assainissement-aus")
    public ResponseEntity<PrevisionAssainissementAuDTO> createPrevisionAssainissementAu(
        @RequestBody PrevisionAssainissementAuDTO previsionAssainissementAuDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save PrevisionAssainissementAu : {}", previsionAssainissementAuDTO);
        if (previsionAssainissementAuDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsionAssainissementAu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisionAssainissementAuDTO result = previsionAssainissementAuService.save(previsionAssainissementAuDTO);
        return ResponseEntity
            .created(new URI("/api/prevision-assainissement-aus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prevision-assainissement-aus} : Updates an existing previsionAssainissementAu.
     *
     * @param previsionAssainissementAuDTO the previsionAssainissementAuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsionAssainissementAuDTO,
     * or with status {@code 400 (Bad Request)} if the previsionAssainissementAuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsionAssainissementAuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prevision-assainissement-aus")
    public ResponseEntity<PrevisionAssainissementAuDTO> updatePrevisionAssainissementAu(
        @RequestBody PrevisionAssainissementAuDTO previsionAssainissementAuDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update PrevisionAssainissementAu : {}", previsionAssainissementAuDTO);
        if (previsionAssainissementAuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisionAssainissementAuDTO result = previsionAssainissementAuService.save(previsionAssainissementAuDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsionAssainissementAuDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /prevision-assainissement-aus} : get all the previsionAssainissementAus.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsionAssainissementAus in body.
     */
    @GetMapping("/prevision-assainissement-aus")
    public ResponseEntity<List<PrevisionAssainissementAuDTO>> getAllPrevisionAssainissementAus(Pageable pageable) {
        log.debug("REST request to get a page of PrevisionAssainissementAus");
        Page<PrevisionAssainissementAuDTO> page = previsionAssainissementAuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prevision-assainissement-aus/:id} : get the "id" previsionAssainissementAu.
     *
     * @param id the id of the previsionAssainissementAuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsionAssainissementAuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prevision-assainissement-aus/{id}")
    public ResponseEntity<PrevisionAssainissementAuDTO> getPrevisionAssainissementAu(@PathVariable Long id) {
        log.debug("REST request to get PrevisionAssainissementAu : {}", id);
        Optional<PrevisionAssainissementAuDTO> previsionAssainissementAuDTO = previsionAssainissementAuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsionAssainissementAuDTO);
    }

    /**
     * {@code DELETE  /prevision-assainissement-aus/:id} : delete the "id" previsionAssainissementAu.
     *
     * @param id the id of the previsionAssainissementAuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prevision-assainissement-aus/{id}")
    public ResponseEntity<Void> deletePrevisionAssainissementAu(@PathVariable Long id) {
        log.debug("REST request to delete PrevisionAssainissementAu : {}", id);
        previsionAssainissementAuService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
