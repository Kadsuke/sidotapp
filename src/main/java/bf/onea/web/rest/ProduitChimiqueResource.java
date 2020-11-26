package bf.onea.web.rest;

import bf.onea.service.ProduitChimiqueService;
import bf.onea.web.rest.errors.BadRequestAlertException;
import bf.onea.service.dto.ProduitChimiqueDTO;

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
 * REST controller for managing {@link bf.onea.domain.ProduitChimique}.
 */
@RestController
@RequestMapping("/api")
public class ProduitChimiqueResource {

    private final Logger log = LoggerFactory.getLogger(ProduitChimiqueResource.class);

    private static final String ENTITY_NAME = "produitChimique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProduitChimiqueService produitChimiqueService;

    public ProduitChimiqueResource(ProduitChimiqueService produitChimiqueService) {
        this.produitChimiqueService = produitChimiqueService;
    }

    /**
     * {@code POST  /produit-chimiques} : Create a new produitChimique.
     *
     * @param produitChimiqueDTO the produitChimiqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produitChimiqueDTO, or with status {@code 400 (Bad Request)} if the produitChimique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produit-chimiques")
    public ResponseEntity<ProduitChimiqueDTO> createProduitChimique(@RequestBody ProduitChimiqueDTO produitChimiqueDTO) throws URISyntaxException {
        log.debug("REST request to save ProduitChimique : {}", produitChimiqueDTO);
        if (produitChimiqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new produitChimique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProduitChimiqueDTO result = produitChimiqueService.save(produitChimiqueDTO);
        return ResponseEntity.created(new URI("/api/produit-chimiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /produit-chimiques} : Updates an existing produitChimique.
     *
     * @param produitChimiqueDTO the produitChimiqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produitChimiqueDTO,
     * or with status {@code 400 (Bad Request)} if the produitChimiqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produitChimiqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/produit-chimiques")
    public ResponseEntity<ProduitChimiqueDTO> updateProduitChimique(@RequestBody ProduitChimiqueDTO produitChimiqueDTO) throws URISyntaxException {
        log.debug("REST request to update ProduitChimique : {}", produitChimiqueDTO);
        if (produitChimiqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProduitChimiqueDTO result = produitChimiqueService.save(produitChimiqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, produitChimiqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /produit-chimiques} : get all the produitChimiques.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produitChimiques in body.
     */
    @GetMapping("/produit-chimiques")
    public ResponseEntity<List<ProduitChimiqueDTO>> getAllProduitChimiques(Pageable pageable) {
        log.debug("REST request to get a page of ProduitChimiques");
        Page<ProduitChimiqueDTO> page = produitChimiqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /produit-chimiques/:id} : get the "id" produitChimique.
     *
     * @param id the id of the produitChimiqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produitChimiqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produit-chimiques/{id}")
    public ResponseEntity<ProduitChimiqueDTO> getProduitChimique(@PathVariable Long id) {
        log.debug("REST request to get ProduitChimique : {}", id);
        Optional<ProduitChimiqueDTO> produitChimiqueDTO = produitChimiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produitChimiqueDTO);
    }

    /**
     * {@code DELETE  /produit-chimiques/:id} : delete the "id" produitChimique.
     *
     * @param id the id of the produitChimiqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produit-chimiques/{id}")
    public ResponseEntity<Void> deleteProduitChimique(@PathVariable Long id) {
        log.debug("REST request to delete ProduitChimique : {}", id);
        produitChimiqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
