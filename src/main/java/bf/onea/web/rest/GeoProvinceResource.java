package bf.onea.web.rest;

import bf.onea.service.GeoProvinceService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.GeoProvinceDTO;

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
 * REST controller for managing {@link bf.onea.domain.GeoProvince}.
 */
@RestController
@RequestMapping("/api")
public class GeoProvinceResource {

    private final Logger log = LoggerFactory.getLogger(GeoProvinceResource.class);

    private static final String ENTITY_NAME = "geoProvince";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoProvinceService geoProvinceService;

    public GeoProvinceResource(GeoProvinceService geoProvinceService) {
        this.geoProvinceService = geoProvinceService;
    }

    /**
     * {@code POST  /geo-provinces} : Create a new geoProvince.
     *
     * @param geoProvinceDTO the geoProvinceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoProvinceDTO, or with status {@code 400 (Bad Request)} if the geoProvince has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-provinces")
    public ResponseEntity<GeoProvinceDTO> createGeoProvince(@Valid @RequestBody GeoProvinceDTO geoProvinceDTO) throws URISyntaxException {
        log.debug("REST request to save GeoProvince : {}", geoProvinceDTO);
        if (geoProvinceDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoProvince cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoProvinceDTO result = geoProvinceService.save(geoProvinceDTO);
        return ResponseEntity.created(new URI("/api/geo-provinces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-provinces} : Updates an existing geoProvince.
     *
     * @param geoProvinceDTO the geoProvinceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoProvinceDTO,
     * or with status {@code 400 (Bad Request)} if the geoProvinceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoProvinceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-provinces")
    public ResponseEntity<GeoProvinceDTO> updateGeoProvince(@Valid @RequestBody GeoProvinceDTO geoProvinceDTO) throws URISyntaxException {
        log.debug("REST request to update GeoProvince : {}", geoProvinceDTO);
        if (geoProvinceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoProvinceDTO result = geoProvinceService.save(geoProvinceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoProvinceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-provinces} : get all the geoProvinces.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoProvinces in body.
     */
    @GetMapping("/geo-provinces")
    public ResponseEntity<List<GeoProvinceDTO>> getAllGeoProvinces(Pageable pageable) {
        log.debug("REST request to get a page of GeoProvinces");
        Page<GeoProvinceDTO> page = geoProvinceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geo-provinces/:id} : get the "id" geoProvince.
     *
     * @param id the id of the geoProvinceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoProvinceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-provinces/{id}")
    public ResponseEntity<GeoProvinceDTO> getGeoProvince(@PathVariable Long id) {
        log.debug("REST request to get GeoProvince : {}", id);
        Optional<GeoProvinceDTO> geoProvinceDTO = geoProvinceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoProvinceDTO);
    }

    /**
     * {@code DELETE  /geo-provinces/:id} : delete the "id" geoProvince.
     *
     * @param id the id of the geoProvinceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-provinces/{id}")
    public ResponseEntity<Void> deleteGeoProvince(@PathVariable Long id) {
        log.debug("REST request to delete GeoProvince : {}", id);
        geoProvinceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
