package bf.onea.web.rest;

import bf.onea.service.GeoCommuneService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoCommuneDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoCommune}.
 */
@RestController
@RequestMapping("/api")
public class GeoCommuneResource {

    private final Logger log = LoggerFactory.getLogger(GeoCommuneResource.class);

    private static final String ENTITY_NAME = "geoCommune";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoCommuneService geoCommuneService;

    public GeoCommuneResource(GeoCommuneService geoCommuneService) {
        this.geoCommuneService = geoCommuneService;
    }

    /**
     * {@code POST  /geo-communes} : Create a new geoCommune.
     *
     * @param geoCommuneDTO the geoCommuneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoCommuneDTO, or with status {@code 400 (Bad Request)} if the geoCommune has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-communes")
    public ResponseEntity<GeoCommuneDTO> createGeoCommune(@RequestBody GeoCommuneDTO geoCommuneDTO) throws URISyntaxException {
        log.debug("REST request to save GeoCommune : {}", geoCommuneDTO);
        if (geoCommuneDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoCommune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoCommuneDTO result = geoCommuneService.save(geoCommuneDTO);
        return ResponseEntity.created(new URI("/api/geo-communes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-communes} : Updates an existing geoCommune.
     *
     * @param geoCommuneDTO the geoCommuneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoCommuneDTO,
     * or with status {@code 400 (Bad Request)} if the geoCommuneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoCommuneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-communes")
    public ResponseEntity<GeoCommuneDTO> updateGeoCommune(@RequestBody GeoCommuneDTO geoCommuneDTO) throws URISyntaxException {
        log.debug("REST request to update GeoCommune : {}", geoCommuneDTO);
        if (geoCommuneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoCommuneDTO result = geoCommuneService.save(geoCommuneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoCommuneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-communes} : get all the geoCommunes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoCommunes in body.
     */
    @GetMapping("/geo-communes")
    public ResponseEntity<List<GeoCommuneDTO>> getAllGeoCommunes(Pageable pageable) {
        log.debug("REST request to get a page of GeoCommunes");
        Page<GeoCommuneDTO> page = geoCommuneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-communes/:id} : get the "id" geoCommune.
     *
     * @param id the id of the geoCommuneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoCommuneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-communes/{id}")
    public ResponseEntity<GeoCommuneDTO> getGeoCommune(@PathVariable Long id) {
        log.debug("REST request to get GeoCommune : {}", id);
        Optional<GeoCommuneDTO> geoCommuneDTO = geoCommuneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoCommuneDTO);
    }

    /**
     * {@code DELETE  /geo-communes/:id} : delete the "id" geoCommune.
     *
     * @param id the id of the geoCommuneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-communes/{id}")
    public ResponseEntity<Void> deleteGeoCommune(@PathVariable Long id) {
        log.debug("REST request to delete GeoCommune : {}", id);
        geoCommuneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
