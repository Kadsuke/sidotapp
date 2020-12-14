package bf.onea.web.rest;

import bf.onea.service.RefAnneeService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.RefAnneeDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link bf.onea.domain.RefAnnee}.
 */
@RestController
@RequestMapping("/api")
public class RefAnneeResource {

    private final Logger log = LoggerFactory.getLogger(RefAnneeResource.class);

    private static final String ENTITY_NAME = "refAnnee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefAnneeService refAnneeService;

    public RefAnneeResource(RefAnneeService refAnneeService) {
        this.refAnneeService = refAnneeService;
    }

    /**
     * {@code POST  /ref-annees} : Create a new refAnnee.
     *
     * @param refAnneeDTO the refAnneeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refAnneeDTO, or with status {@code 400 (Bad Request)} if the refAnnee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-annees")
    public ResponseEntity<RefAnneeDTO> createRefAnnee(@Valid @RequestBody RefAnneeDTO refAnneeDTO) throws URISyntaxException {
        log.debug("REST request to save RefAnnee : {}", refAnneeDTO);
        if (refAnneeDTO.getId() != null) {
            throw new BadRequestAlertException("A new refAnnee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefAnneeDTO result = refAnneeService.save(refAnneeDTO);
        return ResponseEntity.created(new URI("/api/ref-annees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-annees} : Updates an existing refAnnee.
     *
     * @param refAnneeDTO the refAnneeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refAnneeDTO,
     * or with status {@code 400 (Bad Request)} if the refAnneeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refAnneeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-annees")
    public ResponseEntity<RefAnneeDTO> updateRefAnnee(@Valid @RequestBody RefAnneeDTO refAnneeDTO) throws URISyntaxException {
        log.debug("REST request to update RefAnnee : {}", refAnneeDTO);
        if (refAnneeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefAnneeDTO result = refAnneeService.save(refAnneeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refAnneeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ref-annees} : get all the refAnnees.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refAnnees in body.
     */
    @GetMapping("/ref-annees")
    public ResponseEntity<List<RefAnneeDTO>> getAllRefAnnees(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("previsionassainissementau-is-null".equals(filter)) {
            log.debug("REST request to get all RefAnnees where previsionAssainissementAu is null");
            return new ResponseEntity<>(refAnneeService.findAllWherePrevisionAssainissementAuIsNull(),
                    HttpStatus.OK);
        }
        if ("previsionassainissementcol-is-null".equals(filter)) {
            log.debug("REST request to get all RefAnnees where previsionAssainissementCol is null");
            return new ResponseEntity<>(refAnneeService.findAllWherePrevisionAssainissementColIsNull(),
                    HttpStatus.OK);
        }
        if ("previsionpsa-is-null".equals(filter)) {
            log.debug("REST request to get all RefAnnees where previsionPsa is null");
            return new ResponseEntity<>(refAnneeService.findAllWherePrevisionPsaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RefAnnees");
        Page<RefAnneeDTO> page = refAnneeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-annees/:id} : get the "id" refAnnee.
     *
     * @param id the id of the refAnneeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refAnneeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-annees/{id}")
    public ResponseEntity<RefAnneeDTO> getRefAnnee(@PathVariable Long id) {
        log.debug("REST request to get RefAnnee : {}", id);
        Optional<RefAnneeDTO> refAnneeDTO = refAnneeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refAnneeDTO);
    }

    /**
     * {@code DELETE  /ref-annees/:id} : delete the "id" refAnnee.
     *
     * @param id the id of the refAnneeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-annees/{id}")
    public ResponseEntity<Void> deleteRefAnnee(@PathVariable Long id) {
        log.debug("REST request to delete RefAnnee : {}", id);
        refAnneeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
