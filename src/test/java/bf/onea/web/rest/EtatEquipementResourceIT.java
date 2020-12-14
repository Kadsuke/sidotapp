package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.EtatEquipement;
import bf.onea.repository.EtatEquipementRepository;
import bf.onea.service.EtatEquipementService;
import bf.onea.service.dto.EtatEquipementDTO;
import bf.onea.service.mapper.EtatEquipementMapper;

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
 * Integration tests for the {@link EtatEquipementResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatEquipementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private EtatEquipementRepository etatEquipementRepository;

    @Autowired
    private EtatEquipementMapper etatEquipementMapper;

    @Autowired
    private EtatEquipementService etatEquipementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatEquipementMockMvc;

    private EtatEquipement etatEquipement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatEquipement createEntity(EntityManager em) {
        EtatEquipement etatEquipement = new EtatEquipement()
            .libelle(DEFAULT_LIBELLE);
        return etatEquipement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatEquipement createUpdatedEntity(EntityManager em) {
        EtatEquipement etatEquipement = new EtatEquipement()
            .libelle(UPDATED_LIBELLE);
        return etatEquipement;
    }

    @BeforeEach
    public void initTest() {
        etatEquipement = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatEquipement() throws Exception {
        int databaseSizeBeforeCreate = etatEquipementRepository.findAll().size();
        // Create the EtatEquipement
        EtatEquipementDTO etatEquipementDTO = etatEquipementMapper.toDto(etatEquipement);
        restEtatEquipementMockMvc.perform(post("/api/etat-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatEquipementDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatEquipement in the database
        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeCreate + 1);
        EtatEquipement testEtatEquipement = etatEquipementList.get(etatEquipementList.size() - 1);
        assertThat(testEtatEquipement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createEtatEquipementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatEquipementRepository.findAll().size();

        // Create the EtatEquipement with an existing ID
        etatEquipement.setId(1L);
        EtatEquipementDTO etatEquipementDTO = etatEquipementMapper.toDto(etatEquipement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatEquipementMockMvc.perform(post("/api/etat-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatEquipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatEquipement in the database
        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatEquipementRepository.findAll().size();
        // set the field null
        etatEquipement.setLibelle(null);

        // Create the EtatEquipement, which fails.
        EtatEquipementDTO etatEquipementDTO = etatEquipementMapper.toDto(etatEquipement);


        restEtatEquipementMockMvc.perform(post("/api/etat-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatEquipementDTO)))
            .andExpect(status().isBadRequest());

        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatEquipements() throws Exception {
        // Initialize the database
        etatEquipementRepository.saveAndFlush(etatEquipement);

        // Get all the etatEquipementList
        restEtatEquipementMockMvc.perform(get("/api/etat-equipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatEquipement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getEtatEquipement() throws Exception {
        // Initialize the database
        etatEquipementRepository.saveAndFlush(etatEquipement);

        // Get the etatEquipement
        restEtatEquipementMockMvc.perform(get("/api/etat-equipements/{id}", etatEquipement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatEquipement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingEtatEquipement() throws Exception {
        // Get the etatEquipement
        restEtatEquipementMockMvc.perform(get("/api/etat-equipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatEquipement() throws Exception {
        // Initialize the database
        etatEquipementRepository.saveAndFlush(etatEquipement);

        int databaseSizeBeforeUpdate = etatEquipementRepository.findAll().size();

        // Update the etatEquipement
        EtatEquipement updatedEtatEquipement = etatEquipementRepository.findById(etatEquipement.getId()).get();
        // Disconnect from session so that the updates on updatedEtatEquipement are not directly saved in db
        em.detach(updatedEtatEquipement);
        updatedEtatEquipement
            .libelle(UPDATED_LIBELLE);
        EtatEquipementDTO etatEquipementDTO = etatEquipementMapper.toDto(updatedEtatEquipement);

        restEtatEquipementMockMvc.perform(put("/api/etat-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatEquipementDTO)))
            .andExpect(status().isOk());

        // Validate the EtatEquipement in the database
        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeUpdate);
        EtatEquipement testEtatEquipement = etatEquipementList.get(etatEquipementList.size() - 1);
        assertThat(testEtatEquipement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatEquipement() throws Exception {
        int databaseSizeBeforeUpdate = etatEquipementRepository.findAll().size();

        // Create the EtatEquipement
        EtatEquipementDTO etatEquipementDTO = etatEquipementMapper.toDto(etatEquipement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatEquipementMockMvc.perform(put("/api/etat-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatEquipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatEquipement in the database
        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatEquipement() throws Exception {
        // Initialize the database
        etatEquipementRepository.saveAndFlush(etatEquipement);

        int databaseSizeBeforeDelete = etatEquipementRepository.findAll().size();

        // Delete the etatEquipement
        restEtatEquipementMockMvc.perform(delete("/api/etat-equipements/{id}", etatEquipement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatEquipement> etatEquipementList = etatEquipementRepository.findAll();
        assertThat(etatEquipementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
