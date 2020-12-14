package bf.onea.web.rest;

import bf.onea.service.AnalyseParametreService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.AnalyseParametreDTO;

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
 * REST controller for managing {@link bf.onea.domain.AnalyseParametre}.
 */
@RestController
@RequestMapping("/api")
public class AnalyseParametreResource {

    private final Logger log = LoggerFactory.getLogger(AnalyseParametreResource.class);

    private static final String ENTITY_NAME = "analyseParametre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnalyseParametreService analyseParametreService;

    public AnalyseParametreResource(AnalyseParametreService analyseParametreService) {
        this.analyseParametreService = analyseParametreService;
    }

    /**
     * {@code POST  /analyse-parametres} : Create a new analyseParametre.
     *
     * @param analyseParametreDTO the analyseParametreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new analyseParametreDTO, or with status {@code 400 (Bad Request)} if the analyseParametre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/analyse-parametres")
    public ResponseEntity<AnalyseParametreDTO> createAnalyseParametre(@Valid @RequestBody AnalyseParametreDTO analyseParametreDTO) throws URISyntaxException {
        log.debug("REST request to save AnalyseParametre : {}", analyseParametreDTO);
        if (analyseParametreDTO.getId() != null) {
            throw new BadRequestAlertException("A new analyseParametre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalyseParametreDTO result = analyseParametreService.save(analyseParametreDTO);
        return ResponseEntity.created(new URI("/api/analyse-parametres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /analyse-parametres} : Updates an existing analyseParametre.
     *
     * @param analyseParametreDTO the analyseParametreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyseParametreDTO,
     * or with status {@code 400 (Bad Request)} if the analyseParametreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the analyseParametreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/analyse-parametres")
    public ResponseEntity<AnalyseParametreDTO> updateAnalyseParametre(@Valid @RequestBody AnalyseParametreDTO analyseParametreDTO) throws URISyntaxException {
        log.debug("REST request to update AnalyseParametre : {}", analyseParametreDTO);
        if (analyseParametreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnalyseParametreDTO result = analyseParametreService.save(analyseParametreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, analyseParametreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /analyse-parametres} : get all the analyseParametres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of analyseParametres in body.
     */
    @GetMapping("/analyse-parametres")
    public ResponseEntity<List<AnalyseParametreDTO>> getAllAnalyseParametres(Pageable pageable) {
        log.debug("REST request to get a page of AnalyseParametres");
        Page<AnalyseParametreDTO> page = analyseParametreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /analyse-parametres/:id} : get the "id" analyseParametre.
     *
     * @param id the id of the analyseParametreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the analyseParametreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/analyse-parametres/{id}")
    public ResponseEntity<AnalyseParametreDTO> getAnalyseParametre(@PathVariable Long id) {
        log.debug("REST request to get AnalyseParametre : {}", id);
        Optional<AnalyseParametreDTO> analyseParametreDTO = analyseParametreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(analyseParametreDTO);
    }

    /**
     * {@code DELETE  /analyse-parametres/:id} : delete the "id" analyseParametre.
     *
     * @param id the id of the analyseParametreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/analyse-parametres/{id}")
    public ResponseEntity<Void> deleteAnalyseParametre(@PathVariable Long id) {
        log.debug("REST request to delete AnalyseParametre : {}", id);
        analyseParametreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
