package bf.onea.web.rest;

import bf.onea.service.GeoSectionService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoSectionDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoSection}.
 */
@RestController
@RequestMapping("/api")
public class GeoSectionResource {

    private final Logger log = LoggerFactory.getLogger(GeoSectionResource.class);

    private static final String ENTITY_NAME = "geoSection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoSectionService geoSectionService;

    public GeoSectionResource(GeoSectionService geoSectionService) {
        this.geoSectionService = geoSectionService;
    }

    /**
     * {@code POST  /geo-sections} : Create a new geoSection.
     *
     * @param geoSectionDTO the geoSectionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoSectionDTO, or with status {@code 400 (Bad Request)} if the geoSection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-sections")
    public ResponseEntity<GeoSectionDTO> createGeoSection(@RequestBody GeoSectionDTO geoSectionDTO) throws URISyntaxException {
        log.debug("REST request to save GeoSection : {}", geoSectionDTO);
        if (geoSectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoSection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoSectionDTO result = geoSectionService.save(geoSectionDTO);
        return ResponseEntity.created(new URI("/api/geo-sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-sections} : Updates an existing geoSection.
     *
     * @param geoSectionDTO the geoSectionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoSectionDTO,
     * or with status {@code 400 (Bad Request)} if the geoSectionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoSectionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-sections")
    public ResponseEntity<GeoSectionDTO> updateGeoSection(@RequestBody GeoSectionDTO geoSectionDTO) throws URISyntaxException {
        log.debug("REST request to update GeoSection : {}", geoSectionDTO);
        if (geoSectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoSectionDTO result = geoSectionService.save(geoSectionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoSectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-sections} : get all the geoSections.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoSections in body.
     */
    @GetMapping("/geo-sections")
    public ResponseEntity<List<GeoSectionDTO>> getAllGeoSections(Pageable pageable) {
        log.debug("REST request to get a page of GeoSections");
        Page<GeoSectionDTO> page = geoSectionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-sections/:id} : get the "id" geoSection.
     *
     * @param id the id of the geoSectionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoSectionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-sections/{id}")
    public ResponseEntity<GeoSectionDTO> getGeoSection(@PathVariable Long id) {
        log.debug("REST request to get GeoSection : {}", id);
        Optional<GeoSectionDTO> geoSectionDTO = geoSectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoSectionDTO);
    }

    /**
     * {@code DELETE  /geo-sections/:id} : delete the "id" geoSection.
     *
     * @param id the id of the geoSectionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-sections/{id}")
    public ResponseEntity<Void> deleteGeoSection(@PathVariable Long id) {
        log.debug("REST request to delete GeoSection : {}", id);
        geoSectionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
