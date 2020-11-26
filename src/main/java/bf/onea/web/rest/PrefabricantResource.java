package bf.onea.web.rest;

import bf.onea.service.PrefabricantService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.PrefabricantDTO;

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
 * REST controller for managing {@link bf.onea.domain.Prefabricant}.
 */
@RestController
@RequestMapping("/api")
public class PrefabricantResource {

    private final Logger log = LoggerFactory.getLogger(PrefabricantResource.class);

    private static final String ENTITY_NAME = "prefabricant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrefabricantService prefabricantService;

    public PrefabricantResource(PrefabricantService prefabricantService) {
        this.prefabricantService = prefabricantService;
    }

    /**
     * {@code POST  /prefabricants} : Create a new prefabricant.
     *
     * @param prefabricantDTO the prefabricantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prefabricantDTO, or with status {@code 400 (Bad Request)} if the prefabricant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prefabricants")
    public ResponseEntity<PrefabricantDTO> createPrefabricant(@RequestBody PrefabricantDTO prefabricantDTO) throws URISyntaxException {
        log.debug("REST request to save Prefabricant : {}", prefabricantDTO);
        if (prefabricantDTO.getId() != null) {
            throw new BadRequestAlertException("A new prefabricant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrefabricantDTO result = prefabricantService.save(prefabricantDTO);
        return ResponseEntity.created(new URI("/api/prefabricants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prefabricants} : Updates an existing prefabricant.
     *
     * @param prefabricantDTO the prefabricantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prefabricantDTO,
     * or with status {@code 400 (Bad Request)} if the prefabricantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prefabricantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prefabricants")
    public ResponseEntity<PrefabricantDTO> updatePrefabricant(@RequestBody PrefabricantDTO prefabricantDTO) throws URISyntaxException {
        log.debug("REST request to update Prefabricant : {}", prefabricantDTO);
        if (prefabricantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrefabricantDTO result = prefabricantService.save(prefabricantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prefabricantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prefabricants} : get all the prefabricants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prefabricants in body.
     */
    @GetMapping("/prefabricants")
    public ResponseEntity<List<PrefabricantDTO>> getAllPrefabricants(Pageable pageable) {
        log.debug("REST request to get a page of Prefabricants");
        Page<PrefabricantDTO> page = prefabricantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prefabricants/:id} : get the "id" prefabricant.
     *
     * @param id the id of the prefabricantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prefabricantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prefabricants/{id}")
    public ResponseEntity<PrefabricantDTO> getPrefabricant(@PathVariable Long id) {
        log.debug("REST request to get Prefabricant : {}", id);
        Optional<PrefabricantDTO> prefabricantDTO = prefabricantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prefabricantDTO);
    }

    /**
     * {@code DELETE  /prefabricants/:id} : delete the "id" prefabricant.
     *
     * @param id the id of the prefabricantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prefabricants/{id}")
    public ResponseEntity<Void> deletePrefabricant(@PathVariable Long id) {
        log.debug("REST request to delete Prefabricant : {}", id);
        prefabricantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
