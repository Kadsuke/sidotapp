package bf.onea.web.rest;

import bf.onea.service.GeoTypeCommuneService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoTypeCommuneDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoTypeCommune}.
 */
@RestController
@RequestMapping("/api")
public class GeoTypeCommuneResource {

    private final Logger log = LoggerFactory.getLogger(GeoTypeCommuneResource.class);

    private static final String ENTITY_NAME = "geoTypeCommune";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoTypeCommuneService geoTypeCommuneService;

    public GeoTypeCommuneResource(GeoTypeCommuneService geoTypeCommuneService) {
        this.geoTypeCommuneService = geoTypeCommuneService;
    }

    /**
     * {@code POST  /geo-type-communes} : Create a new geoTypeCommune.
     *
     * @param geoTypeCommuneDTO the geoTypeCommuneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoTypeCommuneDTO, or with status {@code 400 (Bad Request)} if the geoTypeCommune has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-type-communes")
    public ResponseEntity<GeoTypeCommuneDTO> createGeoTypeCommune(@RequestBody GeoTypeCommuneDTO geoTypeCommuneDTO) throws URISyntaxException {
        log.debug("REST request to save GeoTypeCommune : {}", geoTypeCommuneDTO);
        if (geoTypeCommuneDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoTypeCommune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoTypeCommuneDTO result = geoTypeCommuneService.save(geoTypeCommuneDTO);
        return ResponseEntity.created(new URI("/api/geo-type-communes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-type-communes} : Updates an existing geoTypeCommune.
     *
     * @param geoTypeCommuneDTO the geoTypeCommuneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoTypeCommuneDTO,
     * or with status {@code 400 (Bad Request)} if the geoTypeCommuneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoTypeCommuneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-type-communes")
    public ResponseEntity<GeoTypeCommuneDTO> updateGeoTypeCommune(@RequestBody GeoTypeCommuneDTO geoTypeCommuneDTO) throws URISyntaxException {
        log.debug("REST request to update GeoTypeCommune : {}", geoTypeCommuneDTO);
        if (geoTypeCommuneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoTypeCommuneDTO result = geoTypeCommuneService.save(geoTypeCommuneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoTypeCommuneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-type-communes} : get all the geoTypeCommunes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoTypeCommunes in body.
     */
    @GetMapping("/geo-type-communes")
    public ResponseEntity<List<GeoTypeCommuneDTO>> getAllGeoTypeCommunes(Pageable pageable) {
        log.debug("REST request to get a page of GeoTypeCommunes");
        Page<GeoTypeCommuneDTO> page = geoTypeCommuneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-type-communes/:id} : get the "id" geoTypeCommune.
     *
     * @param id the id of the geoTypeCommuneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoTypeCommuneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-type-communes/{id}")
    public ResponseEntity<GeoTypeCommuneDTO> getGeoTypeCommune(@PathVariable Long id) {
        log.debug("REST request to get GeoTypeCommune : {}", id);
        Optional<GeoTypeCommuneDTO> geoTypeCommuneDTO = geoTypeCommuneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoTypeCommuneDTO);
    }

    /**
     * {@code DELETE  /geo-type-communes/:id} : delete the "id" geoTypeCommune.
     *
     * @param id the id of the geoTypeCommuneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-type-communes/{id}")
    public ResponseEntity<Void> deleteGeoTypeCommune(@PathVariable Long id) {
        log.debug("REST request to delete GeoTypeCommune : {}", id);
        geoTypeCommuneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
