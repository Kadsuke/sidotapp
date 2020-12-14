package bf.onea.web.rest;

import bf.onea.service.CategorieRessourcesService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.CategorieRessourcesDTO;

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
 * REST controller for managing {@link bf.onea.domain.CategorieRessources}.
 */
@RestController
@RequestMapping("/api")
public class CategorieRessourcesResource {

    private final Logger log = LoggerFactory.getLogger(CategorieRessourcesResource.class);

    private static final String ENTITY_NAME = "categorieRessources";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieRessourcesService categorieRessourcesService;

    public CategorieRessourcesResource(CategorieRessourcesService categorieRessourcesService) {
        this.categorieRessourcesService = categorieRessourcesService;
    }

    /**
     * {@code POST  /categorie-ressources} : Create a new categorieRessources.
     *
     * @param categorieRessourcesDTO the categorieRessourcesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieRessourcesDTO, or with status {@code 400 (Bad Request)} if the categorieRessources has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-ressources")
    public ResponseEntity<CategorieRessourcesDTO> createCategorieRessources(@Valid @RequestBody CategorieRessourcesDTO categorieRessourcesDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieRessources : {}", categorieRessourcesDTO);
        if (categorieRessourcesDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieRessources cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieRessourcesDTO result = categorieRessourcesService.save(categorieRessourcesDTO);
        return ResponseEntity.created(new URI("/api/categorie-ressources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-ressources} : Updates an existing categorieRessources.
     *
     * @param categorieRessourcesDTO the categorieRessourcesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieRessourcesDTO,
     * or with status {@code 400 (Bad Request)} if the categorieRessourcesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieRessourcesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-ressources")
    public ResponseEntity<CategorieRessourcesDTO> updateCategorieRessources(@Valid @RequestBody CategorieRessourcesDTO categorieRessourcesDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieRessources : {}", categorieRessourcesDTO);
        if (categorieRessourcesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieRessourcesDTO result = categorieRessourcesService.save(categorieRessourcesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieRessourcesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-ressources} : get all the categorieRessources.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieRessources in body.
     */
    @GetMapping("/categorie-ressources")
    public ResponseEntity<List<CategorieRessourcesDTO>> getAllCategorieRessources(Pageable pageable) {
        log.debug("REST request to get a page of CategorieRessources");
        Page<CategorieRessourcesDTO> page = categorieRessourcesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-ressources/:id} : get the "id" categorieRessources.
     *
     * @param id the id of the categorieRessourcesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieRessourcesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-ressources/{id}")
    public ResponseEntity<CategorieRessourcesDTO> getCategorieRessources(@PathVariable Long id) {
        log.debug("REST request to get CategorieRessources : {}", id);
        Optional<CategorieRessourcesDTO> categorieRessourcesDTO = categorieRessourcesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieRessourcesDTO);
    }

    /**
     * {@code DELETE  /categorie-ressources/:id} : delete the "id" categorieRessources.
     *
     * @param id the id of the categorieRessourcesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-ressources/{id}")
    public ResponseEntity<Void> deleteCategorieRessources(@PathVariable Long id) {
        log.debug("REST request to delete CategorieRessources : {}", id);
        categorieRessourcesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
