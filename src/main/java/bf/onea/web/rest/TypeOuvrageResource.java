package bf.onea.web.rest;

import bf.onea.service.TypeOuvrageService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypeOuvrageDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypeOuvrage}.
 */
@RestController
@RequestMapping("/api")
public class TypeOuvrageResource {

    private final Logger log = LoggerFactory.getLogger(TypeOuvrageResource.class);

    private static final String ENTITY_NAME = "typeOuvrage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeOuvrageService typeOuvrageService;

    public TypeOuvrageResource(TypeOuvrageService typeOuvrageService) {
        this.typeOuvrageService = typeOuvrageService;
    }

    /**
     * {@code POST  /type-ouvrages} : Create a new typeOuvrage.
     *
     * @param typeOuvrageDTO the typeOuvrageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeOuvrageDTO, or with status {@code 400 (Bad Request)} if the typeOuvrage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-ouvrages")
    public ResponseEntity<TypeOuvrageDTO> createTypeOuvrage(@Valid @RequestBody TypeOuvrageDTO typeOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to save TypeOuvrage : {}", typeOuvrageDTO);
        if (typeOuvrageDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeOuvrage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeOuvrageDTO result = typeOuvrageService.save(typeOuvrageDTO);
        return ResponseEntity.created(new URI("/api/type-ouvrages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-ouvrages} : Updates an existing typeOuvrage.
     *
     * @param typeOuvrageDTO the typeOuvrageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeOuvrageDTO,
     * or with status {@code 400 (Bad Request)} if the typeOuvrageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeOuvrageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-ouvrages")
    public ResponseEntity<TypeOuvrageDTO> updateTypeOuvrage(@Valid @RequestBody TypeOuvrageDTO typeOuvrageDTO) throws URISyntaxException {
        log.debug("REST request to update TypeOuvrage : {}", typeOuvrageDTO);
        if (typeOuvrageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeOuvrageDTO result = typeOuvrageService.save(typeOuvrageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeOuvrageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-ouvrages} : get all the typeOuvrages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeOuvrages in body.
     */
    @GetMapping("/type-ouvrages")
    public ResponseEntity<List<TypeOuvrageDTO>> getAllTypeOuvrages(Pageable pageable) {
        log.debug("REST request to get a page of TypeOuvrages");
        Page<TypeOuvrageDTO> page = typeOuvrageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-ouvrages/:id} : get the "id" typeOuvrage.
     *
     * @param id the id of the typeOuvrageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeOuvrageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-ouvrages/{id}")
    public ResponseEntity<TypeOuvrageDTO> getTypeOuvrage(@PathVariable Long id) {
        log.debug("REST request to get TypeOuvrage : {}", id);
        Optional<TypeOuvrageDTO> typeOuvrageDTO = typeOuvrageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeOuvrageDTO);
    }

    /**
     * {@code DELETE  /type-ouvrages/:id} : delete the "id" typeOuvrage.
     *
     * @param id the id of the typeOuvrageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-ouvrages/{id}")
    public ResponseEntity<Void> deleteTypeOuvrage(@PathVariable Long id) {
        log.debug("REST request to delete TypeOuvrage : {}", id);
        typeOuvrageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
