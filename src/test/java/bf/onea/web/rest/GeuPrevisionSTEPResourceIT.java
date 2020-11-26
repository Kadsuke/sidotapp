package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuPrevisionSTEP;
import bf.onea.repository.GeuPrevisionSTEPRepository;
import bf.onea.service.GeuPrevisionSTEPService;
import bf.onea.service.dto.GeuPrevisionSTEPDTO;
import bf.onea.service.mapper.GeuPrevisionSTEPMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeuPrevisionSTEPResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuPrevisionSTEPResourceIT {

    private static final Instant DEFAULT_DATE_PREV_STEP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_PREV_STEP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VOLUME_PREV_STEP = "AAAAAAAAAA";
    private static final String UPDATED_VOLUME_PREV_STEP = "BBBBBBBBBB";

    @Autowired
    private GeuPrevisionSTEPRepository geuPrevisionSTEPRepository;

    @Autowired
    private GeuPrevisionSTEPMapper geuPrevisionSTEPMapper;

    @Autowired
    private GeuPrevisionSTEPService geuPrevisionSTEPService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuPrevisionSTEPMockMvc;

    private GeuPrevisionSTEP geuPrevisionSTEP;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPrevisionSTEP createEntity(EntityManager em) {
        GeuPrevisionSTEP geuPrevisionSTEP = new GeuPrevisionSTEP()
            .datePrevStep(DEFAULT_DATE_PREV_STEP)
            .volumePrevStep(DEFAULT_VOLUME_PREV_STEP);
        return geuPrevisionSTEP;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPrevisionSTEP createUpdatedEntity(EntityManager em) {
        GeuPrevisionSTEP geuPrevisionSTEP = new GeuPrevisionSTEP()
            .datePrevStep(UPDATED_DATE_PREV_STEP)
            .volumePrevStep(UPDATED_VOLUME_PREV_STEP);
        return geuPrevisionSTEP;
    }

    @BeforeEach
    public void initTest() {
        geuPrevisionSTEP = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuPrevisionSTEP() throws Exception {
        int databaseSizeBeforeCreate = geuPrevisionSTEPRepository.findAll().size();
        // Create the GeuPrevisionSTEP
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO = geuPrevisionSTEPMapper.toDto(geuPrevisionSTEP);
        restGeuPrevisionSTEPMockMvc.perform(post("/api/geu-prevision-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTEPDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuPrevisionSTEP in the database
        List<GeuPrevisionSTEP> geuPrevisionSTEPList = geuPrevisionSTEPRepository.findAll();
        assertThat(geuPrevisionSTEPList).hasSize(databaseSizeBeforeCreate + 1);
        GeuPrevisionSTEP testGeuPrevisionSTEP = geuPrevisionSTEPList.get(geuPrevisionSTEPList.size() - 1);
        assertThat(testGeuPrevisionSTEP.getDatePrevStep()).isEqualTo(DEFAULT_DATE_PREV_STEP);
        assertThat(testGeuPrevisionSTEP.getVolumePrevStep()).isEqualTo(DEFAULT_VOLUME_PREV_STEP);
    }

    @Test
    @Transactional
    public void createGeuPrevisionSTEPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuPrevisionSTEPRepository.findAll().size();

        // Create the GeuPrevisionSTEP with an existing ID
        geuPrevisionSTEP.setId(1L);
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO = geuPrevisionSTEPMapper.toDto(geuPrevisionSTEP);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuPrevisionSTEPMockMvc.perform(post("/api/geu-prevision-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTEPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPrevisionSTEP in the database
        List<GeuPrevisionSTEP> geuPrevisionSTEPList = geuPrevisionSTEPRepository.findAll();
        assertThat(geuPrevisionSTEPList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeuPrevisionSTEPS() throws Exception {
        // Initialize the database
        geuPrevisionSTEPRepository.saveAndFlush(geuPrevisionSTEP);

        // Get all the geuPrevisionSTEPList
        restGeuPrevisionSTEPMockMvc.perform(get("/api/geu-prevision-steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuPrevisionSTEP.getId().intValue())))
            .andExpect(jsonPath("$.[*].datePrevStep").value(hasItem(DEFAULT_DATE_PREV_STEP.toString())))
            .andExpect(jsonPath("$.[*].volumePrevStep").value(hasItem(DEFAULT_VOLUME_PREV_STEP)));
    }
    
    @Test
    @Transactional
    public void getGeuPrevisionSTEP() throws Exception {
        // Initialize the database
        geuPrevisionSTEPRepository.saveAndFlush(geuPrevisionSTEP);

        // Get the geuPrevisionSTEP
        restGeuPrevisionSTEPMockMvc.perform(get("/api/geu-prevision-steps/{id}", geuPrevisionSTEP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuPrevisionSTEP.getId().intValue()))
            .andExpect(jsonPath("$.datePrevStep").value(DEFAULT_DATE_PREV_STEP.toString()))
            .andExpect(jsonPath("$.volumePrevStep").value(DEFAULT_VOLUME_PREV_STEP));
    }
    @Test
    @Transactional
    public void getNonExistingGeuPrevisionSTEP() throws Exception {
        // Get the geuPrevisionSTEP
        restGeuPrevisionSTEPMockMvc.perform(get("/api/geu-prevision-steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuPrevisionSTEP() throws Exception {
        // Initialize the database
        geuPrevisionSTEPRepository.saveAndFlush(geuPrevisionSTEP);

        int databaseSizeBeforeUpdate = geuPrevisionSTEPRepository.findAll().size();

        // Update the geuPrevisionSTEP
        GeuPrevisionSTEP updatedGeuPrevisionSTEP = geuPrevisionSTEPRepository.findById(geuPrevisionSTEP.getId()).get();
        // Disconnect from session so that the updates on updatedGeuPrevisionSTEP are not directly saved in db
        em.detach(updatedGeuPrevisionSTEP);
        updatedGeuPrevisionSTEP
            .datePrevStep(UPDATED_DATE_PREV_STEP)
            .volumePrevStep(UPDATED_VOLUME_PREV_STEP);
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO = geuPrevisionSTEPMapper.toDto(updatedGeuPrevisionSTEP);

        restGeuPrevisionSTEPMockMvc.perform(put("/api/geu-prevision-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTEPDTO)))
            .andExpect(status().isOk());

        // Validate the GeuPrevisionSTEP in the database
        List<GeuPrevisionSTEP> geuPrevisionSTEPList = geuPrevisionSTEPRepository.findAll();
        assertThat(geuPrevisionSTEPList).hasSize(databaseSizeBeforeUpdate);
        GeuPrevisionSTEP testGeuPrevisionSTEP = geuPrevisionSTEPList.get(geuPrevisionSTEPList.size() - 1);
        assertThat(testGeuPrevisionSTEP.getDatePrevStep()).isEqualTo(UPDATED_DATE_PREV_STEP);
        assertThat(testGeuPrevisionSTEP.getVolumePrevStep()).isEqualTo(UPDATED_VOLUME_PREV_STEP);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuPrevisionSTEP() throws Exception {
        int databaseSizeBeforeUpdate = geuPrevisionSTEPRepository.findAll().size();

        // Create the GeuPrevisionSTEP
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO = geuPrevisionSTEPMapper.toDto(geuPrevisionSTEP);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuPrevisionSTEPMockMvc.perform(put("/api/geu-prevision-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTEPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPrevisionSTEP in the database
        List<GeuPrevisionSTEP> geuPrevisionSTEPList = geuPrevisionSTEPRepository.findAll();
        assertThat(geuPrevisionSTEPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuPrevisionSTEP() throws Exception {
        // Initialize the database
        geuPrevisionSTEPRepository.saveAndFlush(geuPrevisionSTEP);

        int databaseSizeBeforeDelete = geuPrevisionSTEPRepository.findAll().size();

        // Delete the geuPrevisionSTEP
        restGeuPrevisionSTEPMockMvc.perform(delete("/api/geu-prevision-steps/{id}", geuPrevisionSTEP.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuPrevisionSTEP> geuPrevisionSTEPList = geuPrevisionSTEPRepository.findAll();
        assertThat(geuPrevisionSTEPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
