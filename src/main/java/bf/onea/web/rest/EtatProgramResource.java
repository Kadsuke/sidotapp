package bf.onea.web.rest;

import bf.onea.service.EtatProgramService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.EtatProgramDTO;

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
 * REST controller for managing {@link bf.onea.domain.EtatProgram}.
 */
@RestController
@RequestMapping("/api")
public class EtatProgramResource {

    private final Logger log = LoggerFactory.getLogger(EtatProgramResource.class);

    private static final String ENTITY_NAME = "etatProgram";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatProgramService etatProgramService;

    public EtatProgramResource(EtatProgramService etatProgramService) {
        this.etatProgramService = etatProgramService;
    }

    /**
     * {@code POST  /etat-programs} : Create a new etatProgram.
     *
     * @param etatProgramDTO the etatProgramDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatProgramDTO, or with status {@code 400 (Bad Request)} if the etatProgram has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-programs")
    public ResponseEntity<EtatProgramDTO> createEtatProgram(@Valid @RequestBody EtatProgramDTO etatProgramDTO) throws URISyntaxException {
        log.debug("REST request to save EtatProgram : {}", etatProgramDTO);
        if (etatProgramDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatProgram cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatProgramDTO result = etatProgramService.save(etatProgramDTO);
        return ResponseEntity.created(new URI("/api/etat-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-programs} : Updates an existing etatProgram.
     *
     * @param etatProgramDTO the etatProgramDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatProgramDTO,
     * or with status {@code 400 (Bad Request)} if the etatProgramDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatProgramDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-programs")
    public ResponseEntity<EtatProgramDTO> updateEtatProgram(@Valid @RequestBody EtatProgramDTO etatProgramDTO) throws URISyntaxException {
        log.debug("REST request to update EtatProgram : {}", etatProgramDTO);
        if (etatProgramDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatProgramDTO result = etatProgramService.save(etatProgramDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatProgramDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-programs} : get all the etatPrograms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatPrograms in body.
     */
    @GetMapping("/etat-programs")
    public ResponseEntity<List<EtatProgramDTO>> getAllEtatPrograms(Pageable pageable) {
        log.debug("REST request to get a page of EtatPrograms");
        Page<EtatProgramDTO> page = etatProgramService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etat-programs/:id} : get the "id" etatProgram.
     *
     * @param id the id of the etatProgramDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatProgramDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-programs/{id}")
    public ResponseEntity<EtatProgramDTO> getEtatProgram(@PathVariable Long id) {
        log.debug("REST request to get EtatProgram : {}", id);
        Optional<EtatProgramDTO> etatProgramDTO = etatProgramService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatProgramDTO);
    }

    /**
     * {@code DELETE  /etat-programs/:id} : delete the "id" etatProgram.
     *
     * @param id the id of the etatProgramDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-programs/{id}")
    public ResponseEntity<Void> deleteEtatProgram(@PathVariable Long id) {
        log.debug("REST request to delete EtatProgram : {}", id);
        etatProgramService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
