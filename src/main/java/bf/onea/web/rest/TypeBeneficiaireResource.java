package bf.onea.web.rest;

import bf.onea.service.TypeBeneficiaireService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.TypeBeneficiaireDTO;

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
 * REST controller for managing {@link bf.onea.domain.TypeBeneficiaire}.
 */
@RestController
@RequestMapping("/api")
public class TypeBeneficiaireResource {

    private final Logger log = LoggerFactory.getLogger(TypeBeneficiaireResource.class);

    private static final String ENTITY_NAME = "typeBeneficiaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeBeneficiaireService typeBeneficiaireService;

    public TypeBeneficiaireResource(TypeBeneficiaireService typeBeneficiaireService) {
        this.typeBeneficiaireService = typeBeneficiaireService;
    }

    /**
     * {@code POST  /type-beneficiaires} : Create a new typeBeneficiaire.
     *
     * @param typeBeneficiaireDTO the typeBeneficiaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeBeneficiaireDTO, or with status {@code 400 (Bad Request)} if the typeBeneficiaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-beneficiaires")
    public ResponseEntity<TypeBeneficiaireDTO> createTypeBeneficiaire(@RequestBody TypeBeneficiaireDTO typeBeneficiaireDTO) throws URISyntaxException {
        log.debug("REST request to save TypeBeneficiaire : {}", typeBeneficiaireDTO);
        if (typeBeneficiaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeBeneficiaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeBeneficiaireDTO result = typeBeneficiaireService.save(typeBeneficiaireDTO);
        return ResponseEntity.created(new URI("/api/type-beneficiaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-beneficiaires} : Updates an existing typeBeneficiaire.
     *
     * @param typeBeneficiaireDTO the typeBeneficiaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeBeneficiaireDTO,
     * or with status {@code 400 (Bad Request)} if the typeBeneficiaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeBeneficiaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-beneficiaires")
    public ResponseEntity<TypeBeneficiaireDTO> updateTypeBeneficiaire(@RequestBody TypeBeneficiaireDTO typeBeneficiaireDTO) throws URISyntaxException {
        log.debug("REST request to update TypeBeneficiaire : {}", typeBeneficiaireDTO);
        if (typeBeneficiaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeBeneficiaireDTO result = typeBeneficiaireService.save(typeBeneficiaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeBeneficiaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-beneficiaires} : get all the typeBeneficiaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeBeneficiaires in body.
     */
    @GetMapping("/type-beneficiaires")
    public ResponseEntity<List<TypeBeneficiaireDTO>> getAllTypeBeneficiaires(Pageable pageable) {
        log.debug("REST request to get a page of TypeBeneficiaires");
        Page<TypeBeneficiaireDTO> page = typeBeneficiaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-beneficiaires/:id} : get the "id" typeBeneficiaire.
     *
     * @param id the id of the typeBeneficiaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeBeneficiaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-beneficiaires/{id}")
    public ResponseEntity<TypeBeneficiaireDTO> getTypeBeneficiaire(@PathVariable Long id) {
        log.debug("REST request to get TypeBeneficiaire : {}", id);
        Optional<TypeBeneficiaireDTO> typeBeneficiaireDTO = typeBeneficiaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeBeneficiaireDTO);
    }

    /**
     * {@code DELETE  /type-beneficiaires/:id} : delete the "id" typeBeneficiaire.
     *
     * @param id the id of the typeBeneficiaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-beneficiaires/{id}")
    public ResponseEntity<Void> deleteTypeBeneficiaire(@PathVariable Long id) {
        log.debug("REST request to delete TypeBeneficiaire : {}", id);
        typeBeneficiaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
