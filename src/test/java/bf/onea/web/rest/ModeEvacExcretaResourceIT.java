package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.ModeEvacExcreta;
import bf.onea.repository.ModeEvacExcretaRepository;
import bf.onea.service.ModeEvacExcretaService;
import bf.onea.service.dto.ModeEvacExcretaDTO;
import bf.onea.service.mapper.ModeEvacExcretaMapper;

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
 * Integration tests for the {@link ModeEvacExcretaResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModeEvacExcretaResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ModeEvacExcretaRepository modeEvacExcretaRepository;

    @Autowired
    private ModeEvacExcretaMapper modeEvacExcretaMapper;

    @Autowired
    private ModeEvacExcretaService modeEvacExcretaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModeEvacExcretaMockMvc;

    private ModeEvacExcreta modeEvacExcreta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeEvacExcreta createEntity(EntityManager em) {
        ModeEvacExcreta modeEvacExcreta = new ModeEvacExcreta()
            .libelle(DEFAULT_LIBELLE);
        return modeEvacExcreta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeEvacExcreta createUpdatedEntity(EntityManager em) {
        ModeEvacExcreta modeEvacExcreta = new ModeEvacExcreta()
            .libelle(UPDATED_LIBELLE);
        return modeEvacExcreta;
    }

    @BeforeEach
    public void initTest() {
        modeEvacExcreta = createEntity(em);
    }

    @Test
    @Transactional
    public void createModeEvacExcreta() throws Exception {
        int databaseSizeBeforeCreate = modeEvacExcretaRepository.findAll().size();
        // Create the ModeEvacExcreta
        ModeEvacExcretaDTO modeEvacExcretaDTO = modeEvacExcretaMapper.toDto(modeEvacExcreta);
        restModeEvacExcretaMockMvc.perform(post("/api/mode-evac-excretas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacExcretaDTO)))
            .andExpect(status().isCreated());

        // Validate the ModeEvacExcreta in the database
        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeCreate + 1);
        ModeEvacExcreta testModeEvacExcreta = modeEvacExcretaList.get(modeEvacExcretaList.size() - 1);
        assertThat(testModeEvacExcreta.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createModeEvacExcretaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modeEvacExcretaRepository.findAll().size();

        // Create the ModeEvacExcreta with an existing ID
        modeEvacExcreta.setId(1L);
        ModeEvacExcretaDTO modeEvacExcretaDTO = modeEvacExcretaMapper.toDto(modeEvacExcreta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModeEvacExcretaMockMvc.perform(post("/api/mode-evac-excretas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacExcretaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModeEvacExcreta in the database
        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = modeEvacExcretaRepository.findAll().size();
        // set the field null
        modeEvacExcreta.setLibelle(null);

        // Create the ModeEvacExcreta, which fails.
        ModeEvacExcretaDTO modeEvacExcretaDTO = modeEvacExcretaMapper.toDto(modeEvacExcreta);


        restModeEvacExcretaMockMvc.perform(post("/api/mode-evac-excretas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacExcretaDTO)))
            .andExpect(status().isBadRequest());

        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModeEvacExcretas() throws Exception {
        // Initialize the database
        modeEvacExcretaRepository.saveAndFlush(modeEvacExcreta);

        // Get all the modeEvacExcretaList
        restModeEvacExcretaMockMvc.perform(get("/api/mode-evac-excretas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeEvacExcreta.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getModeEvacExcreta() throws Exception {
        // Initialize the database
        modeEvacExcretaRepository.saveAndFlush(modeEvacExcreta);

        // Get the modeEvacExcreta
        restModeEvacExcretaMockMvc.perform(get("/api/mode-evac-excretas/{id}", modeEvacExcreta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modeEvacExcreta.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingModeEvacExcreta() throws Exception {
        // Get the modeEvacExcreta
        restModeEvacExcretaMockMvc.perform(get("/api/mode-evac-excretas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModeEvacExcreta() throws Exception {
        // Initialize the database
        modeEvacExcretaRepository.saveAndFlush(modeEvacExcreta);

        int databaseSizeBeforeUpdate = modeEvacExcretaRepository.findAll().size();

        // Update the modeEvacExcreta
        ModeEvacExcreta updatedModeEvacExcreta = modeEvacExcretaRepository.findById(modeEvacExcreta.getId()).get();
        // Disconnect from session so that the updates on updatedModeEvacExcreta are not directly saved in db
        em.detach(updatedModeEvacExcreta);
        updatedModeEvacExcreta
            .libelle(UPDATED_LIBELLE);
        ModeEvacExcretaDTO modeEvacExcretaDTO = modeEvacExcretaMapper.toDto(updatedModeEvacExcreta);

        restModeEvacExcretaMockMvc.perform(put("/api/mode-evac-excretas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacExcretaDTO)))
            .andExpect(status().isOk());

        // Validate the ModeEvacExcreta in the database
        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeUpdate);
        ModeEvacExcreta testModeEvacExcreta = modeEvacExcretaList.get(modeEvacExcretaList.size() - 1);
        assertThat(testModeEvacExcreta.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingModeEvacExcreta() throws Exception {
        int databaseSizeBeforeUpdate = modeEvacExcretaRepository.findAll().size();

        // Create the ModeEvacExcreta
        ModeEvacExcretaDTO modeEvacExcretaDTO = modeEvacExcretaMapper.toDto(modeEvacExcreta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModeEvacExcretaMockMvc.perform(put("/api/mode-evac-excretas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacExcretaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModeEvacExcreta in the database
        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModeEvacExcreta() throws Exception {
        // Initialize the database
        modeEvacExcretaRepository.saveAndFlush(modeEvacExcreta);

        int databaseSizeBeforeDelete = modeEvacExcretaRepository.findAll().size();

        // Delete the modeEvacExcreta
        restModeEvacExcretaMockMvc.perform(delete("/api/mode-evac-excretas/{id}", modeEvacExcreta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModeEvacExcreta> modeEvacExcretaList = modeEvacExcretaRepository.findAll();
        assertThat(modeEvacExcretaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
