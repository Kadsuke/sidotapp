package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.ModeEvacuationEauUsee;
import bf.onea.repository.ModeEvacuationEauUseeRepository;
import bf.onea.service.ModeEvacuationEauUseeService;
import bf.onea.service.dto.ModeEvacuationEauUseeDTO;
import bf.onea.service.mapper.ModeEvacuationEauUseeMapper;

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
 * Integration tests for the {@link ModeEvacuationEauUseeResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModeEvacuationEauUseeResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ModeEvacuationEauUseeRepository modeEvacuationEauUseeRepository;

    @Autowired
    private ModeEvacuationEauUseeMapper modeEvacuationEauUseeMapper;

    @Autowired
    private ModeEvacuationEauUseeService modeEvacuationEauUseeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModeEvacuationEauUseeMockMvc;

    private ModeEvacuationEauUsee modeEvacuationEauUsee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeEvacuationEauUsee createEntity(EntityManager em) {
        ModeEvacuationEauUsee modeEvacuationEauUsee = new ModeEvacuationEauUsee()
            .libelle(DEFAULT_LIBELLE);
        return modeEvacuationEauUsee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModeEvacuationEauUsee createUpdatedEntity(EntityManager em) {
        ModeEvacuationEauUsee modeEvacuationEauUsee = new ModeEvacuationEauUsee()
            .libelle(UPDATED_LIBELLE);
        return modeEvacuationEauUsee;
    }

    @BeforeEach
    public void initTest() {
        modeEvacuationEauUsee = createEntity(em);
    }

    @Test
    @Transactional
    public void createModeEvacuationEauUsee() throws Exception {
        int databaseSizeBeforeCreate = modeEvacuationEauUseeRepository.findAll().size();
        // Create the ModeEvacuationEauUsee
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO = modeEvacuationEauUseeMapper.toDto(modeEvacuationEauUsee);
        restModeEvacuationEauUseeMockMvc.perform(post("/api/mode-evacuation-eau-usees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacuationEauUseeDTO)))
            .andExpect(status().isCreated());

        // Validate the ModeEvacuationEauUsee in the database
        List<ModeEvacuationEauUsee> modeEvacuationEauUseeList = modeEvacuationEauUseeRepository.findAll();
        assertThat(modeEvacuationEauUseeList).hasSize(databaseSizeBeforeCreate + 1);
        ModeEvacuationEauUsee testModeEvacuationEauUsee = modeEvacuationEauUseeList.get(modeEvacuationEauUseeList.size() - 1);
        assertThat(testModeEvacuationEauUsee.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createModeEvacuationEauUseeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modeEvacuationEauUseeRepository.findAll().size();

        // Create the ModeEvacuationEauUsee with an existing ID
        modeEvacuationEauUsee.setId(1L);
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO = modeEvacuationEauUseeMapper.toDto(modeEvacuationEauUsee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModeEvacuationEauUseeMockMvc.perform(post("/api/mode-evacuation-eau-usees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacuationEauUseeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModeEvacuationEauUsee in the database
        List<ModeEvacuationEauUsee> modeEvacuationEauUseeList = modeEvacuationEauUseeRepository.findAll();
        assertThat(modeEvacuationEauUseeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllModeEvacuationEauUsees() throws Exception {
        // Initialize the database
        modeEvacuationEauUseeRepository.saveAndFlush(modeEvacuationEauUsee);

        // Get all the modeEvacuationEauUseeList
        restModeEvacuationEauUseeMockMvc.perform(get("/api/mode-evacuation-eau-usees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modeEvacuationEauUsee.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getModeEvacuationEauUsee() throws Exception {
        // Initialize the database
        modeEvacuationEauUseeRepository.saveAndFlush(modeEvacuationEauUsee);

        // Get the modeEvacuationEauUsee
        restModeEvacuationEauUseeMockMvc.perform(get("/api/mode-evacuation-eau-usees/{id}", modeEvacuationEauUsee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modeEvacuationEauUsee.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingModeEvacuationEauUsee() throws Exception {
        // Get the modeEvacuationEauUsee
        restModeEvacuationEauUseeMockMvc.perform(get("/api/mode-evacuation-eau-usees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModeEvacuationEauUsee() throws Exception {
        // Initialize the database
        modeEvacuationEauUseeRepository.saveAndFlush(modeEvacuationEauUsee);

        int databaseSizeBeforeUpdate = modeEvacuationEauUseeRepository.findAll().size();

        // Update the modeEvacuationEauUsee
        ModeEvacuationEauUsee updatedModeEvacuationEauUsee = modeEvacuationEauUseeRepository.findById(modeEvacuationEauUsee.getId()).get();
        // Disconnect from session so that the updates on updatedModeEvacuationEauUsee are not directly saved in db
        em.detach(updatedModeEvacuationEauUsee);
        updatedModeEvacuationEauUsee
            .libelle(UPDATED_LIBELLE);
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO = modeEvacuationEauUseeMapper.toDto(updatedModeEvacuationEauUsee);

        restModeEvacuationEauUseeMockMvc.perform(put("/api/mode-evacuation-eau-usees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacuationEauUseeDTO)))
            .andExpect(status().isOk());

        // Validate the ModeEvacuationEauUsee in the database
        List<ModeEvacuationEauUsee> modeEvacuationEauUseeList = modeEvacuationEauUseeRepository.findAll();
        assertThat(modeEvacuationEauUseeList).hasSize(databaseSizeBeforeUpdate);
        ModeEvacuationEauUsee testModeEvacuationEauUsee = modeEvacuationEauUseeList.get(modeEvacuationEauUseeList.size() - 1);
        assertThat(testModeEvacuationEauUsee.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingModeEvacuationEauUsee() throws Exception {
        int databaseSizeBeforeUpdate = modeEvacuationEauUseeRepository.findAll().size();

        // Create the ModeEvacuationEauUsee
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO = modeEvacuationEauUseeMapper.toDto(modeEvacuationEauUsee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModeEvacuationEauUseeMockMvc.perform(put("/api/mode-evacuation-eau-usees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modeEvacuationEauUseeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModeEvacuationEauUsee in the database
        List<ModeEvacuationEauUsee> modeEvacuationEauUseeList = modeEvacuationEauUseeRepository.findAll();
        assertThat(modeEvacuationEauUseeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModeEvacuationEauUsee() throws Exception {
        // Initialize the database
        modeEvacuationEauUseeRepository.saveAndFlush(modeEvacuationEauUsee);

        int databaseSizeBeforeDelete = modeEvacuationEauUseeRepository.findAll().size();

        // Delete the modeEvacuationEauUsee
        restModeEvacuationEauUseeMockMvc.perform(delete("/api/mode-evacuation-eau-usees/{id}", modeEvacuationEauUsee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModeEvacuationEauUsee> modeEvacuationEauUseeList = modeEvacuationEauUseeRepository.findAll();
        assertThat(modeEvacuationEauUseeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
