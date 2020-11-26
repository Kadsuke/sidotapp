package bf.onea.web.rest;

import bf.onea.service.CentreRegroupementService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.CentreRegroupementDTO;

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
 * REST controller for managing {@link bf.onea.domain.CentreRegroupement}.
 */
@RestController
@RequestMapping("/api")
public class CentreRegroupementResource {

    private final Logger log = LoggerFactory.getLogger(CentreRegroupementResource.class);

    private static final String ENTITY_NAME = "centreRegroupement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CentreRegroupementService centreRegroupementService;

    public CentreRegroupementResource(CentreRegroupementService centreRegroupementService) {
        this.centreRegroupementService = centreRegroupementService;
    }

    /**
     * {@code POST  /centre-regroupements} : Create a new centreRegroupement.
     *
     * @param centreRegroupementDTO the centreRegroupementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centreRegroupementDTO, or with status {@code 400 (Bad Request)} if the centreRegroupement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/centre-regroupements")
    public ResponseEntity<CentreRegroupementDTO> createCentreRegroupement(@RequestBody CentreRegroupementDTO centreRegroupementDTO) throws URISyntaxException {
        log.debug("REST request to save CentreRegroupement : {}", centreRegroupementDTO);
        if (centreRegroupementDTO.getId() != null) {
            throw new BadRequestAlertException("A new centreRegroupement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentreRegroupementDTO result = centreRegroupementService.save(centreRegroupementDTO);
        return ResponseEntity.created(new URI("/api/centre-regroupements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /centre-regroupements} : Updates an existing centreRegroupement.
     *
     * @param centreRegroupementDTO the centreRegroupementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centreRegroupementDTO,
     * or with status {@code 400 (Bad Request)} if the centreRegroupementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centreRegroupementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/centre-regroupements")
    public ResponseEntity<CentreRegroupementDTO> updateCentreRegroupement(@RequestBody CentreRegroupementDTO centreRegroupementDTO) throws URISyntaxException {
        log.debug("REST request to update CentreRegroupement : {}", centreRegroupementDTO);
        if (centreRegroupementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentreRegroupementDTO result = centreRegroupementService.save(centreRegroupementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centreRegroupementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /centre-regroupements} : get all the centreRegroupements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centreRegroupements in body.
     */
    @GetMapping("/centre-regroupements")
    public ResponseEntity<List<CentreRegroupementDTO>> getAllCentreRegroupements(Pageable pageable) {
        log.debug("REST request to get a page of CentreRegroupements");
        Page<CentreRegroupementDTO> page = centreRegroupementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /centre-regroupements/:id} : get the "id" centreRegroupement.
     *
     * @param id the id of the centreRegroupementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centreRegroupementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/centre-regroupements/{id}")
    public ResponseEntity<CentreRegroupementDTO> getCentreRegroupement(@PathVariable Long id) {
        log.debug("REST request to get CentreRegroupement : {}", id);
        Optional<CentreRegroupementDTO> centreRegroupementDTO = centreRegroupementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centreRegroupementDTO);
    }

    /**
     * {@code DELETE  /centre-regroupements/:id} : delete the "id" centreRegroupement.
     *
     * @param id the id of the centreRegroupementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/centre-regroupements/{id}")
    public ResponseEntity<Void> deleteCentreRegroupement(@PathVariable Long id) {
        log.debug("REST request to delete CentreRegroupement : {}", id);
        centreRegroupementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
