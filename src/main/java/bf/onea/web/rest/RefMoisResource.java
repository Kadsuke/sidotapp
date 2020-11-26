package bf.onea.web.rest;

import bf.onea.service.RefMoisService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.RefMoisDTO;

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
 * REST controller for managing {@link bf.onea.domain.RefMois}.
 */
@RestController
@RequestMapping("/api")
public class RefMoisResource {

    private final Logger log = LoggerFactory.getLogger(RefMoisResource.class);

    private static final String ENTITY_NAME = "refMois";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefMoisService refMoisService;

    public RefMoisResource(RefMoisService refMoisService) {
        this.refMoisService = refMoisService;
    }

    /**
     * {@code POST  /ref-mois} : Create a new refMois.
     *
     * @param refMoisDTO the refMoisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refMoisDTO, or with status {@code 400 (Bad Request)} if the refMois has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-mois")
    public ResponseEntity<RefMoisDTO> createRefMois(@RequestBody RefMoisDTO refMoisDTO) throws URISyntaxException {
        log.debug("REST request to save RefMois : {}", refMoisDTO);
        if (refMoisDTO.getId() != null) {
            throw new BadRequestAlertException("A new refMois cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefMoisDTO result = refMoisService.save(refMoisDTO);
        return ResponseEntity.created(new URI("/api/ref-mois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-mois} : Updates an existing refMois.
     *
     * @param refMoisDTO the refMoisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refMoisDTO,
     * or with status {@code 400 (Bad Request)} if the refMoisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refMoisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-mois")
    public ResponseEntity<RefMoisDTO> updateRefMois(@RequestBody RefMoisDTO refMoisDTO) throws URISyntaxException {
        log.debug("REST request to update RefMois : {}", refMoisDTO);
        if (refMoisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefMoisDTO result = refMoisService.save(refMoisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refMoisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ref-mois} : get all the refMois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refMois in body.
     */
    @GetMapping("/ref-mois")
    public ResponseEntity<List<RefMoisDTO>> getAllRefMois(Pageable pageable) {
        log.debug("REST request to get a page of RefMois");
        Page<RefMoisDTO> page = refMoisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-mois/:id} : get the "id" refMois.
     *
     * @param id the id of the refMoisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refMoisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-mois/{id}")
    public ResponseEntity<RefMoisDTO> getRefMois(@PathVariable Long id) {
        log.debug("REST request to get RefMois : {}", id);
        Optional<RefMoisDTO> refMoisDTO = refMoisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refMoisDTO);
    }

    /**
     * {@code DELETE  /ref-mois/:id} : delete the "id" refMois.
     *
     * @param id the id of the refMoisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-mois/{id}")
    public ResponseEntity<Void> deleteRefMois(@PathVariable Long id) {
        log.debug("REST request to delete RefMois : {}", id);
        refMoisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
