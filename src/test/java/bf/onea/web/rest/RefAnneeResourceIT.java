package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.RefAnnee;
import bf.onea.repository.RefAnneeRepository;
import bf.onea.service.RefAnneeService;
import bf.onea.service.dto.RefAnneeDTO;
import bf.onea.service.mapper.RefAnneeMapper;

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
 * Integration tests for the {@link RefAnneeResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RefAnneeResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefAnneeRepository refAnneeRepository;

    @Autowired
    private RefAnneeMapper refAnneeMapper;

    @Autowired
    private RefAnneeService refAnneeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefAnneeMockMvc;

    private RefAnnee refAnnee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefAnnee createEntity(EntityManager em) {
        RefAnnee refAnnee = new RefAnnee()
            .libelle(DEFAULT_LIBELLE);
        return refAnnee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefAnnee createUpdatedEntity(EntityManager em) {
        RefAnnee refAnnee = new RefAnnee()
            .libelle(UPDATED_LIBELLE);
        return refAnnee;
    }

    @BeforeEach
    public void initTest() {
        refAnnee = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefAnnee() throws Exception {
        int databaseSizeBeforeCreate = refAnneeRepository.findAll().size();
        // Create the RefAnnee
        RefAnneeDTO refAnneeDTO = refAnneeMapper.toDto(refAnnee);
        restRefAnneeMockMvc.perform(post("/api/ref-annees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refAnneeDTO)))
            .andExpect(status().isCreated());

        // Validate the RefAnnee in the database
        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeCreate + 1);
        RefAnnee testRefAnnee = refAnneeList.get(refAnneeList.size() - 1);
        assertThat(testRefAnnee.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefAnneeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refAnneeRepository.findAll().size();

        // Create the RefAnnee with an existing ID
        refAnnee.setId(1L);
        RefAnneeDTO refAnneeDTO = refAnneeMapper.toDto(refAnnee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefAnneeMockMvc.perform(post("/api/ref-annees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refAnneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefAnnee in the database
        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refAnneeRepository.findAll().size();
        // set the field null
        refAnnee.setLibelle(null);

        // Create the RefAnnee, which fails.
        RefAnneeDTO refAnneeDTO = refAnneeMapper.toDto(refAnnee);


        restRefAnneeMockMvc.perform(post("/api/ref-annees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refAnneeDTO)))
            .andExpect(status().isBadRequest());

        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefAnnees() throws Exception {
        // Initialize the database
        refAnneeRepository.saveAndFlush(refAnnee);

        // Get all the refAnneeList
        restRefAnneeMockMvc.perform(get("/api/ref-annees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refAnnee.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRefAnnee() throws Exception {
        // Initialize the database
        refAnneeRepository.saveAndFlush(refAnnee);

        // Get the refAnnee
        restRefAnneeMockMvc.perform(get("/api/ref-annees/{id}", refAnnee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refAnnee.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRefAnnee() throws Exception {
        // Get the refAnnee
        restRefAnneeMockMvc.perform(get("/api/ref-annees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefAnnee() throws Exception {
        // Initialize the database
        refAnneeRepository.saveAndFlush(refAnnee);

        int databaseSizeBeforeUpdate = refAnneeRepository.findAll().size();

        // Update the refAnnee
        RefAnnee updatedRefAnnee = refAnneeRepository.findById(refAnnee.getId()).get();
        // Disconnect from session so that the updates on updatedRefAnnee are not directly saved in db
        em.detach(updatedRefAnnee);
        updatedRefAnnee
            .libelle(UPDATED_LIBELLE);
        RefAnneeDTO refAnneeDTO = refAnneeMapper.toDto(updatedRefAnnee);

        restRefAnneeMockMvc.perform(put("/api/ref-annees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refAnneeDTO)))
            .andExpect(status().isOk());

        // Validate the RefAnnee in the database
        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeUpdate);
        RefAnnee testRefAnnee = refAnneeList.get(refAnneeList.size() - 1);
        assertThat(testRefAnnee.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefAnnee() throws Exception {
        int databaseSizeBeforeUpdate = refAnneeRepository.findAll().size();

        // Create the RefAnnee
        RefAnneeDTO refAnneeDTO = refAnneeMapper.toDto(refAnnee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefAnneeMockMvc.perform(put("/api/ref-annees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refAnneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefAnnee in the database
        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefAnnee() throws Exception {
        // Initialize the database
        refAnneeRepository.saveAndFlush(refAnnee);

        int databaseSizeBeforeDelete = refAnneeRepository.findAll().size();

        // Delete the refAnnee
        restRefAnneeMockMvc.perform(delete("/api/ref-annees/{id}", refAnnee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefAnnee> refAnneeList = refAnneeRepository.findAll();
        assertThat(refAnneeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
