package bf.onea.web.rest;

import bf.onea.service.GeoLotService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoLotDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoLot}.
 */
@RestController
@RequestMapping("/api")
public class GeoLotResource {

    private final Logger log = LoggerFactory.getLogger(GeoLotResource.class);

    private static final String ENTITY_NAME = "geoLot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoLotService geoLotService;

    public GeoLotResource(GeoLotService geoLotService) {
        this.geoLotService = geoLotService;
    }

    /**
     * {@code POST  /geo-lots} : Create a new geoLot.
     *
     * @param geoLotDTO the geoLotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoLotDTO, or with status {@code 400 (Bad Request)} if the geoLot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-lots")
    public ResponseEntity<GeoLotDTO> createGeoLot(@Valid @RequestBody GeoLotDTO geoLotDTO) throws URISyntaxException {
        log.debug("REST request to save GeoLot : {}", geoLotDTO);
        if (geoLotDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoLot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoLotDTO result = geoLotService.save(geoLotDTO);
        return ResponseEntity.created(new URI("/api/geo-lots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-lots} : Updates an existing geoLot.
     *
     * @param geoLotDTO the geoLotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoLotDTO,
     * or with status {@code 400 (Bad Request)} if the geoLotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoLotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-lots")
    public ResponseEntity<GeoLotDTO> updateGeoLot(@Valid @RequestBody GeoLotDTO geoLotDTO) throws URISyntaxException {
        log.debug("REST request to update GeoLot : {}", geoLotDTO);
        if (geoLotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoLotDTO result = geoLotService.save(geoLotDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoLotDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-lots} : get all the geoLots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoLots in body.
     */
    @GetMapping("/geo-lots")
    public ResponseEntity<List<GeoLotDTO>> getAllGeoLots(Pageable pageable) {
        log.debug("REST request to get a page of GeoLots");
        Page<GeoLotDTO> page = geoLotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-lots/:id} : get the "id" geoLot.
     *
     * @param id the id of the geoLotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoLotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-lots/{id}")
    public ResponseEntity<GeoLotDTO> getGeoLot(@PathVariable Long id) {
        log.debug("REST request to get GeoLot : {}", id);
        Optional<GeoLotDTO> geoLotDTO = geoLotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoLotDTO);
    }

    /**
     * {@code DELETE  /geo-lots/:id} : delete the "id" geoLot.
     *
     * @param id the id of the geoLotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-lots/{id}")
    public ResponseEntity<Void> deleteGeoLot(@PathVariable Long id) {
        log.debug("REST request to delete GeoLot : {}", id);
        geoLotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
