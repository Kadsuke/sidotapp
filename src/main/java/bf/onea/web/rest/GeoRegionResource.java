package bf.onea.web.rest;

import bf.onea.service.GeoRegionService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoRegionDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoRegion}.
 */
@RestController
@RequestMapping("/api")
public class GeoRegionResource {

    private final Logger log = LoggerFactory.getLogger(GeoRegionResource.class);

    private static final String ENTITY_NAME = "geoRegion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoRegionService geoRegionService;

    public GeoRegionResource(GeoRegionService geoRegionService) {
        this.geoRegionService = geoRegionService;
    }

    /**
     * {@code POST  /geo-regions} : Create a new geoRegion.
     *
     * @param geoRegionDTO the geoRegionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoRegionDTO, or with status {@code 400 (Bad Request)} if the geoRegion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-regions")
    public ResponseEntity<GeoRegionDTO> createGeoRegion(@Valid @RequestBody GeoRegionDTO geoRegionDTO) throws URISyntaxException {
        log.debug("REST request to save GeoRegion : {}", geoRegionDTO);
        if (geoRegionDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoRegion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoRegionDTO result = geoRegionService.save(geoRegionDTO);
        return ResponseEntity.created(new URI("/api/geo-regions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-regions} : Updates an existing geoRegion.
     *
     * @param geoRegionDTO the geoRegionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoRegionDTO,
     * or with status {@code 400 (Bad Request)} if the geoRegionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoRegionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-regions")
    public ResponseEntity<GeoRegionDTO> updateGeoRegion(@Valid @RequestBody GeoRegionDTO geoRegionDTO) throws URISyntaxException {
        log.debug("REST request to update GeoRegion : {}", geoRegionDTO);
        if (geoRegionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoRegionDTO result = geoRegionService.save(geoRegionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoRegionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-regions} : get all the geoRegions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoRegions in body.
     */
    @GetMapping("/geo-regions")
    public ResponseEntity<List<GeoRegionDTO>> getAllGeoRegions(Pageable pageable) {
        log.debug("REST request to get a page of GeoRegions");
        Page<GeoRegionDTO> page = geoRegionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-regions/:id} : get the "id" geoRegion.
     *
     * @param id the id of the geoRegionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoRegionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-regions/{id}")
    public ResponseEntity<GeoRegionDTO> getGeoRegion(@PathVariable Long id) {
        log.debug("REST request to get GeoRegion : {}", id);
        Optional<GeoRegionDTO> geoRegionDTO = geoRegionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoRegionDTO);
    }

    /**
     * {@code DELETE  /geo-regions/:id} : delete the "id" geoRegion.
     *
     * @param id the id of the geoRegionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-regions/{id}")
    public ResponseEntity<Void> deleteGeoRegion(@PathVariable Long id) {
        log.debug("REST request to delete GeoRegion : {}", id);
        geoRegionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
