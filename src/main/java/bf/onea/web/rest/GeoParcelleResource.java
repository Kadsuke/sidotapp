package bf.onea.web.rest;

import bf.onea.service.GeoParcelleService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoParcelleDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoParcelle}.
 */
@RestController
@RequestMapping("/api")
public class GeoParcelleResource {

    private final Logger log = LoggerFactory.getLogger(GeoParcelleResource.class);

    private static final String ENTITY_NAME = "geoParcelle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoParcelleService geoParcelleService;

    public GeoParcelleResource(GeoParcelleService geoParcelleService) {
        this.geoParcelleService = geoParcelleService;
    }

    /**
     * {@code POST  /geo-parcelles} : Create a new geoParcelle.
     *
     * @param geoParcelleDTO the geoParcelleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoParcelleDTO, or with status {@code 400 (Bad Request)} if the geoParcelle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-parcelles")
    public ResponseEntity<GeoParcelleDTO> createGeoParcelle(@Valid @RequestBody GeoParcelleDTO geoParcelleDTO) throws URISyntaxException {
        log.debug("REST request to save GeoParcelle : {}", geoParcelleDTO);
        if (geoParcelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoParcelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoParcelleDTO result = geoParcelleService.save(geoParcelleDTO);
        return ResponseEntity.created(new URI("/api/geo-parcelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-parcelles} : Updates an existing geoParcelle.
     *
     * @param geoParcelleDTO the geoParcelleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoParcelleDTO,
     * or with status {@code 400 (Bad Request)} if the geoParcelleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoParcelleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-parcelles")
    public ResponseEntity<GeoParcelleDTO> updateGeoParcelle(@Valid @RequestBody GeoParcelleDTO geoParcelleDTO) throws URISyntaxException {
        log.debug("REST request to update GeoParcelle : {}", geoParcelleDTO);
        if (geoParcelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoParcelleDTO result = geoParcelleService.save(geoParcelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoParcelleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-parcelles} : get all the geoParcelles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoParcelles in body.
     */
    @GetMapping("/geo-parcelles")
    public ResponseEntity<List<GeoParcelleDTO>> getAllGeoParcelles(Pageable pageable) {
        log.debug("REST request to get a page of GeoParcelles");
        Page<GeoParcelleDTO> page = geoParcelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-parcelles/:id} : get the "id" geoParcelle.
     *
     * @param id the id of the geoParcelleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoParcelleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-parcelles/{id}")
    public ResponseEntity<GeoParcelleDTO> getGeoParcelle(@PathVariable Long id) {
        log.debug("REST request to get GeoParcelle : {}", id);
        Optional<GeoParcelleDTO> geoParcelleDTO = geoParcelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoParcelleDTO);
    }

    /**
     * {@code DELETE  /geo-parcelles/:id} : delete the "id" geoParcelle.
     *
     * @param id the id of the geoParcelleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-parcelles/{id}")
    public ResponseEntity<Void> deleteGeoParcelle(@PathVariable Long id) {
        log.debug("REST request to delete GeoParcelle : {}", id);
        geoParcelleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
