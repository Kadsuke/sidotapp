package bf.onea.web.rest;

import bf.onea.service.TypeEquipementService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypeEquipementDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypeEquipement}.
 */
@RestController
@RequestMapping("/api")
public class TypeEquipementResource {

    private final Logger log = LoggerFactory.getLogger(TypeEquipementResource.class);

    private static final String ENTITY_NAME = "typeEquipement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeEquipementService typeEquipementService;

    public TypeEquipementResource(TypeEquipementService typeEquipementService) {
        this.typeEquipementService = typeEquipementService;
    }

    /**
     * {@code POST  /type-equipements} : Create a new typeEquipement.
     *
     * @param typeEquipementDTO the typeEquipementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeEquipementDTO, or with status {@code 400 (Bad Request)} if the typeEquipement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-equipements")
    public ResponseEntity<TypeEquipementDTO> createTypeEquipement(@RequestBody TypeEquipementDTO typeEquipementDTO) throws URISyntaxException {
        log.debug("REST request to save TypeEquipement : {}", typeEquipementDTO);
        if (typeEquipementDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeEquipement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeEquipementDTO result = typeEquipementService.save(typeEquipementDTO);
        return ResponseEntity.created(new URI("/api/type-equipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-equipements} : Updates an existing typeEquipement.
     *
     * @param typeEquipementDTO the typeEquipementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeEquipementDTO,
     * or with status {@code 400 (Bad Request)} if the typeEquipementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeEquipementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-equipements")
    public ResponseEntity<TypeEquipementDTO> updateTypeEquipement(@RequestBody TypeEquipementDTO typeEquipementDTO) throws URISyntaxException {
        log.debug("REST request to update TypeEquipement : {}", typeEquipementDTO);
        if (typeEquipementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeEquipementDTO result = typeEquipementService.save(typeEquipementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeEquipementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-equipements} : get all the typeEquipements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeEquipements in body.
     */
    @GetMapping("/type-equipements")
    public ResponseEntity<List<TypeEquipementDTO>> getAllTypeEquipements(Pageable pageable) {
        log.debug("REST request to get a page of TypeEquipements");
        Page<TypeEquipementDTO> page = typeEquipementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-equipements/:id} : get the "id" typeEquipement.
     *
     * @param id the id of the typeEquipementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeEquipementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-equipements/{id}")
    public ResponseEntity<TypeEquipementDTO> getTypeEquipement(@PathVariable Long id) {
        log.debug("REST request to get TypeEquipement : {}", id);
        Optional<TypeEquipementDTO> typeEquipementDTO = typeEquipementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeEquipementDTO);
    }

    /**
     * {@code DELETE  /type-equipements/:id} : delete the "id" typeEquipement.
     *
     * @param id the id of the typeEquipementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-equipements/{id}")
    public ResponseEntity<Void> deleteTypeEquipement(@PathVariable Long id) {
        log.debug("REST request to delete TypeEquipement : {}", id);
        typeEquipementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
