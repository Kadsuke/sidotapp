package bf.onea.web.rest;

import bf.onea.service.PrestataireService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.PrestataireDTO;

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
 * REST controller for managing {@link bf.onea.domain.Prestataire}.
 */
@RestController
@RequestMapping("/api")
public class PrestataireResource {

    private final Logger log = LoggerFactory.getLogger(PrestataireResource.class);

    private static final String ENTITY_NAME = "prestataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrestataireService prestataireService;

    public PrestataireResource(PrestataireService prestataireService) {
        this.prestataireService = prestataireService;
    }

    /**
     * {@code POST  /prestataires} : Create a new prestataire.
     *
     * @param prestataireDTO the prestataireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prestataireDTO, or with status {@code 400 (Bad Request)} if the prestataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prestataires")
    public ResponseEntity<PrestataireDTO> createPrestataire(@Valid @RequestBody PrestataireDTO prestataireDTO) throws URISyntaxException {
        log.debug("REST request to save Prestataire : {}", prestataireDTO);
        if (prestataireDTO.getId() != null) {
            throw new BadRequestAlertException("A new prestataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrestataireDTO result = prestataireService.save(prestataireDTO);
        return ResponseEntity.created(new URI("/api/prestataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prestataires} : Updates an existing prestataire.
     *
     * @param prestataireDTO the prestataireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prestataireDTO,
     * or with status {@code 400 (Bad Request)} if the prestataireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prestataireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prestataires")
    public ResponseEntity<PrestataireDTO> updatePrestataire(@Valid @RequestBody PrestataireDTO prestataireDTO) throws URISyntaxException {
        log.debug("REST request to update Prestataire : {}", prestataireDTO);
        if (prestataireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrestataireDTO result = prestataireService.save(prestataireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prestataireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prestataires} : get all the prestataires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prestataires in body.
     */
    @GetMapping("/prestataires")
    public ResponseEntity<List<PrestataireDTO>> getAllPrestataires(Pageable pageable) {
        log.debug("REST request to get a page of Prestataires");
        Page<PrestataireDTO> page = prestataireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /prestataires/:id} : get the "id" prestataire.
     *
     * @param id the id of the prestataireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prestataireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prestataires/{id}")
    public ResponseEntity<PrestataireDTO> getPrestataire(@PathVariable Long id) {
        log.debug("REST request to get Prestataire : {}", id);
        Optional<PrestataireDTO> prestataireDTO = prestataireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prestataireDTO);
    }

    /**
     * {@code DELETE  /prestataires/:id} : delete the "id" prestataire.
     *
     * @param id the id of the prestataireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prestataires/{id}")
    public ResponseEntity<Void> deletePrestataire(@PathVariable Long id) {
        log.debug("REST request to delete Prestataire : {}", id);
        prestataireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
