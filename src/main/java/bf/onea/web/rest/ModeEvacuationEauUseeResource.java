package bf.onea.web.rest;

import bf.onea.service.ModeEvacuationEauUseeService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.ModeEvacuationEauUseeDTO;

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
 * REST controller for managing {@link bf.onea.domain.ModeEvacuationEauUsee}.
 */
@RestController
@RequestMapping("/api")
public class ModeEvacuationEauUseeResource {

    private final Logger log = LoggerFactory.getLogger(ModeEvacuationEauUseeResource.class);

    private static final String ENTITY_NAME = "modeEvacuationEauUsee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModeEvacuationEauUseeService modeEvacuationEauUseeService;

    public ModeEvacuationEauUseeResource(ModeEvacuationEauUseeService modeEvacuationEauUseeService) {
        this.modeEvacuationEauUseeService = modeEvacuationEauUseeService;
    }

    /**
     * {@code POST  /mode-evacuation-eau-usees} : Create a new modeEvacuationEauUsee.
     *
     * @param modeEvacuationEauUseeDTO the modeEvacuationEauUseeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modeEvacuationEauUseeDTO, or with status {@code 400 (Bad Request)} if the modeEvacuationEauUsee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mode-evacuation-eau-usees")
    public ResponseEntity<ModeEvacuationEauUseeDTO> createModeEvacuationEauUsee(@Valid @RequestBody ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO) throws URISyntaxException {
        log.debug("REST request to save ModeEvacuationEauUsee : {}", modeEvacuationEauUseeDTO);
        if (modeEvacuationEauUseeDTO.getId() != null) {
            throw new BadRequestAlertException("A new modeEvacuationEauUsee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModeEvacuationEauUseeDTO result = modeEvacuationEauUseeService.save(modeEvacuationEauUseeDTO);
        return ResponseEntity.created(new URI("/api/mode-evacuation-eau-usees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mode-evacuation-eau-usees} : Updates an existing modeEvacuationEauUsee.
     *
     * @param modeEvacuationEauUseeDTO the modeEvacuationEauUseeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modeEvacuationEauUseeDTO,
     * or with status {@code 400 (Bad Request)} if the modeEvacuationEauUseeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modeEvacuationEauUseeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mode-evacuation-eau-usees")
    public ResponseEntity<ModeEvacuationEauUseeDTO> updateModeEvacuationEauUsee(@Valid @RequestBody ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO) throws URISyntaxException {
        log.debug("REST request to update ModeEvacuationEauUsee : {}", modeEvacuationEauUseeDTO);
        if (modeEvacuationEauUseeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModeEvacuationEauUseeDTO result = modeEvacuationEauUseeService.save(modeEvacuationEauUseeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modeEvacuationEauUseeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mode-evacuation-eau-usees} : get all the modeEvacuationEauUsees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modeEvacuationEauUsees in body.
     */
    @GetMapping("/mode-evacuation-eau-usees")
    public ResponseEntity<List<ModeEvacuationEauUseeDTO>> getAllModeEvacuationEauUsees(Pageable pageable) {
        log.debug("REST request to get a page of ModeEvacuationEauUsees");
        Page<ModeEvacuationEauUseeDTO> page = modeEvacuationEauUseeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mode-evacuation-eau-usees/:id} : get the "id" modeEvacuationEauUsee.
     *
     * @param id the id of the modeEvacuationEauUseeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modeEvacuationEauUseeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mode-evacuation-eau-usees/{id}")
    public ResponseEntity<ModeEvacuationEauUseeDTO> getModeEvacuationEauUsee(@PathVariable Long id) {
        log.debug("REST request to get ModeEvacuationEauUsee : {}", id);
        Optional<ModeEvacuationEauUseeDTO> modeEvacuationEauUseeDTO = modeEvacuationEauUseeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modeEvacuationEauUseeDTO);
    }

    /**
     * {@code DELETE  /mode-evacuation-eau-usees/:id} : delete the "id" modeEvacuationEauUsee.
     *
     * @param id the id of the modeEvacuationEauUseeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mode-evacuation-eau-usees/{id}")
    public ResponseEntity<Void> deleteModeEvacuationEauUsee(@PathVariable Long id) {
        log.debug("REST request to delete ModeEvacuationEauUsee : {}", id);
        modeEvacuationEauUseeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
