package bf.onea.web.rest;

import bf.onea.service.DirectionRegionaleService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.DirectionRegionaleDTO;

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
 * REST controller for managing {@link bf.onea.domain.DirectionRegionale}.
 */
@RestController
@RequestMapping("/api")
public class DirectionRegionaleResource {

    private final Logger log = LoggerFactory.getLogger(DirectionRegionaleResource.class);

    private static final String ENTITY_NAME = "directionRegionale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectionRegionaleService directionRegionaleService;

    public DirectionRegionaleResource(DirectionRegionaleService directionRegionaleService) {
        this.directionRegionaleService = directionRegionaleService;
    }

    /**
     * {@code POST  /direction-regionales} : Create a new directionRegionale.
     *
     * @param directionRegionaleDTO the directionRegionaleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new directionRegionaleDTO, or with status {@code 400 (Bad Request)} if the directionRegionale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/direction-regionales")
    public ResponseEntity<DirectionRegionaleDTO> createDirectionRegionale(@RequestBody DirectionRegionaleDTO directionRegionaleDTO) throws URISyntaxException {
        log.debug("REST request to save DirectionRegionale : {}", directionRegionaleDTO);
        if (directionRegionaleDTO.getId() != null) {
            throw new BadRequestAlertException("A new directionRegionale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DirectionRegionaleDTO result = directionRegionaleService.save(directionRegionaleDTO);
        return ResponseEntity.created(new URI("/api/direction-regionales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /direction-regionales} : Updates an existing directionRegionale.
     *
     * @param directionRegionaleDTO the directionRegionaleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directionRegionaleDTO,
     * or with status {@code 400 (Bad Request)} if the directionRegionaleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the directionRegionaleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/direction-regionales")
    public ResponseEntity<DirectionRegionaleDTO> updateDirectionRegionale(@RequestBody DirectionRegionaleDTO directionRegionaleDTO) throws URISyntaxException {
        log.debug("REST request to update DirectionRegionale : {}", directionRegionaleDTO);
        if (directionRegionaleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DirectionRegionaleDTO result = directionRegionaleService.save(directionRegionaleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, directionRegionaleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /direction-regionales} : get all the directionRegionales.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directionRegionales in body.
     */
    @GetMapping("/direction-regionales")
    public ResponseEntity<List<DirectionRegionaleDTO>> getAllDirectionRegionales(Pageable pageable) {
        log.debug("REST request to get a page of DirectionRegionales");
        Page<DirectionRegionaleDTO> page = directionRegionaleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /direction-regionales/:id} : get the "id" directionRegionale.
     *
     * @param id the id of the directionRegionaleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directionRegionaleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direction-regionales/{id}")
    public ResponseEntity<DirectionRegionaleDTO> getDirectionRegionale(@PathVariable Long id) {
        log.debug("REST request to get DirectionRegionale : {}", id);
        Optional<DirectionRegionaleDTO> directionRegionaleDTO = directionRegionaleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(directionRegionaleDTO);
    }

    /**
     * {@code DELETE  /direction-regionales/:id} : delete the "id" directionRegionale.
     *
     * @param id the id of the directionRegionaleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/direction-regionales/{id}")
    public ResponseEntity<Void> deleteDirectionRegionale(@PathVariable Long id) {
        log.debug("REST request to delete DirectionRegionale : {}", id);
        directionRegionaleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
