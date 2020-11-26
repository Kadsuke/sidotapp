package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Prefabricant;
import bf.onea.repository.PrefabricantRepository;
import bf.onea.service.PrefabricantService;
import bf.onea.service.dto.PrefabricantDTO;
import bf.onea.service.mapper.PrefabricantMapper;

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
 * Integration tests for the {@link PrefabricantResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrefabricantResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private PrefabricantRepository prefabricantRepository;

    @Autowired
    private PrefabricantMapper prefabricantMapper;

    @Autowired
    private PrefabricantService prefabricantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrefabricantMockMvc;

    private Prefabricant prefabricant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prefabricant createEntity(EntityManager em) {
        Prefabricant prefabricant = new Prefabricant()
            .libelle(DEFAULT_LIBELLE);
        return prefabricant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prefabricant createUpdatedEntity(EntityManager em) {
        Prefabricant prefabricant = new Prefabricant()
            .libelle(UPDATED_LIBELLE);
        return prefabricant;
    }

    @BeforeEach
    public void initTest() {
        prefabricant = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrefabricant() throws Exception {
        int databaseSizeBeforeCreate = prefabricantRepository.findAll().size();
        // Create the Prefabricant
        PrefabricantDTO prefabricantDTO = prefabricantMapper.toDto(prefabricant);
        restPrefabricantMockMvc.perform(post("/api/prefabricants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prefabricantDTO)))
            .andExpect(status().isCreated());

        // Validate the Prefabricant in the database
        List<Prefabricant> prefabricantList = prefabricantRepository.findAll();
        assertThat(prefabricantList).hasSize(databaseSizeBeforeCreate + 1);
        Prefabricant testPrefabricant = prefabricantList.get(prefabricantList.size() - 1);
        assertThat(testPrefabricant.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createPrefabricantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prefabricantRepository.findAll().size();

        // Create the Prefabricant with an existing ID
        prefabricant.setId(1L);
        PrefabricantDTO prefabricantDTO = prefabricantMapper.toDto(prefabricant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrefabricantMockMvc.perform(post("/api/prefabricants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prefabricantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prefabricant in the database
        List<Prefabricant> prefabricantList = prefabricantRepository.findAll();
        assertThat(prefabricantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPrefabricants() throws Exception {
        // Initialize the database
        prefabricantRepository.saveAndFlush(prefabricant);

        // Get all the prefabricantList
        restPrefabricantMockMvc.perform(get("/api/prefabricants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prefabricant.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getPrefabricant() throws Exception {
        // Initialize the database
        prefabricantRepository.saveAndFlush(prefabricant);

        // Get the prefabricant
        restPrefabricantMockMvc.perform(get("/api/prefabricants/{id}", prefabricant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prefabricant.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingPrefabricant() throws Exception {
        // Get the prefabricant
        restPrefabricantMockMvc.perform(get("/api/prefabricants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrefabricant() throws Exception {
        // Initialize the database
        prefabricantRepository.saveAndFlush(prefabricant);

        int databaseSizeBeforeUpdate = prefabricantRepository.findAll().size();

        // Update the prefabricant
        Prefabricant updatedPrefabricant = prefabricantRepository.findById(prefabricant.getId()).get();
        // Disconnect from session so that the updates on updatedPrefabricant are not directly saved in db
        em.detach(updatedPrefabricant);
        updatedPrefabricant
            .libelle(UPDATED_LIBELLE);
        PrefabricantDTO prefabricantDTO = prefabricantMapper.toDto(updatedPrefabricant);

        restPrefabricantMockMvc.perform(put("/api/prefabricants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prefabricantDTO)))
            .andExpect(status().isOk());

        // Validate the Prefabricant in the database
        List<Prefabricant> prefabricantList = prefabricantRepository.findAll();
        assertThat(prefabricantList).hasSize(databaseSizeBeforeUpdate);
        Prefabricant testPrefabricant = prefabricantList.get(prefabricantList.size() - 1);
        assertThat(testPrefabricant.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrefabricant() throws Exception {
        int databaseSizeBeforeUpdate = prefabricantRepository.findAll().size();

        // Create the Prefabricant
        PrefabricantDTO prefabricantDTO = prefabricantMapper.toDto(prefabricant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrefabricantMockMvc.perform(put("/api/prefabricants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prefabricantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prefabricant in the database
        List<Prefabricant> prefabricantList = prefabricantRepository.findAll();
        assertThat(prefabricantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrefabricant() throws Exception {
        // Initialize the database
        prefabricantRepository.saveAndFlush(prefabricant);

        int databaseSizeBeforeDelete = prefabricantRepository.findAll().size();

        // Delete the prefabricant
        restPrefabricantMockMvc.perform(delete("/api/prefabricants/{id}", prefabricant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prefabricant> prefabricantList = prefabricantRepository.findAll();
        assertThat(prefabricantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
