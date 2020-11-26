package bf.onea.web.rest;

import bf.onea.service.ModeEvacExcretaService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.ModeEvacExcretaDTO;

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
 * REST controller for managing {@link bf.onea.domain.ModeEvacExcreta}.
 */
@RestController
@RequestMapping("/api")
public class ModeEvacExcretaResource {

    private final Logger log = LoggerFactory.getLogger(ModeEvacExcretaResource.class);

    private static final String ENTITY_NAME = "modeEvacExcreta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModeEvacExcretaService modeEvacExcretaService;

    public ModeEvacExcretaResource(ModeEvacExcretaService modeEvacExcretaService) {
        this.modeEvacExcretaService = modeEvacExcretaService;
    }

    /**
     * {@code POST  /mode-evac-excretas} : Create a new modeEvacExcreta.
     *
     * @param modeEvacExcretaDTO the modeEvacExcretaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modeEvacExcretaDTO, or with status {@code 400 (Bad Request)} if the modeEvacExcreta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mode-evac-excretas")
    public ResponseEntity<ModeEvacExcretaDTO> createModeEvacExcreta(@RequestBody ModeEvacExcretaDTO modeEvacExcretaDTO) throws URISyntaxException {
        log.debug("REST request to save ModeEvacExcreta : {}", modeEvacExcretaDTO);
        if (modeEvacExcretaDTO.getId() != null) {
            throw new BadRequestAlertException("A new modeEvacExcreta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModeEvacExcretaDTO result = modeEvacExcretaService.save(modeEvacExcretaDTO);
        return ResponseEntity.created(new URI("/api/mode-evac-excretas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mode-evac-excretas} : Updates an existing modeEvacExcreta.
     *
     * @param modeEvacExcretaDTO the modeEvacExcretaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modeEvacExcretaDTO,
     * or with status {@code 400 (Bad Request)} if the modeEvacExcretaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modeEvacExcretaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mode-evac-excretas")
    public ResponseEntity<ModeEvacExcretaDTO> updateModeEvacExcreta(@RequestBody ModeEvacExcretaDTO modeEvacExcretaDTO) throws URISyntaxException {
        log.debug("REST request to update ModeEvacExcreta : {}", modeEvacExcretaDTO);
        if (modeEvacExcretaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModeEvacExcretaDTO result = modeEvacExcretaService.save(modeEvacExcretaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modeEvacExcretaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mode-evac-excretas} : get all the modeEvacExcretas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modeEvacExcretas in body.
     */
    @GetMapping("/mode-evac-excretas")
    public ResponseEntity<List<ModeEvacExcretaDTO>> getAllModeEvacExcretas(Pageable pageable) {
        log.debug("REST request to get a page of ModeEvacExcretas");
        Page<ModeEvacExcretaDTO> page = modeEvacExcretaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mode-evac-excretas/:id} : get the "id" modeEvacExcreta.
     *
     * @param id the id of the modeEvacExcretaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modeEvacExcretaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mode-evac-excretas/{id}")
    public ResponseEntity<ModeEvacExcretaDTO> getModeEvacExcreta(@PathVariable Long id) {
        log.debug("REST request to get ModeEvacExcreta : {}", id);
        Optional<ModeEvacExcretaDTO> modeEvacExcretaDTO = modeEvacExcretaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modeEvacExcretaDTO);
    }

    /**
     * {@code DELETE  /mode-evac-excretas/:id} : delete the "id" modeEvacExcreta.
     *
     * @param id the id of the modeEvacExcretaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mode-evac-excretas/{id}")
    public ResponseEntity<Void> deleteModeEvacExcreta(@PathVariable Long id) {
        log.debug("REST request to delete ModeEvacExcreta : {}", id);
        modeEvacExcretaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
