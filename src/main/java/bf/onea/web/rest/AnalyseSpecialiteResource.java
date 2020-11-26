package bf.onea.web.rest;

import bf.onea.service.AnalyseSpecialiteService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.AnalyseSpecialiteDTO;

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
 * REST controller for managing {@link bf.onea.domain.AnalyseSpecialite}.
 */
@RestController
@RequestMapping("/api")
public class AnalyseSpecialiteResource {

    private final Logger log = LoggerFactory.getLogger(AnalyseSpecialiteResource.class);

    private static final String ENTITY_NAME = "analyseSpecialite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnalyseSpecialiteService analyseSpecialiteService;

    public AnalyseSpecialiteResource(AnalyseSpecialiteService analyseSpecialiteService) {
        this.analyseSpecialiteService = analyseSpecialiteService;
    }

    /**
     * {@code POST  /analyse-specialites} : Create a new analyseSpecialite.
     *
     * @param analyseSpecialiteDTO the analyseSpecialiteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new analyseSpecialiteDTO, or with status {@code 400 (Bad Request)} if the analyseSpecialite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/analyse-specialites")
    public ResponseEntity<AnalyseSpecialiteDTO> createAnalyseSpecialite(@RequestBody AnalyseSpecialiteDTO analyseSpecialiteDTO) throws URISyntaxException {
        log.debug("REST request to save AnalyseSpecialite : {}", analyseSpecialiteDTO);
        if (analyseSpecialiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new analyseSpecialite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnalyseSpecialiteDTO result = analyseSpecialiteService.save(analyseSpecialiteDTO);
        return ResponseEntity.created(new URI("/api/analyse-specialites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /analyse-specialites} : Updates an existing analyseSpecialite.
     *
     * @param analyseSpecialiteDTO the analyseSpecialiteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated analyseSpecialiteDTO,
     * or with status {@code 400 (Bad Request)} if the analyseSpecialiteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the analyseSpecialiteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/analyse-specialites")
    public ResponseEntity<AnalyseSpecialiteDTO> updateAnalyseSpecialite(@RequestBody AnalyseSpecialiteDTO analyseSpecialiteDTO) throws URISyntaxException {
        log.debug("REST request to update AnalyseSpecialite : {}", analyseSpecialiteDTO);
        if (analyseSpecialiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnalyseSpecialiteDTO result = analyseSpecialiteService.save(analyseSpecialiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, analyseSpecialiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /analyse-specialites} : get all the analyseSpecialites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of analyseSpecialites in body.
     */
    @GetMapping("/analyse-specialites")
    public ResponseEntity<List<AnalyseSpecialiteDTO>> getAllAnalyseSpecialites(Pageable pageable) {
        log.debug("REST request to get a page of AnalyseSpecialites");
        Page<AnalyseSpecialiteDTO> page = analyseSpecialiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /analyse-specialites/:id} : get the "id" analyseSpecialite.
     *
     * @param id the id of the analyseSpecialiteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the analyseSpecialiteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/analyse-specialites/{id}")
    public ResponseEntity<AnalyseSpecialiteDTO> getAnalyseSpecialite(@PathVariable Long id) {
        log.debug("REST request to get AnalyseSpecialite : {}", id);
        Optional<AnalyseSpecialiteDTO> analyseSpecialiteDTO = analyseSpecialiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(analyseSpecialiteDTO);
    }

    /**
     * {@code DELETE  /analyse-specialites/:id} : delete the "id" analyseSpecialite.
     *
     * @param id the id of the analyseSpecialiteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/analyse-specialites/{id}")
    public ResponseEntity<Void> deleteAnalyseSpecialite(@PathVariable Long id) {
        log.debug("REST request to delete AnalyseSpecialite : {}", id);
        analyseSpecialiteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
