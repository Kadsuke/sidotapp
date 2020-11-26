package bf.onea.web.rest;

import bf.onea.service.BailleurService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.BailleurDTO;

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
 * REST controller for managing {@link bf.onea.domain.Bailleur}.
 */
@RestController
@RequestMapping("/api")
public class BailleurResource {

    private final Logger log = LoggerFactory.getLogger(BailleurResource.class);

    private static final String ENTITY_NAME = "bailleur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BailleurService bailleurService;

    public BailleurResource(BailleurService bailleurService) {
        this.bailleurService = bailleurService;
    }

    /**
     * {@code POST  /bailleurs} : Create a new bailleur.
     *
     * @param bailleurDTO the bailleurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bailleurDTO, or with status {@code 400 (Bad Request)} if the bailleur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bailleurs")
    public ResponseEntity<BailleurDTO> createBailleur(@RequestBody BailleurDTO bailleurDTO) throws URISyntaxException {
        log.debug("REST request to save Bailleur : {}", bailleurDTO);
        if (bailleurDTO.getId() != null) {
            throw new BadRequestAlertException("A new bailleur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BailleurDTO result = bailleurService.save(bailleurDTO);
        return ResponseEntity.created(new URI("/api/bailleurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bailleurs} : Updates an existing bailleur.
     *
     * @param bailleurDTO the bailleurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bailleurDTO,
     * or with status {@code 400 (Bad Request)} if the bailleurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bailleurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bailleurs")
    public ResponseEntity<BailleurDTO> updateBailleur(@RequestBody BailleurDTO bailleurDTO) throws URISyntaxException {
        log.debug("REST request to update Bailleur : {}", bailleurDTO);
        if (bailleurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BailleurDTO result = bailleurService.save(bailleurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bailleurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bailleurs} : get all the bailleurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bailleurs in body.
     */
    @GetMapping("/bailleurs")
    public ResponseEntity<List<BailleurDTO>> getAllBailleurs(Pageable pageable) {
        log.debug("REST request to get a page of Bailleurs");
        Page<BailleurDTO> page = bailleurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bailleurs/:id} : get the "id" bailleur.
     *
     * @param id the id of the bailleurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bailleurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bailleurs/{id}")
    public ResponseEntity<BailleurDTO> getBailleur(@PathVariable Long id) {
        log.debug("REST request to get Bailleur : {}", id);
        Optional<BailleurDTO> bailleurDTO = bailleurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bailleurDTO);
    }

    /**
     * {@code DELETE  /bailleurs/:id} : delete the "id" bailleur.
     *
     * @param id the id of the bailleurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bailleurs/{id}")
    public ResponseEntity<Void> deleteBailleur(@PathVariable Long id) {
        log.debug("REST request to delete Bailleur : {}", id);
        bailleurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
