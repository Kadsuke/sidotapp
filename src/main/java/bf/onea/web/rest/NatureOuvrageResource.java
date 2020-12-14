package bf.onea.web.rest;

import bf.onea.service.NatureOuvrageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.NatureOuvrageDTO;

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
 * REST controller for managing {@link bf.onea.domain.NatureOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class NatureOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(NatureOuvrageResource.class);

    private static final String ENTITY_NAME = "natureOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureOuvrageService natureOuvrageService;

    public NatureOuvrageResource(NatureOuvrageService natureOuvrageService) {
        this.natureOuvrageService = natureOuvrageService;
    }

    /**
     * {@code POST  /nature-ouvrages} : Create a new natureOuvrage.
     *
     * @param natureOuvrageDTO the natureOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureOuvrageDTO, or with status {@code 400 (Bad Request)} if the natureOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-ouvrages")
    public ResponseEntity<NatureOuvrageDTO> createNatureOuvrage(@Valid @RequestBody NatureOuvrageDTO natureOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save NatureOuvrage : {}", natureOuvrageDTO);
        if (natureOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new natureOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureOuvrageDTO result = natureOuvrageService.save(natureOuvrageDTO);
        return ResponseEntity.created(new URI("/api/nature-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-ouvrages} : Updates an existing natureOuvrage.
     *
     * @param natureOuvrageDTO the natureOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the natureOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-ouvrages")
    public ResponseEntity<NatureOuvrageDTO> updateNatureOuvrage(@Valid @RequestBody NatureOuvrageDTO natureOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update NatureOuvrage : {}", natureOuvrageDTO);
        if (natureOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureOuvrageDTO result = natureOuvrageService.save(natureOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, natureOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-ouvrages} : get all the natureOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureOuvrages in body.
     */
    @GetMapping("/nature-ouvrages")
    public ResponseEntity<List<NatureOuvrageDTO>> getAllNatureOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of NatureOuvrages");
        Page<NatureOuvrageDTO> page = natureOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-ouvrages/:id} : get the "id" natureOuvrage.
     *
     * @param id the id of the natureOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-ouvrages/{id}")
    public ResponseEntity<NatureOuvrageDTO> getNatureOuvrage(@PathVariable Long id) {
        log.debug("REST request to get NatureOuvrage : {}", id);
        Optional<NatureOuvrageDTO> natureOuvrageDTO = natureOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureOuvrageDTO);
    }

    /**
     * {@code DELETE  /nature-ouvrages/:id} : delete the "id" natureOuvrage.
     *
     * @param id the id of the natureOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-ouvrages/{id}")
    public ResponseEntity<Void> deleteNatureOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete NatureOuvrage : {}", id);
        natureOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
