package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Tacherons;
import bf.onea.repository.TacheronsRepository;
import bf.onea.service.TacheronsService;
import bf.onea.service.dto.TacheronsDTO;
import bf.onea.service.mapper.TacheronsMapper;

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
 * Integration tests for the {@link TacheronsResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TacheronsResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    @Autowired
    private TacheronsRepository tacheronsRepository;

    @Autowired
    private TacheronsMapper tacheronsMapper;

    @Autowired
    private TacheronsService tacheronsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTacheronsMockMvc;

    private Tacherons tacherons;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tacherons createEntity(EntityManager em) {
        Tacherons tacherons = new Tacherons()
            .nom(DEFAULT_NOM)
            .tel(DEFAULT_TEL)
            .adresse(DEFAULT_ADRESSE);
        return tacherons;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tacherons createUpdatedEntity(EntityManager em) {
        Tacherons tacherons = new Tacherons()
            .nom(UPDATED_NOM)
            .tel(UPDATED_TEL)
            .adresse(UPDATED_ADRESSE);
        return tacherons;
    }

    @BeforeEach
    public void initTest() {
        tacherons = createEntity(em);
    }

    @Test
    @Transactional
    public void createTacherons() throws Exception {
        int databaseSizeBeforeCreate = tacheronsRepository.findAll().size();
        // Create the Tacherons
        TacheronsDTO tacheronsDTO = tacheronsMapper.toDto(tacherons);
        restTacheronsMockMvc.perform(post("/api/tacherons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tacheronsDTO)))
            .andExpect(status().isCreated());

        // Validate the Tacherons in the database
        List<Tacherons> tacheronsList = tacheronsRepository.findAll();
        assertThat(tacheronsList).hasSize(databaseSizeBeforeCreate + 1);
        Tacherons testTacherons = tacheronsList.get(tacheronsList.size() - 1);
        assertThat(testTacherons.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTacherons.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testTacherons.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
    }

    @Test
    @Transactional
    public void createTacheronsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tacheronsRepository.findAll().size();

        // Create the Tacherons with an existing ID
        tacherons.setId(1L);
        TacheronsDTO tacheronsDTO = tacheronsMapper.toDto(tacherons);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTacheronsMockMvc.perform(post("/api/tacherons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tacheronsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tacherons in the database
        List<Tacherons> tacheronsList = tacheronsRepository.findAll();
        assertThat(tacheronsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTacherons() throws Exception {
        // Initialize the database
        tacheronsRepository.saveAndFlush(tacherons);

        // Get all the tacheronsList
        restTacheronsMockMvc.perform(get("/api/tacherons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tacherons.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)));
    }
    
    @Test
    @Transactional
    public void getTacherons() throws Exception {
        // Initialize the database
        tacheronsRepository.saveAndFlush(tacherons);

        // Get the tacherons
        restTacheronsMockMvc.perform(get("/api/tacherons/{id}", tacherons.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tacherons.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE));
    }
    @Test
    @Transactional
    public void getNonExistingTacherons() throws Exception {
        // Get the tacherons
        restTacheronsMockMvc.perform(get("/api/tacherons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTacherons() throws Exception {
        // Initialize the database
        tacheronsRepository.saveAndFlush(tacherons);

        int databaseSizeBeforeUpdate = tacheronsRepository.findAll().size();

        // Update the tacherons
        Tacherons updatedTacherons = tacheronsRepository.findById(tacherons.getId()).get();
        // Disconnect from session so that the updates on updatedTacherons are not directly saved in db
        em.detach(updatedTacherons);
        updatedTacherons
            .nom(UPDATED_NOM)
            .tel(UPDATED_TEL)
            .adresse(UPDATED_ADRESSE);
        TacheronsDTO tacheronsDTO = tacheronsMapper.toDto(updatedTacherons);

        restTacheronsMockMvc.perform(put("/api/tacherons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tacheronsDTO)))
            .andExpect(status().isOk());

        // Validate the Tacherons in the database
        List<Tacherons> tacheronsList = tacheronsRepository.findAll();
        assertThat(tacheronsList).hasSize(databaseSizeBeforeUpdate);
        Tacherons testTacherons = tacheronsList.get(tacheronsList.size() - 1);
        assertThat(testTacherons.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTacherons.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testTacherons.getAdresse()).isEqualTo(UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void updateNonExistingTacherons() throws Exception {
        int databaseSizeBeforeUpdate = tacheronsRepository.findAll().size();

        // Create the Tacherons
        TacheronsDTO tacheronsDTO = tacheronsMapper.toDto(tacherons);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTacheronsMockMvc.perform(put("/api/tacherons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tacheronsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tacherons in the database
        List<Tacherons> tacheronsList = tacheronsRepository.findAll();
        assertThat(tacheronsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTacherons() throws Exception {
        // Initialize the database
        tacheronsRepository.saveAndFlush(tacherons);

        int databaseSizeBeforeDelete = tacheronsRepository.findAll().size();

        // Delete the tacherons
        restTacheronsMockMvc.perform(delete("/api/tacherons/{id}", tacherons.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tacherons> tacheronsList = tacheronsRepository.findAll();
        assertThat(tacheronsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
