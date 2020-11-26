package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuRealisation;
import bf.onea.repository.GeuRealisationRepository;
import bf.onea.service.GeuRealisationService;
import bf.onea.service.dto.GeuRealisationDTO;
import bf.onea.service.mapper.GeuRealisationMapper;

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
 * Integration tests for the {@link GeuRealisationResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuRealisationResourceIT {

    private static final Float DEFAULT_NB_REALISATION = 1F;
    private static final Float UPDATED_NB_REALISATION = 2F;

    @Autowired
    private GeuRealisationRepository geuRealisationRepository;

    @Autowired
    private GeuRealisationMapper geuRealisationMapper;

    @Autowired
    private GeuRealisationService geuRealisationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuRealisationMockMvc;

    private GeuRealisation geuRealisation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRealisation createEntity(EntityManager em) {
        GeuRealisation geuRealisation = new GeuRealisation()
            .nbRealisation(DEFAULT_NB_REALISATION);
        return geuRealisation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRealisation createUpdatedEntity(EntityManager em) {
        GeuRealisation geuRealisation = new GeuRealisation()
            .nbRealisation(UPDATED_NB_REALISATION);
        return geuRealisation;
    }

    @BeforeEach
    public void initTest() {
        geuRealisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuRealisation() throws Exception {
        int databaseSizeBeforeCreate = geuRealisationRepository.findAll().size();
        // Create the GeuRealisation
        GeuRealisationDTO geuRealisationDTO = geuRealisationMapper.toDto(geuRealisation);
        restGeuRealisationMockMvc.perform(post("/api/geu-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuRealisation in the database
        List<GeuRealisation> geuRealisationList = geuRealisationRepository.findAll();
        assertThat(geuRealisationList).hasSize(databaseSizeBeforeCreate + 1);
        GeuRealisation testGeuRealisation = geuRealisationList.get(geuRealisationList.size() - 1);
        assertThat(testGeuRealisation.getNbRealisation()).isEqualTo(DEFAULT_NB_REALISATION);
    }

    @Test
    @Transactional
    public void createGeuRealisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuRealisationRepository.findAll().size();

        // Create the GeuRealisation with an existing ID
        geuRealisation.setId(1L);
        GeuRealisationDTO geuRealisationDTO = geuRealisationMapper.toDto(geuRealisation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuRealisationMockMvc.perform(post("/api/geu-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRealisation in the database
        List<GeuRealisation> geuRealisationList = geuRealisationRepository.findAll();
        assertThat(geuRealisationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeuRealisations() throws Exception {
        // Initialize the database
        geuRealisationRepository.saveAndFlush(geuRealisation);

        // Get all the geuRealisationList
        restGeuRealisationMockMvc.perform(get("/api/geu-realisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuRealisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbRealisation").value(hasItem(DEFAULT_NB_REALISATION.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getGeuRealisation() throws Exception {
        // Initialize the database
        geuRealisationRepository.saveAndFlush(geuRealisation);

        // Get the geuRealisation
        restGeuRealisationMockMvc.perform(get("/api/geu-realisations/{id}", geuRealisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuRealisation.getId().intValue()))
            .andExpect(jsonPath("$.nbRealisation").value(DEFAULT_NB_REALISATION.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingGeuRealisation() throws Exception {
        // Get the geuRealisation
        restGeuRealisationMockMvc.perform(get("/api/geu-realisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuRealisation() throws Exception {
        // Initialize the database
        geuRealisationRepository.saveAndFlush(geuRealisation);

        int databaseSizeBeforeUpdate = geuRealisationRepository.findAll().size();

        // Update the geuRealisation
        GeuRealisation updatedGeuRealisation = geuRealisationRepository.findById(geuRealisation.getId()).get();
        // Disconnect from session so that the updates on updatedGeuRealisation are not directly saved in db
        em.detach(updatedGeuRealisation);
        updatedGeuRealisation
            .nbRealisation(UPDATED_NB_REALISATION);
        GeuRealisationDTO geuRealisationDTO = geuRealisationMapper.toDto(updatedGeuRealisation);

        restGeuRealisationMockMvc.perform(put("/api/geu-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationDTO)))
            .andExpect(status().isOk());

        // Validate the GeuRealisation in the database
        List<GeuRealisation> geuRealisationList = geuRealisationRepository.findAll();
        assertThat(geuRealisationList).hasSize(databaseSizeBeforeUpdate);
        GeuRealisation testGeuRealisation = geuRealisationList.get(geuRealisationList.size() - 1);
        assertThat(testGeuRealisation.getNbRealisation()).isEqualTo(UPDATED_NB_REALISATION);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuRealisation() throws Exception {
        int databaseSizeBeforeUpdate = geuRealisationRepository.findAll().size();

        // Create the GeuRealisation
        GeuRealisationDTO geuRealisationDTO = geuRealisationMapper.toDto(geuRealisation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuRealisationMockMvc.perform(put("/api/geu-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRealisation in the database
        List<GeuRealisation> geuRealisationList = geuRealisationRepository.findAll();
        assertThat(geuRealisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuRealisation() throws Exception {
        // Initialize the database
        geuRealisationRepository.saveAndFlush(geuRealisation);

        int databaseSizeBeforeDelete = geuRealisationRepository.findAll().size();

        // Delete the geuRealisation
        restGeuRealisationMockMvc.perform(delete("/api/geu-realisations/{id}", geuRealisation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuRealisation> geuRealisationList = geuRealisationRepository.findAll();
        assertThat(geuRealisationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
