package bf.onea.web.rest;

import bf.onea.service.EtatEquipementService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.EtatEquipementDTO;

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
 * REST controller for managing {@link bf.onea.domain.EtatEquipement}.
 */
@RestController
@RequestMapping("/api")
public class EtatEquipementResource {

    private final Logger log = LoggerFactory.getLogger(EtatEquipementResource.class);

    private static final String ENTITY_NAME = "etatEquipement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatEquipementService etatEquipementService;

    public EtatEquipementResource(EtatEquipementService etatEquipementService) {
        this.etatEquipementService = etatEquipementService;
    }

    /**
     * {@code POST  /etat-equipements} : Create a new etatEquipement.
     *
     * @param etatEquipementDTO the etatEquipementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etatEquipementDTO, or with status {@code 400 (Bad Request)} if the etatEquipement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etat-equipements")
    public ResponseEntity<EtatEquipementDTO> createEtatEquipement(@Valid @RequestBody EtatEquipementDTO etatEquipementDTO) throws URISyntaxException {
        log.debug("REST request to save EtatEquipement : {}", etatEquipementDTO);
        if (etatEquipementDTO.getId() != null) {
            throw new BadRequestAlertException("A new etatEquipement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatEquipementDTO result = etatEquipementService.save(etatEquipementDTO);
        return ResponseEntity.created(new URI("/api/etat-equipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etat-equipements} : Updates an existing etatEquipement.
     *
     * @param etatEquipementDTO the etatEquipementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etatEquipementDTO,
     * or with status {@code 400 (Bad Request)} if the etatEquipementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etatEquipementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etat-equipements")
    public ResponseEntity<EtatEquipementDTO> updateEtatEquipement(@Valid @RequestBody EtatEquipementDTO etatEquipementDTO) throws URISyntaxException {
        log.debug("REST request to update EtatEquipement : {}", etatEquipementDTO);
        if (etatEquipementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtatEquipementDTO result = etatEquipementService.save(etatEquipementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etatEquipementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etat-equipements} : get all the etatEquipements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etatEquipements in body.
     */
    @GetMapping("/etat-equipements")
    public ResponseEntity<List<EtatEquipementDTO>> getAllEtatEquipements(Pageable pageable) {
        log.debug("REST request to get a page of EtatEquipements");
        Page<EtatEquipementDTO> page = etatEquipementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etat-equipements/:id} : get the "id" etatEquipement.
     *
     * @param id the id of the etatEquipementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etatEquipementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etat-equipements/{id}")
    public ResponseEntity<EtatEquipementDTO> getEtatEquipement(@PathVariable Long id) {
        log.debug("REST request to get EtatEquipement : {}", id);
        Optional<EtatEquipementDTO> etatEquipementDTO = etatEquipementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etatEquipementDTO);
    }

    /**
     * {@code DELETE  /etat-equipements/:id} : delete the "id" etatEquipement.
     *
     * @param id the id of the etatEquipementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etat-equipements/{id}")
    public ResponseEntity<Void> deleteEtatEquipement(@PathVariable Long id) {
        log.debug("REST request to delete EtatEquipement : {}", id);
        etatEquipementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
