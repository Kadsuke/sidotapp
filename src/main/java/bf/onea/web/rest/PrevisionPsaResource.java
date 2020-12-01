package bf.onea.web.rest;

import bf.onea.service.PrevisionPsaService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.PrevisionPsaDTO;

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
 * REST controller for managing {@link bf.onea.domain.PrevisionPsa}.
 */
@RestController
@RequestMapping("/api")
public class PrevisionPsaResource {

    private final Logger log = LoggerFactory.getLogger(PrevisionPsaResource.class);

    private static final String ENTITY_NAME = "previsionPsa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisionPsaService previsionPsaService;

    public PrevisionPsaResource(PrevisionPsaService previsionPsaService) {
        this.previsionPsaService = previsionPsaService;
    }

    /**
     * {@code POST  /prevision-psas} : Create a new previsionPsa.
     *
     * @param previsionPsaDTO the previsionPsaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsionPsaDTO, or with status {@code 400 (Bad Request)} if the previsionPsa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prevision-psas")
    public ResponseEntity<PrevisionPsaDTO> createPrevisionPsa(@RequestBody PrevisionPsaDTO previsionPsaDTO) throws URISyntaxException {
        log.debug("REST request to save PrevisionPsa : {}", previsionPsaDTO);
        if (previsionPsaDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsionPsa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisionPsaDTO result = previsionPsaService.save(previsionPsaDTO);
        return ResponseEntity.created(new URI("/api/prevision-psas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prevision-psas} : Updates an existing previsionPsa.
     *
     * @param previsionPsaDTO the previsionPsaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsionPsaDTO,
     * or with status {@code 400 (Bad Request)} if the previsionPsaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsionPsaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prevision-psas")
    public ResponseEntity<PrevisionPsaDTO> updatePrevisionPsa(@RequestBody PrevisionPsaDTO previsionPsaDTO) throws URISyntaxException {
        log.debug("REST request to update PrevisionPsa : {}", previsionPsaDTO);
        if (previsionPsaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisionPsaDTO result = previsionPsaService.save(previsionPsaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsionPsaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prevision-psas} : get all the previsionPsas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsionPsas in body.
     */
    @GetMapping("/prevision-psas")
    public ResponseEntity<List<PrevisionPsaDTO>> getAllPrevisionPsas(Pageable pageable) {
        log.debug("REST request to get a page of PrevisionPsas");
        Page<PrevisionPsaDTO> page = previsionPsaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prevision-psas/:id} : get the "id" previsionPsa.
     *
     * @param id the id of the previsionPsaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsionPsaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prevision-psas/{id}")
    public ResponseEntity<PrevisionPsaDTO> getPrevisionPsa(@PathVariable Long id) {
        log.debug("REST request to get PrevisionPsa : {}", id);
        Optional<PrevisionPsaDTO> previsionPsaDTO = previsionPsaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsionPsaDTO);
    }

    /**
     * {@code DELETE  /prevision-psas/:id} : delete the "id" previsionPsa.
     *
     * @param id the id of the previsionPsaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prevision-psas/{id}")
    public ResponseEntity<Void> deletePrevisionPsa(@PathVariable Long id) {
        log.debug("REST request to delete PrevisionPsa : {}", id);
        previsionPsaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
