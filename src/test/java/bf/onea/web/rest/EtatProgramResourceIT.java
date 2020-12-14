package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.EtatProgram;
import bf.onea.repository.EtatProgramRepository;
import bf.onea.service.EtatProgramService;
import bf.onea.service.dto.EtatProgramDTO;
import bf.onea.service.mapper.EtatProgramMapper;

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
 * Integration tests for the {@link EtatProgramResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatProgramResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private EtatProgramRepository etatProgramRepository;

    @Autowired
    private EtatProgramMapper etatProgramMapper;

    @Autowired
    private EtatProgramService etatProgramService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatProgramMockMvc;

    private EtatProgram etatProgram;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatProgram createEntity(EntityManager em) {
        EtatProgram etatProgram = new EtatProgram()
            .libelle(DEFAULT_LIBELLE);
        return etatProgram;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatProgram createUpdatedEntity(EntityManager em) {
        EtatProgram etatProgram = new EtatProgram()
            .libelle(UPDATED_LIBELLE);
        return etatProgram;
    }

    @BeforeEach
    public void initTest() {
        etatProgram = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatProgram() throws Exception {
        int databaseSizeBeforeCreate = etatProgramRepository.findAll().size();
        // Create the EtatProgram
        EtatProgramDTO etatProgramDTO = etatProgramMapper.toDto(etatProgram);
        restEtatProgramMockMvc.perform(post("/api/etat-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProgramDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatProgram in the database
        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeCreate + 1);
        EtatProgram testEtatProgram = etatProgramList.get(etatProgramList.size() - 1);
        assertThat(testEtatProgram.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createEtatProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatProgramRepository.findAll().size();

        // Create the EtatProgram with an existing ID
        etatProgram.setId(1L);
        EtatProgramDTO etatProgramDTO = etatProgramMapper.toDto(etatProgram);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatProgramMockMvc.perform(post("/api/etat-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatProgram in the database
        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatProgramRepository.findAll().size();
        // set the field null
        etatProgram.setLibelle(null);

        // Create the EtatProgram, which fails.
        EtatProgramDTO etatProgramDTO = etatProgramMapper.toDto(etatProgram);


        restEtatProgramMockMvc.perform(post("/api/etat-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProgramDTO)))
            .andExpect(status().isBadRequest());

        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatPrograms() throws Exception {
        // Initialize the database
        etatProgramRepository.saveAndFlush(etatProgram);

        // Get all the etatProgramList
        restEtatProgramMockMvc.perform(get("/api/etat-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatProgram.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getEtatProgram() throws Exception {
        // Initialize the database
        etatProgramRepository.saveAndFlush(etatProgram);

        // Get the etatProgram
        restEtatProgramMockMvc.perform(get("/api/etat-programs/{id}", etatProgram.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatProgram.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingEtatProgram() throws Exception {
        // Get the etatProgram
        restEtatProgramMockMvc.perform(get("/api/etat-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatProgram() throws Exception {
        // Initialize the database
        etatProgramRepository.saveAndFlush(etatProgram);

        int databaseSizeBeforeUpdate = etatProgramRepository.findAll().size();

        // Update the etatProgram
        EtatProgram updatedEtatProgram = etatProgramRepository.findById(etatProgram.getId()).get();
        // Disconnect from session so that the updates on updatedEtatProgram are not directly saved in db
        em.detach(updatedEtatProgram);
        updatedEtatProgram
            .libelle(UPDATED_LIBELLE);
        EtatProgramDTO etatProgramDTO = etatProgramMapper.toDto(updatedEtatProgram);

        restEtatProgramMockMvc.perform(put("/api/etat-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProgramDTO)))
            .andExpect(status().isOk());

        // Validate the EtatProgram in the database
        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeUpdate);
        EtatProgram testEtatProgram = etatProgramList.get(etatProgramList.size() - 1);
        assertThat(testEtatProgram.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatProgram() throws Exception {
        int databaseSizeBeforeUpdate = etatProgramRepository.findAll().size();

        // Create the EtatProgram
        EtatProgramDTO etatProgramDTO = etatProgramMapper.toDto(etatProgram);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatProgramMockMvc.perform(put("/api/etat-programs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProgramDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatProgram in the database
        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatProgram() throws Exception {
        // Initialize the database
        etatProgramRepository.saveAndFlush(etatProgram);

        int databaseSizeBeforeDelete = etatProgramRepository.findAll().size();

        // Delete the etatProgram
        restEtatProgramMockMvc.perform(delete("/api/etat-programs/{id}", etatProgram.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatProgram> etatProgramList = etatProgramRepository.findAll();
        assertThat(etatProgramList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
