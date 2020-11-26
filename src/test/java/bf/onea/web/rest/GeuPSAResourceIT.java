package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuPSA;
import bf.onea.repository.GeuPSARepository;
import bf.onea.service.GeuPSAService;
import bf.onea.service.dto.GeuPSADTO;
import bf.onea.service.mapper.GeuPSAMapper;

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
 * Integration tests for the {@link GeuPSAResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuPSAResourceIT {

    private static final Instant DEFAULT_DATE_ELABORATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_ELABORATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_MISE_EN_OEUVRE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_MISE_EN_OEUVRE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private GeuPSARepository geuPSARepository;

    @Autowired
    private GeuPSAMapper geuPSAMapper;

    @Autowired
    private GeuPSAService geuPSAService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuPSAMockMvc;

    private GeuPSA geuPSA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPSA createEntity(EntityManager em) {
        GeuPSA geuPSA = new GeuPSA()
            .dateElaboration(DEFAULT_DATE_ELABORATION)
            .dateMiseEnOeuvre(DEFAULT_DATE_MISE_EN_OEUVRE);
        return geuPSA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPSA createUpdatedEntity(EntityManager em) {
        GeuPSA geuPSA = new GeuPSA()
            .dateElaboration(UPDATED_DATE_ELABORATION)
            .dateMiseEnOeuvre(UPDATED_DATE_MISE_EN_OEUVRE);
        return geuPSA;
    }

    @BeforeEach
    public void initTest() {
        geuPSA = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuPSA() throws Exception {
        int databaseSizeBeforeCreate = geuPSARepository.findAll().size();
        // Create the GeuPSA
        GeuPSADTO geuPSADTO = geuPSAMapper.toDto(geuPSA);
        restGeuPSAMockMvc.perform(post("/api/geu-psas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPSADTO)))
            .andExpect(status().isCreated());

        // Validate the GeuPSA in the database
        List<GeuPSA> geuPSAList = geuPSARepository.findAll();
        assertThat(geuPSAList).hasSize(databaseSizeBeforeCreate + 1);
        GeuPSA testGeuPSA = geuPSAList.get(geuPSAList.size() - 1);
        assertThat(testGeuPSA.getDateElaboration()).isEqualTo(DEFAULT_DATE_ELABORATION);
        assertThat(testGeuPSA.getDateMiseEnOeuvre()).isEqualTo(DEFAULT_DATE_MISE_EN_OEUVRE);
    }

    @Test
    @Transactional
    public void createGeuPSAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuPSARepository.findAll().size();

        // Create the GeuPSA with an existing ID
        geuPSA.setId(1L);
        GeuPSADTO geuPSADTO = geuPSAMapper.toDto(geuPSA);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuPSAMockMvc.perform(post("/api/geu-psas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPSADTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPSA in the database
        List<GeuPSA> geuPSAList = geuPSARepository.findAll();
        assertThat(geuPSAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeuPSAS() throws Exception {
        // Initialize the database
        geuPSARepository.saveAndFlush(geuPSA);

        // Get all the geuPSAList
        restGeuPSAMockMvc.perform(get("/api/geu-psas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuPSA.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateElaboration").value(hasItem(DEFAULT_DATE_ELABORATION.toString())))
            .andExpect(jsonPath("$.[*].dateMiseEnOeuvre").value(hasItem(DEFAULT_DATE_MISE_EN_OEUVRE.toString())));
    }
    
    @Test
    @Transactional
    public void getGeuPSA() throws Exception {
        // Initialize the database
        geuPSARepository.saveAndFlush(geuPSA);

        // Get the geuPSA
        restGeuPSAMockMvc.perform(get("/api/geu-psas/{id}", geuPSA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuPSA.getId().intValue()))
            .andExpect(jsonPath("$.dateElaboration").value(DEFAULT_DATE_ELABORATION.toString()))
            .andExpect(jsonPath("$.dateMiseEnOeuvre").value(DEFAULT_DATE_MISE_EN_OEUVRE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGeuPSA() throws Exception {
        // Get the geuPSA
        restGeuPSAMockMvc.perform(get("/api/geu-psas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuPSA() throws Exception {
        // Initialize the database
        geuPSARepository.saveAndFlush(geuPSA);

        int databaseSizeBeforeUpdate = geuPSARepository.findAll().size();

        // Update the geuPSA
        GeuPSA updatedGeuPSA = geuPSARepository.findById(geuPSA.getId()).get();
        // Disconnect from session so that the updates on updatedGeuPSA are not directly saved in db
        em.detach(updatedGeuPSA);
        updatedGeuPSA
            .dateElaboration(UPDATED_DATE_ELABORATION)
            .dateMiseEnOeuvre(UPDATED_DATE_MISE_EN_OEUVRE);
        GeuPSADTO geuPSADTO = geuPSAMapper.toDto(updatedGeuPSA);

        restGeuPSAMockMvc.perform(put("/api/geu-psas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPSADTO)))
            .andExpect(status().isOk());

        // Validate the GeuPSA in the database
        List<GeuPSA> geuPSAList = geuPSARepository.findAll();
        assertThat(geuPSAList).hasSize(databaseSizeBeforeUpdate);
        GeuPSA testGeuPSA = geuPSAList.get(geuPSAList.size() - 1);
        assertThat(testGeuPSA.getDateElaboration()).isEqualTo(UPDATED_DATE_ELABORATION);
        assertThat(testGeuPSA.getDateMiseEnOeuvre()).isEqualTo(UPDATED_DATE_MISE_EN_OEUVRE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuPSA() throws Exception {
        int databaseSizeBeforeUpdate = geuPSARepository.findAll().size();

        // Create the GeuPSA
        GeuPSADTO geuPSADTO = geuPSAMapper.toDto(geuPSA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuPSAMockMvc.perform(put("/api/geu-psas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPSADTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPSA in the database
        List<GeuPSA> geuPSAList = geuPSARepository.findAll();
        assertThat(geuPSAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuPSA() throws Exception {
        // Initialize the database
        geuPSARepository.saveAndFlush(geuPSA);

        int databaseSizeBeforeDelete = geuPSARepository.findAll().size();

        // Delete the geuPSA
        restGeuPSAMockMvc.perform(delete("/api/geu-psas/{id}", geuPSA.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuPSA> geuPSAList = geuPSARepository.findAll();
        assertThat(geuPSAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
