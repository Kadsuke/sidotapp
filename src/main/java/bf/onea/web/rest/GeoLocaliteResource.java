package bf.onea.web.rest;

import bf.onea.service.GeoLocaliteService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoLocaliteDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoLocalite}.
 */
@RestController
@RequestMapping("/api")
public class GeoLocaliteResource {

    private final Logger log = LoggerFactory.getLogger(GeoLocaliteResource.class);

    private static final String ENTITY_NAME = "geoLocalite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoLocaliteService geoLocaliteService;

    public GeoLocaliteResource(GeoLocaliteService geoLocaliteService) {
        this.geoLocaliteService = geoLocaliteService;
    }

    /**
     * {@code POST  /geo-localites} : Create a new geoLocalite.
     *
     * @param geoLocaliteDTO the geoLocaliteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoLocaliteDTO, or with status {@code 400 (Bad Request)} if the geoLocalite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-localites")
    public ResponseEntity<GeoLocaliteDTO> createGeoLocalite(@Valid @RequestBody GeoLocaliteDTO geoLocaliteDTO) throws URISyntaxException {
        log.debug("REST request to save GeoLocalite : {}", geoLocaliteDTO);
        if (geoLocaliteDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoLocalite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoLocaliteDTO result = geoLocaliteService.save(geoLocaliteDTO);
        return ResponseEntity.created(new URI("/api/geo-localites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-localites} : Updates an existing geoLocalite.
     *
     * @param geoLocaliteDTO the geoLocaliteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoLocaliteDTO,
     * or with status {@code 400 (Bad Request)} if the geoLocaliteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoLocaliteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-localites")
    public ResponseEntity<GeoLocaliteDTO> updateGeoLocalite(@Valid @RequestBody GeoLocaliteDTO geoLocaliteDTO) throws URISyntaxException {
        log.debug("REST request to update GeoLocalite : {}", geoLocaliteDTO);
        if (geoLocaliteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoLocaliteDTO result = geoLocaliteService.save(geoLocaliteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoLocaliteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-localites} : get all the geoLocalites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoLocalites in body.
     */
    @GetMapping("/geo-localites")
    public ResponseEntity<List<GeoLocaliteDTO>> getAllGeoLocalites(Pageable pageable) {
        log.debug("REST request to get a page of GeoLocalites");
        Page<GeoLocaliteDTO> page = geoLocaliteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-localites/:id} : get the "id" geoLocalite.
     *
     * @param id the id of the geoLocaliteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoLocaliteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-localites/{id}")
    public ResponseEntity<GeoLocaliteDTO> getGeoLocalite(@PathVariable Long id) {
        log.debug("REST request to get GeoLocalite : {}", id);
        Optional<GeoLocaliteDTO> geoLocaliteDTO = geoLocaliteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoLocaliteDTO);
    }

    /**
     * {@code DELETE  /geo-localites/:id} : delete the "id" geoLocalite.
     *
     * @param id the id of the geoLocaliteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-localites/{id}")
    public ResponseEntity<Void> deleteGeoLocalite(@PathVariable Long id) {
        log.debug("REST request to delete GeoLocalite : {}", id);
        geoLocaliteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
