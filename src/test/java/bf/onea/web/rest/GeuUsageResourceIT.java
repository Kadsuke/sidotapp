package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuUsage;
import bf.onea.repository.GeuUsageRepository;
import bf.onea.service.GeuUsageService;
import bf.onea.service.dto.GeuUsageDTO;
import bf.onea.service.mapper.GeuUsageMapper;

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
 * Integration tests for the {@link GeuUsageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuUsageResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeuUsageRepository geuUsageRepository;

    @Autowired
    private GeuUsageMapper geuUsageMapper;

    @Autowired
    private GeuUsageService geuUsageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuUsageMockMvc;

    private GeuUsage geuUsage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuUsage createEntity(EntityManager em) {
        GeuUsage geuUsage = new GeuUsage()
            .libelle(DEFAULT_LIBELLE);
        return geuUsage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuUsage createUpdatedEntity(EntityManager em) {
        GeuUsage geuUsage = new GeuUsage()
            .libelle(UPDATED_LIBELLE);
        return geuUsage;
    }

    @BeforeEach
    public void initTest() {
        geuUsage = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuUsage() throws Exception {
        int databaseSizeBeforeCreate = geuUsageRepository.findAll().size();
        // Create the GeuUsage
        GeuUsageDTO geuUsageDTO = geuUsageMapper.toDto(geuUsage);
        restGeuUsageMockMvc.perform(post("/api/geu-usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuUsageDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuUsage in the database
        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeCreate + 1);
        GeuUsage testGeuUsage = geuUsageList.get(geuUsageList.size() - 1);
        assertThat(testGeuUsage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeuUsageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuUsageRepository.findAll().size();

        // Create the GeuUsage with an existing ID
        geuUsage.setId(1L);
        GeuUsageDTO geuUsageDTO = geuUsageMapper.toDto(geuUsage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuUsageMockMvc.perform(post("/api/geu-usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuUsageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuUsage in the database
        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuUsageRepository.findAll().size();
        // set the field null
        geuUsage.setLibelle(null);

        // Create the GeuUsage, which fails.
        GeuUsageDTO geuUsageDTO = geuUsageMapper.toDto(geuUsage);


        restGeuUsageMockMvc.perform(post("/api/geu-usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuUsageDTO)))
            .andExpect(status().isBadRequest());

        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeuUsages() throws Exception {
        // Initialize the database
        geuUsageRepository.saveAndFlush(geuUsage);

        // Get all the geuUsageList
        restGeuUsageMockMvc.perform(get("/api/geu-usages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuUsage.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeuUsage() throws Exception {
        // Initialize the database
        geuUsageRepository.saveAndFlush(geuUsage);

        // Get the geuUsage
        restGeuUsageMockMvc.perform(get("/api/geu-usages/{id}", geuUsage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuUsage.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeuUsage() throws Exception {
        // Get the geuUsage
        restGeuUsageMockMvc.perform(get("/api/geu-usages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuUsage() throws Exception {
        // Initialize the database
        geuUsageRepository.saveAndFlush(geuUsage);

        int databaseSizeBeforeUpdate = geuUsageRepository.findAll().size();

        // Update the geuUsage
        GeuUsage updatedGeuUsage = geuUsageRepository.findById(geuUsage.getId()).get();
        // Disconnect from session so that the updates on updatedGeuUsage are not directly saved in db
        em.detach(updatedGeuUsage);
        updatedGeuUsage
            .libelle(UPDATED_LIBELLE);
        GeuUsageDTO geuUsageDTO = geuUsageMapper.toDto(updatedGeuUsage);

        restGeuUsageMockMvc.perform(put("/api/geu-usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuUsageDTO)))
            .andExpect(status().isOk());

        // Validate the GeuUsage in the database
        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeUpdate);
        GeuUsage testGeuUsage = geuUsageList.get(geuUsageList.size() - 1);
        assertThat(testGeuUsage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuUsage() throws Exception {
        int databaseSizeBeforeUpdate = geuUsageRepository.findAll().size();

        // Create the GeuUsage
        GeuUsageDTO geuUsageDTO = geuUsageMapper.toDto(geuUsage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuUsageMockMvc.perform(put("/api/geu-usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuUsageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuUsage in the database
        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuUsage() throws Exception {
        // Initialize the database
        geuUsageRepository.saveAndFlush(geuUsage);

        int databaseSizeBeforeDelete = geuUsageRepository.findAll().size();

        // Delete the geuUsage
        restGeuUsageMockMvc.perform(delete("/api/geu-usages/{id}", geuUsage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuUsage> geuUsageList = geuUsageRepository.findAll();
        assertThat(geuUsageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
