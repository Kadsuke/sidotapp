package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.CentreRegroupement;
import bf.onea.repository.CentreRegroupementRepository;
import bf.onea.service.CentreRegroupementService;
import bf.onea.service.dto.CentreRegroupementDTO;
import bf.onea.service.mapper.CentreRegroupementMapper;

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
 * Integration tests for the {@link CentreRegroupementResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CentreRegroupementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private CentreRegroupementRepository centreRegroupementRepository;

    @Autowired
    private CentreRegroupementMapper centreRegroupementMapper;

    @Autowired
    private CentreRegroupementService centreRegroupementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCentreRegroupementMockMvc;

    private CentreRegroupement centreRegroupement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentreRegroupement createEntity(EntityManager em) {
        CentreRegroupement centreRegroupement = new CentreRegroupement()
            .libelle(DEFAULT_LIBELLE)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return centreRegroupement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentreRegroupement createUpdatedEntity(EntityManager em) {
        CentreRegroupement centreRegroupement = new CentreRegroupement()
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return centreRegroupement;
    }

    @BeforeEach
    public void initTest() {
        centreRegroupement = createEntity(em);
    }

    @Test
    @Transactional
    public void createCentreRegroupement() throws Exception {
        int databaseSizeBeforeCreate = centreRegroupementRepository.findAll().size();
        // Create the CentreRegroupement
        CentreRegroupementDTO centreRegroupementDTO = centreRegroupementMapper.toDto(centreRegroupement);
        restCentreRegroupementMockMvc.perform(post("/api/centre-regroupements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreRegroupementDTO)))
            .andExpect(status().isCreated());

        // Validate the CentreRegroupement in the database
        List<CentreRegroupement> centreRegroupementList = centreRegroupementRepository.findAll();
        assertThat(centreRegroupementList).hasSize(databaseSizeBeforeCreate + 1);
        CentreRegroupement testCentreRegroupement = centreRegroupementList.get(centreRegroupementList.size() - 1);
        assertThat(testCentreRegroupement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCentreRegroupement.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testCentreRegroupement.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createCentreRegroupementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = centreRegroupementRepository.findAll().size();

        // Create the CentreRegroupement with an existing ID
        centreRegroupement.setId(1L);
        CentreRegroupementDTO centreRegroupementDTO = centreRegroupementMapper.toDto(centreRegroupement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentreRegroupementMockMvc.perform(post("/api/centre-regroupements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreRegroupementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CentreRegroupement in the database
        List<CentreRegroupement> centreRegroupementList = centreRegroupementRepository.findAll();
        assertThat(centreRegroupementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCentreRegroupements() throws Exception {
        // Initialize the database
        centreRegroupementRepository.saveAndFlush(centreRegroupement);

        // Get all the centreRegroupementList
        restCentreRegroupementMockMvc.perform(get("/api/centre-regroupements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centreRegroupement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getCentreRegroupement() throws Exception {
        // Initialize the database
        centreRegroupementRepository.saveAndFlush(centreRegroupement);

        // Get the centreRegroupement
        restCentreRegroupementMockMvc.perform(get("/api/centre-regroupements/{id}", centreRegroupement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(centreRegroupement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingCentreRegroupement() throws Exception {
        // Get the centreRegroupement
        restCentreRegroupementMockMvc.perform(get("/api/centre-regroupements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCentreRegroupement() throws Exception {
        // Initialize the database
        centreRegroupementRepository.saveAndFlush(centreRegroupement);

        int databaseSizeBeforeUpdate = centreRegroupementRepository.findAll().size();

        // Update the centreRegroupement
        CentreRegroupement updatedCentreRegroupement = centreRegroupementRepository.findById(centreRegroupement.getId()).get();
        // Disconnect from session so that the updates on updatedCentreRegroupement are not directly saved in db
        em.detach(updatedCentreRegroupement);
        updatedCentreRegroupement
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        CentreRegroupementDTO centreRegroupementDTO = centreRegroupementMapper.toDto(updatedCentreRegroupement);

        restCentreRegroupementMockMvc.perform(put("/api/centre-regroupements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreRegroupementDTO)))
            .andExpect(status().isOk());

        // Validate the CentreRegroupement in the database
        List<CentreRegroupement> centreRegroupementList = centreRegroupementRepository.findAll();
        assertThat(centreRegroupementList).hasSize(databaseSizeBeforeUpdate);
        CentreRegroupement testCentreRegroupement = centreRegroupementList.get(centreRegroupementList.size() - 1);
        assertThat(testCentreRegroupement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCentreRegroupement.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testCentreRegroupement.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingCentreRegroupement() throws Exception {
        int databaseSizeBeforeUpdate = centreRegroupementRepository.findAll().size();

        // Create the CentreRegroupement
        CentreRegroupementDTO centreRegroupementDTO = centreRegroupementMapper.toDto(centreRegroupement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentreRegroupementMockMvc.perform(put("/api/centre-regroupements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreRegroupementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CentreRegroupement in the database
        List<CentreRegroupement> centreRegroupementList = centreRegroupementRepository.findAll();
        assertThat(centreRegroupementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCentreRegroupement() throws Exception {
        // Initialize the database
        centreRegroupementRepository.saveAndFlush(centreRegroupement);

        int databaseSizeBeforeDelete = centreRegroupementRepository.findAll().size();

        // Delete the centreRegroupement
        restCentreRegroupementMockMvc.perform(delete("/api/centre-regroupements/{id}", centreRegroupement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CentreRegroupement> centreRegroupementList = centreRegroupementRepository.findAll();
        assertThat(centreRegroupementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
