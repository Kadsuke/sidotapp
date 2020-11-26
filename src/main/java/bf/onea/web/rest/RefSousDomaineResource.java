package bf.onea.web.rest;

import bf.onea.service.RefSousDomaineService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.RefSousDomaineDTO;

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
 * REST controller for managing {@link bf.onea.domain.RefSousDomaine}.
 */
@RestController
@RequestMapping("/api")
public class RefSousDomaineResource {

    private final Logger log = LoggerFactory.getLogger(RefSousDomaineResource.class);

    private static final String ENTITY_NAME = "refSousDomaine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefSousDomaineService refSousDomaineService;

    public RefSousDomaineResource(RefSousDomaineService refSousDomaineService) {
        this.refSousDomaineService = refSousDomaineService;
    }

    /**
     * {@code POST  /ref-sous-domaines} : Create a new refSousDomaine.
     *
     * @param refSousDomaineDTO the refSousDomaineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refSousDomaineDTO, or with status {@code 400 (Bad Request)} if the refSousDomaine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-sous-domaines")
    public ResponseEntity<RefSousDomaineDTO> createRefSousDomaine(@RequestBody RefSousDomaineDTO refSousDomaineDTO) throws URISyntaxException {
        log.debug("REST request to save RefSousDomaine : {}", refSousDomaineDTO);
        if (refSousDomaineDTO.getId() != null) {
            throw new BadRequestAlertException("A new refSousDomaine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefSousDomaineDTO result = refSousDomaineService.save(refSousDomaineDTO);
        return ResponseEntity.created(new URI("/api/ref-sous-domaines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-sous-domaines} : Updates an existing refSousDomaine.
     *
     * @param refSousDomaineDTO the refSousDomaineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refSousDomaineDTO,
     * or with status {@code 400 (Bad Request)} if the refSousDomaineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refSousDomaineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-sous-domaines")
    public ResponseEntity<RefSousDomaineDTO> updateRefSousDomaine(@RequestBody RefSousDomaineDTO refSousDomaineDTO) throws URISyntaxException {
        log.debug("REST request to update RefSousDomaine : {}", refSousDomaineDTO);
        if (refSousDomaineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefSousDomaineDTO result = refSousDomaineService.save(refSousDomaineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refSousDomaineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ref-sous-domaines} : get all the refSousDomaines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refSousDomaines in body.
     */
    @GetMapping("/ref-sous-domaines")
    public ResponseEntity<List<RefSousDomaineDTO>> getAllRefSousDomaines(Pageable pageable) {
        log.debug("REST request to get a page of RefSousDomaines");
        Page<RefSousDomaineDTO> page = refSousDomaineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-sous-domaines/:id} : get the "id" refSousDomaine.
     *
     * @param id the id of the refSousDomaineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refSousDomaineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-sous-domaines/{id}")
    public ResponseEntity<RefSousDomaineDTO> getRefSousDomaine(@PathVariable Long id) {
        log.debug("REST request to get RefSousDomaine : {}", id);
        Optional<RefSousDomaineDTO> refSousDomaineDTO = refSousDomaineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refSousDomaineDTO);
    }

    /**
     * {@code DELETE  /ref-sous-domaines/:id} : delete the "id" refSousDomaine.
     *
     * @param id the id of the refSousDomaineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-sous-domaines/{id}")
    public ResponseEntity<Void> deleteRefSousDomaine(@PathVariable Long id) {
        log.debug("REST request to delete RefSousDomaine : {}", id);
        refSousDomaineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
