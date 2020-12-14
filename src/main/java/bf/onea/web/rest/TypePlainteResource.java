package bf.onea.web.rest;

import bf.onea.service.TypePlainteService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypePlainteDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypePlainte}.
 */
@RestController
@RequestMapping("/api")
public class TypePlainteResource {

    private final Logger log = LoggerFactory.getLogger(TypePlainteResource.class);

    private static final String ENTITY_NAME = "typePlainte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypePlainteService typePlainteService;

    public TypePlainteResource(TypePlainteService typePlainteService) {
        this.typePlainteService = typePlainteService;
    }

    /**
     * {@code POST  /type-plaintes} : Create a new typePlainte.
     *
     * @param typePlainteDTO the typePlainteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typePlainteDTO, or with status {@code 400 (Bad Request)} if the typePlainte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-plaintes")
    public ResponseEntity<TypePlainteDTO> createTypePlainte(@Valid @RequestBody TypePlainteDTO typePlainteDTO) throws URISyntaxException {
        log.debug("REST request to save TypePlainte : {}", typePlainteDTO);
        if (typePlainteDTO.getId() != null) {
            throw new BadRequestAlertException("A new typePlainte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypePlainteDTO result = typePlainteService.save(typePlainteDTO);
        return ResponseEntity.created(new URI("/api/type-plaintes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-plaintes} : Updates an existing typePlainte.
     *
     * @param typePlainteDTO the typePlainteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePlainteDTO,
     * or with status {@code 400 (Bad Request)} if the typePlainteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typePlainteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-plaintes")
    public ResponseEntity<TypePlainteDTO> updateTypePlainte(@Valid @RequestBody TypePlainteDTO typePlainteDTO) throws URISyntaxException {
        log.debug("REST request to update TypePlainte : {}", typePlainteDTO);
        if (typePlainteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypePlainteDTO result = typePlainteService.save(typePlainteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePlainteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-plaintes} : get all the typePlaintes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typePlaintes in body.
     */
    @GetMapping("/type-plaintes")
    public ResponseEntity<List<TypePlainteDTO>> getAllTypePlaintes(Pageable pageable) {
        log.debug("REST request to get a page of TypePlaintes");
        Page<TypePlainteDTO> page = typePlainteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-plaintes/:id} : get the "id" typePlainte.
     *
     * @param id the id of the typePlainteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typePlainteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-plaintes/{id}")
    public ResponseEntity<TypePlainteDTO> getTypePlainte(@PathVariable Long id) {
        log.debug("REST request to get TypePlainte : {}", id);
        Optional<TypePlainteDTO> typePlainteDTO = typePlainteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typePlainteDTO);
    }

    /**
     * {@code DELETE  /type-plaintes/:id} : delete the "id" typePlainte.
     *
     * @param id the id of the typePlainteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-plaintes/{id}")
    public ResponseEntity<Void> deleteTypePlainte(@PathVariable Long id) {
        log.debug("REST request to delete TypePlainte : {}", id);
        typePlainteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
