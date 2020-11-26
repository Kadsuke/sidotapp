package bf.onea.web.rest;

import bf.onea.service.TacheronsService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TacheronsDTO;

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
 * REST controller for managing {@link bf.onea.domain.Tacherons}.
 */
@RestController
@RequestMapping("/api")
public class TacheronsResource {

    private final Logger log = LoggerFactory.getLogger(TacheronsResource.class);

    private static final String ENTITY_NAME = "tacherons";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TacheronsService tacheronsService;

    public TacheronsResource(TacheronsService tacheronsService) {
        this.tacheronsService = tacheronsService;
    }

    /**
     * {@code POST  /tacherons} : Create a new tacherons.
     *
     * @param tacheronsDTO the tacheronsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tacheronsDTO, or with status {@code 400 (Bad Request)} if the tacherons has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tacherons")
    public ResponseEntity<TacheronsDTO> createTacherons(@RequestBody TacheronsDTO tacheronsDTO) throws URISyntaxException {
        log.debug("REST request to save Tacherons : {}", tacheronsDTO);
        if (tacheronsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tacherons cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TacheronsDTO result = tacheronsService.save(tacheronsDTO);
        return ResponseEntity.created(new URI("/api/tacherons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tacherons} : Updates an existing tacherons.
     *
     * @param tacheronsDTO the tacheronsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tacheronsDTO,
     * or with status {@code 400 (Bad Request)} if the tacheronsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tacheronsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tacherons")
    public ResponseEntity<TacheronsDTO> updateTacherons(@RequestBody TacheronsDTO tacheronsDTO) throws URISyntaxException {
        log.debug("REST request to update Tacherons : {}", tacheronsDTO);
        if (tacheronsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TacheronsDTO result = tacheronsService.save(tacheronsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tacheronsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tacherons} : get all the tacherons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tacherons in body.
     */
    @GetMapping("/tacherons")
    public ResponseEntity<List<TacheronsDTO>> getAllTacherons(Pageable pageable) {
        log.debug("REST request to get a page of Tacherons");
        Page<TacheronsDTO> page = tacheronsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tacherons/:id} : get the "id" tacherons.
     *
     * @param id the id of the tacheronsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tacheronsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tacherons/{id}")
    public ResponseEntity<TacheronsDTO> getTacherons(@PathVariable Long id) {
        log.debug("REST request to get Tacherons : {}", id);
        Optional<TacheronsDTO> tacheronsDTO = tacheronsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tacheronsDTO);
    }

    /**
     * {@code DELETE  /tacherons/:id} : delete the "id" tacherons.
     *
     * @param id the id of the tacheronsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tacherons/{id}")
    public ResponseEntity<Void> deleteTacherons(@PathVariable Long id) {
        log.debug("REST request to delete Tacherons : {}", id);
        tacheronsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
