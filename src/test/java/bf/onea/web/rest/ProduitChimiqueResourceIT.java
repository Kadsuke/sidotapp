package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.ProduitChimique;
import bf.onea.repository.ProduitChimiqueRepository;
import bf.onea.service.ProduitChimiqueService;
import bf.onea.service.dto.ProduitChimiqueDTO;
import bf.onea.service.mapper.ProduitChimiqueMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProduitChimiqueResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProduitChimiqueResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ProduitChimiqueRepository produitChimiqueRepository;

    @Autowired
    private ProduitChimiqueMapper produitChimiqueMapper;

    @Autowired
    private ProduitChimiqueService produitChimiqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProduitChimiqueMockMvc;

    private ProduitChimique produitChimique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitChimique createEntity(EntityManager em) {
        ProduitChimique produitChimique = new ProduitChimique()
            .libelle(DEFAULT_LIBELLE);
        return produitChimique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitChimique createUpdatedEntity(EntityManager em) {
        ProduitChimique produitChimique = new ProduitChimique()
            .libelle(UPDATED_LIBELLE);
        return produitChimique;
    }

    @BeforeEach
    public void initTest() {
        produitChimique = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduitChimique() throws Exception {
        int databaseSizeBeforeCreate = produitChimiqueRepository.findAll().size();
        // Create the ProduitChimique
        ProduitChimiqueDTO produitChimiqueDTO = produitChimiqueMapper.toDto(produitChimique);
        restProduitChimiqueMockMvc.perform(post("/api/produit-chimiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitChimiqueDTO)))
            .andExpect(status().isCreated());

        // Validate the ProduitChimique in the database
        List<ProduitChimique> produitChimiqueList = produitChimiqueRepository.findAll();
        assertThat(produitChimiqueList).hasSize(databaseSizeBeforeCreate + 1);
        ProduitChimique testProduitChimique = produitChimiqueList.get(produitChimiqueList.size() - 1);
        assertThat(testProduitChimique.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createProduitChimiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produitChimiqueRepository.findAll().size();

        // Create the ProduitChimique with an existing ID
        produitChimique.setId(1L);
        ProduitChimiqueDTO produitChimiqueDTO = produitChimiqueMapper.toDto(produitChimique);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitChimiqueMockMvc.perform(post("/api/produit-chimiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitChimiqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProduitChimique in the database
        List<ProduitChimique> produitChimiqueList = produitChimiqueRepository.findAll();
        assertThat(produitChimiqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProduitChimiques() throws Exception {
        // Initialize the database
        produitChimiqueRepository.saveAndFlush(produitChimique);

        // Get all the produitChimiqueList
        restProduitChimiqueMockMvc.perform(get("/api/produit-chimiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produitChimique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getProduitChimique() throws Exception {
        // Initialize the database
        produitChimiqueRepository.saveAndFlush(produitChimique);

        // Get the produitChimique
        restProduitChimiqueMockMvc.perform(get("/api/produit-chimiques/{id}", produitChimique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produitChimique.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingProduitChimique() throws Exception {
        // Get the produitChimique
        restProduitChimiqueMockMvc.perform(get("/api/produit-chimiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduitChimique() throws Exception {
        // Initialize the database
        produitChimiqueRepository.saveAndFlush(produitChimique);

        int databaseSizeBeforeUpdate = produitChimiqueRepository.findAll().size();

        // Update the produitChimique
        ProduitChimique updatedProduitChimique = produitChimiqueRepository.findById(produitChimique.getId()).get();
        // Disconnect from session so that the updates on updatedProduitChimique are not directly saved in db
        em.detach(updatedProduitChimique);
        updatedProduitChimique
            .libelle(UPDATED_LIBELLE);
        ProduitChimiqueDTO produitChimiqueDTO = produitChimiqueMapper.toDto(updatedProduitChimique);

        restProduitChimiqueMockMvc.perform(put("/api/produit-chimiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitChimiqueDTO)))
            .andExpect(status().isOk());

        // Validate the ProduitChimique in the database
        List<ProduitChimique> produitChimiqueList = produitChimiqueRepository.findAll();
        assertThat(produitChimiqueList).hasSize(databaseSizeBeforeUpdate);
        ProduitChimique testProduitChimique = produitChimiqueList.get(produitChimiqueList.size() - 1);
        assertThat(testProduitChimique.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingProduitChimique() throws Exception {
        int databaseSizeBeforeUpdate = produitChimiqueRepository.findAll().size();

        // Create the ProduitChimique
        ProduitChimiqueDTO produitChimiqueDTO = produitChimiqueMapper.toDto(produitChimique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitChimiqueMockMvc.perform(put("/api/produit-chimiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitChimiqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProduitChimique in the database
        List<ProduitChimique> produitChimiqueList = produitChimiqueRepository.findAll();
        assertThat(produitChimiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduitChimique() throws Exception {
        // Initialize the database
        produitChimiqueRepository.saveAndFlush(produitChimique);

        int databaseSizeBeforeDelete = produitChimiqueRepository.findAll().size();

        // Delete the produitChimique
        restProduitChimiqueMockMvc.perform(delete("/api/produit-chimiques/{id}", produitChimique.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProduitChimique> produitChimiqueList = produitChimiqueRepository.findAll();
        assertThat(produitChimiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
