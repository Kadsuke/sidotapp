package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.CategorieRessources;
import bf.onea.repository.CategorieRessourcesRepository;
import bf.onea.service.CategorieRessourcesService;
import bf.onea.service.dto.CategorieRessourcesDTO;
import bf.onea.service.mapper.CategorieRessourcesMapper;

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
 * Integration tests for the {@link CategorieRessourcesResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategorieRessourcesResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private CategorieRessourcesRepository categorieRessourcesRepository;

    @Autowired
    private CategorieRessourcesMapper categorieRessourcesMapper;

    @Autowired
    private CategorieRessourcesService categorieRessourcesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieRessourcesMockMvc;

    private CategorieRessources categorieRessources;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieRessources createEntity(EntityManager em) {
        CategorieRessources categorieRessources = new CategorieRessources()
            .libelle(DEFAULT_LIBELLE);
        return categorieRessources;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieRessources createUpdatedEntity(EntityManager em) {
        CategorieRessources categorieRessources = new CategorieRessources()
            .libelle(UPDATED_LIBELLE);
        return categorieRessources;
    }

    @BeforeEach
    public void initTest() {
        categorieRessources = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieRessources() throws Exception {
        int databaseSizeBeforeCreate = categorieRessourcesRepository.findAll().size();
        // Create the CategorieRessources
        CategorieRessourcesDTO categorieRessourcesDTO = categorieRessourcesMapper.toDto(categorieRessources);
        restCategorieRessourcesMockMvc.perform(post("/api/categorie-ressources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieRessourcesDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieRessources in the database
        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieRessources testCategorieRessources = categorieRessourcesList.get(categorieRessourcesList.size() - 1);
        assertThat(testCategorieRessources.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createCategorieRessourcesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieRessourcesRepository.findAll().size();

        // Create the CategorieRessources with an existing ID
        categorieRessources.setId(1L);
        CategorieRessourcesDTO categorieRessourcesDTO = categorieRessourcesMapper.toDto(categorieRessources);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieRessourcesMockMvc.perform(post("/api/categorie-ressources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieRessourcesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieRessources in the database
        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = categorieRessourcesRepository.findAll().size();
        // set the field null
        categorieRessources.setLibelle(null);

        // Create the CategorieRessources, which fails.
        CategorieRessourcesDTO categorieRessourcesDTO = categorieRessourcesMapper.toDto(categorieRessources);


        restCategorieRessourcesMockMvc.perform(post("/api/categorie-ressources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieRessourcesDTO)))
            .andExpect(status().isBadRequest());

        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategorieRessources() throws Exception {
        // Initialize the database
        categorieRessourcesRepository.saveAndFlush(categorieRessources);

        // Get all the categorieRessourcesList
        restCategorieRessourcesMockMvc.perform(get("/api/categorie-ressources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieRessources.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getCategorieRessources() throws Exception {
        // Initialize the database
        categorieRessourcesRepository.saveAndFlush(categorieRessources);

        // Get the categorieRessources
        restCategorieRessourcesMockMvc.perform(get("/api/categorie-ressources/{id}", categorieRessources.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieRessources.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingCategorieRessources() throws Exception {
        // Get the categorieRessources
        restCategorieRessourcesMockMvc.perform(get("/api/categorie-ressources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieRessources() throws Exception {
        // Initialize the database
        categorieRessourcesRepository.saveAndFlush(categorieRessources);

        int databaseSizeBeforeUpdate = categorieRessourcesRepository.findAll().size();

        // Update the categorieRessources
        CategorieRessources updatedCategorieRessources = categorieRessourcesRepository.findById(categorieRessources.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieRessources are not directly saved in db
        em.detach(updatedCategorieRessources);
        updatedCategorieRessources
            .libelle(UPDATED_LIBELLE);
        CategorieRessourcesDTO categorieRessourcesDTO = categorieRessourcesMapper.toDto(updatedCategorieRessources);

        restCategorieRessourcesMockMvc.perform(put("/api/categorie-ressources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieRessourcesDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieRessources in the database
        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeUpdate);
        CategorieRessources testCategorieRessources = categorieRessourcesList.get(categorieRessourcesList.size() - 1);
        assertThat(testCategorieRessources.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieRessources() throws Exception {
        int databaseSizeBeforeUpdate = categorieRessourcesRepository.findAll().size();

        // Create the CategorieRessources
        CategorieRessourcesDTO categorieRessourcesDTO = categorieRessourcesMapper.toDto(categorieRessources);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieRessourcesMockMvc.perform(put("/api/categorie-ressources")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieRessourcesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieRessources in the database
        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieRessources() throws Exception {
        // Initialize the database
        categorieRessourcesRepository.saveAndFlush(categorieRessources);

        int databaseSizeBeforeDelete = categorieRessourcesRepository.findAll().size();

        // Delete the categorieRessources
        restCategorieRessourcesMockMvc.perform(delete("/api/categorie-ressources/{id}", categorieRessources.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieRessources> categorieRessourcesList = categorieRessourcesRepository.findAll();
        assertThat(categorieRessourcesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
