package bf.onea.web.rest;

import bf.onea.service.TypeAnalyseService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypeAnalyseDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypeAnalyse}.
 */
@RestController
@RequestMapping("/api")
public class TypeAnalyseResource {

    private final Logger log = LoggerFactory.getLogger(TypeAnalyseResource.class);

    private static final String ENTITY_NAME = "typeAnalyse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAnalyseService typeAnalyseService;

    public TypeAnalyseResource(TypeAnalyseService typeAnalyseService) {
        this.typeAnalyseService = typeAnalyseService;
    }

    /**
     * {@code POST  /type-analyses} : Create a new typeAnalyse.
     *
     * @param typeAnalyseDTO the typeAnalyseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAnalyseDTO, or with status {@code 400 (Bad Request)} if the typeAnalyse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-analyses")
    public ResponseEntity<TypeAnalyseDTO> createTypeAnalyse(@RequestBody TypeAnalyseDTO typeAnalyseDTO) throws URISyntaxException {
        log.debug("REST request to save TypeAnalyse : {}", typeAnalyseDTO);
        if (typeAnalyseDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeAnalyse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeAnalyseDTO result = typeAnalyseService.save(typeAnalyseDTO);
        return ResponseEntity.created(new URI("/api/type-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-analyses} : Updates an existing typeAnalyse.
     *
     * @param typeAnalyseDTO the typeAnalyseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAnalyseDTO,
     * or with status {@code 400 (Bad Request)} if the typeAnalyseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAnalyseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-analyses")
    public ResponseEntity<TypeAnalyseDTO> updateTypeAnalyse(@RequestBody TypeAnalyseDTO typeAnalyseDTO) throws URISyntaxException {
        log.debug("REST request to update TypeAnalyse : {}", typeAnalyseDTO);
        if (typeAnalyseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeAnalyseDTO result = typeAnalyseService.save(typeAnalyseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAnalyseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-analyses} : get all the typeAnalyses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAnalyses in body.
     */
    @GetMapping("/type-analyses")
    public ResponseEntity<List<TypeAnalyseDTO>> getAllTypeAnalyses(Pageable pageable) {
        log.debug("REST request to get a page of TypeAnalyses");
        Page<TypeAnalyseDTO> page = typeAnalyseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-analyses/:id} : get the "id" typeAnalyse.
     *
     * @param id the id of the typeAnalyseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAnalyseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-analyses/{id}")
    public ResponseEntity<TypeAnalyseDTO> getTypeAnalyse(@PathVariable Long id) {
        log.debug("REST request to get TypeAnalyse : {}", id);
        Optional<TypeAnalyseDTO> typeAnalyseDTO = typeAnalyseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeAnalyseDTO);
    }

    /**
     * {@code DELETE  /type-analyses/:id} : delete the "id" typeAnalyse.
     *
     * @param id the id of the typeAnalyseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-analyses/{id}")
    public ResponseEntity<Void> deleteTypeAnalyse(@PathVariable Long id) {
        log.debug("REST request to delete TypeAnalyse : {}", id);
        typeAnalyseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
