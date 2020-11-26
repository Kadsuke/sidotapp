package bf.onea.web.rest;

import bf.onea.service.TypeInterventionService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypeInterventionDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypeIntervention}.
 */
@RestController
@RequestMapping("/api")
public class TypeInterventionResource {

    private final Logger log = LoggerFactory.getLogger(TypeInterventionResource.class);

    private static final String ENTITY_NAME = "typeIntervention";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeInterventionService typeInterventionService;

    public TypeInterventionResource(TypeInterventionService typeInterventionService) {
        this.typeInterventionService = typeInterventionService;
    }

    /**
     * {@code POST  /type-interventions} : Create a new typeIntervention.
     *
     * @param typeInterventionDTO the typeInterventionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeInterventionDTO, or with status {@code 400 (Bad Request)} if the typeIntervention has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-interventions")
    public ResponseEntity<TypeInterventionDTO> createTypeIntervention(@RequestBody TypeInterventionDTO typeInterventionDTO) throws URISyntaxException {
        log.debug("REST request to save TypeIntervention : {}", typeInterventionDTO);
        if (typeInterventionDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeIntervention cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeInterventionDTO result = typeInterventionService.save(typeInterventionDTO);
        return ResponseEntity.created(new URI("/api/type-interventions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-interventions} : Updates an existing typeIntervention.
     *
     * @param typeInterventionDTO the typeInterventionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeInterventionDTO,
     * or with status {@code 400 (Bad Request)} if the typeInterventionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeInterventionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-interventions")
    public ResponseEntity<TypeInterventionDTO> updateTypeIntervention(@RequestBody TypeInterventionDTO typeInterventionDTO) throws URISyntaxException {
        log.debug("REST request to update TypeIntervention : {}", typeInterventionDTO);
        if (typeInterventionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeInterventionDTO result = typeInterventionService.save(typeInterventionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeInterventionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-interventions} : get all the typeInterventions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeInterventions in body.
     */
    @GetMapping("/type-interventions")
    public ResponseEntity<List<TypeInterventionDTO>> getAllTypeInterventions(Pageable pageable) {
        log.debug("REST request to get a page of TypeInterventions");
        Page<TypeInterventionDTO> page = typeInterventionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-interventions/:id} : get the "id" typeIntervention.
     *
     * @param id the id of the typeInterventionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeInterventionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-interventions/{id}")
    public ResponseEntity<TypeInterventionDTO> getTypeIntervention(@PathVariable Long id) {
        log.debug("REST request to get TypeIntervention : {}", id);
        Optional<TypeInterventionDTO> typeInterventionDTO = typeInterventionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeInterventionDTO);
    }

    /**
     * {@code DELETE  /type-interventions/:id} : delete the "id" typeIntervention.
     *
     * @param id the id of the typeInterventionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-interventions/{id}")
    public ResponseEntity<Void> deleteTypeIntervention(@PathVariable Long id) {
        log.debug("REST request to delete TypeIntervention : {}", id);
        typeInterventionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
