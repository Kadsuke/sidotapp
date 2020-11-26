package bf.onea.web.rest;

import bf.onea.service.EtatProjetService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.EtatProjetDTO;

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
 * REST controller for managing {@link bf.onea.domain.EtatProjet}.
 */
@RestController
@RequestMapping("/api")
public class EtatProjetResource {

    private final Logger log = LoggerFactory.getLogger(EtatProjetResource.class);

    private static final String ENTITY_NAME = "etatProjet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatProjetService etatProjetService;

    public EtatProjetResource(EtatProjetService etatProjetService) {
        this.etatProjetService = etatProjetService;
    }

    /**
     * {@code POST  /etat-projets} : Create a new etatProjet.
     *
     * @param etatProjetDTO the etatProjetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatProjetDTO, or with status {@code 400 (Bad Request)} if the etatProjet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-projets")
    public ResponseEntity<EtatProjetDTO> createEtatProjet(@RequestBody EtatProjetDTO etatProjetDTO) throws URISyntaxException {
        log.debug("REST request to save EtatProjet : {}", etatProjetDTO);
        if (etatProjetDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatProjet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatProjetDTO result = etatProjetService.save(etatProjetDTO);
        return ResponseEntity.created(new URI("/api/etat-projets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-projets} : Updates an existing etatProjet.
     *
     * @param etatProjetDTO the etatProjetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatProjetDTO,
     * or with status {@code 400 (Bad Request)} if the etatProjetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatProjetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-projets")
    public ResponseEntity<EtatProjetDTO> updateEtatProjet(@RequestBody EtatProjetDTO etatProjetDTO) throws URISyntaxException {
        log.debug("REST request to update EtatProjet : {}", etatProjetDTO);
        if (etatProjetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatProjetDTO result = etatProjetService.save(etatProjetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatProjetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-projets} : get all the etatProjets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatProjets in body.
     */
    @GetMapping("/etat-projets")
    public ResponseEntity<List<EtatProjetDTO>> getAllEtatProjets(Pageable pageable) {
        log.debug("REST request to get a page of EtatProjets");
        Page<EtatProjetDTO> page = etatProjetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etat-projets/:id} : get the "id" etatProjet.
     *
     * @param id the id of the etatProjetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatProjetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-projets/{id}")
    public ResponseEntity<EtatProjetDTO> getEtatProjet(@PathVariable Long id) {
        log.debug("REST request to get EtatProjet : {}", id);
        Optional<EtatProjetDTO> etatProjetDTO = etatProjetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatProjetDTO);
    }

    /**
     * {@code DELETE  /etat-projets/:id} : delete the "id" etatProjet.
     *
     * @param id the id of the etatProjetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-projets/{id}")
    public ResponseEntity<Void> deleteEtatProjet(@PathVariable Long id) {
        log.debug("REST request to delete EtatProjet : {}", id);
        etatProjetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
