package bf.onea.web.rest;

import bf.onea.service.RefPeriodiciteService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.RefPeriodiciteDTO;

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
 * REST controller for managing {@link bf.onea.domain.RefPeriodicite}.
 */
@RestController
@RequestMapping("/api")
public class RefPeriodiciteResource {

    private final Logger log = LoggerFactory.getLogger(RefPeriodiciteResource.class);

    private static final String ENTITY_NAME = "refPeriodicite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefPeriodiciteService refPeriodiciteService;

    public RefPeriodiciteResource(RefPeriodiciteService refPeriodiciteService) {
        this.refPeriodiciteService = refPeriodiciteService;
    }

    /**
     * {@code POST  /ref-periodicites} : Create a new refPeriodicite.
     *
     * @param refPeriodiciteDTO the refPeriodiciteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refPeriodiciteDTO, or with status {@code 400 (Bad Request)} if the refPeriodicite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-periodicites")
    public ResponseEntity<RefPeriodiciteDTO> createRefPeriodicite(@Valid @RequestBody RefPeriodiciteDTO refPeriodiciteDTO) throws URISyntaxException {
        log.debug("REST request to save RefPeriodicite : {}", refPeriodiciteDTO);
        if (refPeriodiciteDTO.getId() != null) {
            throw new BadRequestAlertException("A new refPeriodicite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefPeriodiciteDTO result = refPeriodiciteService.save(refPeriodiciteDTO);
        return ResponseEntity.created(new URI("/api/ref-periodicites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-periodicites} : Updates an existing refPeriodicite.
     *
     * @param refPeriodiciteDTO the refPeriodiciteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refPeriodiciteDTO,
     * or with status {@code 400 (Bad Request)} if the refPeriodiciteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refPeriodiciteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-periodicites")
    public ResponseEntity<RefPeriodiciteDTO> updateRefPeriodicite(@Valid @RequestBody RefPeriodiciteDTO refPeriodiciteDTO) throws URISyntaxException {
        log.debug("REST request to update RefPeriodicite : {}", refPeriodiciteDTO);
        if (refPeriodiciteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefPeriodiciteDTO result = refPeriodiciteService.save(refPeriodiciteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refPeriodiciteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ref-periodicites} : get all the refPeriodicites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refPeriodicites in body.
     */
    @GetMapping("/ref-periodicites")
    public ResponseEntity<List<RefPeriodiciteDTO>> getAllRefPeriodicites(Pageable pageable) {
        log.debug("REST request to get a page of RefPeriodicites");
        Page<RefPeriodiciteDTO> page = refPeriodiciteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-periodicites/:id} : get the "id" refPeriodicite.
     *
     * @param id the id of the refPeriodiciteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refPeriodiciteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-periodicites/{id}")
    public ResponseEntity<RefPeriodiciteDTO> getRefPeriodicite(@PathVariable Long id) {
        log.debug("REST request to get RefPeriodicite : {}", id);
        Optional<RefPeriodiciteDTO> refPeriodiciteDTO = refPeriodiciteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refPeriodiciteDTO);
    }

    /**
     * {@code DELETE  /ref-periodicites/:id} : delete the "id" refPeriodicite.
     *
     * @param id the id of the refPeriodiciteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-periodicites/{id}")
    public ResponseEntity<Void> deleteRefPeriodicite(@PathVariable Long id) {
        log.debug("REST request to delete RefPeriodicite : {}", id);
        refPeriodiciteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
