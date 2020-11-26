package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.EtatOuvrage;
import bf.onea.repository.EtatOuvrageRepository;
import bf.onea.service.EtatOuvrageService;
import bf.onea.service.dto.EtatOuvrageDTO;
import bf.onea.service.mapper.EtatOuvrageMapper;

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
 * Integration tests for the {@link EtatOuvrageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatOuvrageResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private EtatOuvrageRepository etatOuvrageRepository;

    @Autowired
    private EtatOuvrageMapper etatOuvrageMapper;

    @Autowired
    private EtatOuvrageService etatOuvrageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatOuvrageMockMvc;

    private EtatOuvrage etatOuvrage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatOuvrage createEntity(EntityManager em) {
        EtatOuvrage etatOuvrage = new EtatOuvrage()
            .libelle(DEFAULT_LIBELLE);
        return etatOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatOuvrage createUpdatedEntity(EntityManager em) {
        EtatOuvrage etatOuvrage = new EtatOuvrage()
            .libelle(UPDATED_LIBELLE);
        return etatOuvrage;
    }

    @BeforeEach
    public void initTest() {
        etatOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatOuvrage() throws Exception {
        int databaseSizeBeforeCreate = etatOuvrageRepository.findAll().size();
        // Create the EtatOuvrage
        EtatOuvrageDTO etatOuvrageDTO = etatOuvrageMapper.toDto(etatOuvrage);
        restEtatOuvrageMockMvc.perform(post("/api/etat-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatOuvrage in the database
        List<EtatOuvrage> etatOuvrageList = etatOuvrageRepository.findAll();
        assertThat(etatOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        EtatOuvrage testEtatOuvrage = etatOuvrageList.get(etatOuvrageList.size() - 1);
        assertThat(testEtatOuvrage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createEtatOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatOuvrageRepository.findAll().size();

        // Create the EtatOuvrage with an existing ID
        etatOuvrage.setId(1L);
        EtatOuvrageDTO etatOuvrageDTO = etatOuvrageMapper.toDto(etatOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatOuvrageMockMvc.perform(post("/api/etat-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatOuvrage in the database
        List<EtatOuvrage> etatOuvrageList = etatOuvrageRepository.findAll();
        assertThat(etatOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtatOuvrages() throws Exception {
        // Initialize the database
        etatOuvrageRepository.saveAndFlush(etatOuvrage);

        // Get all the etatOuvrageList
        restEtatOuvrageMockMvc.perform(get("/api/etat-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getEtatOuvrage() throws Exception {
        // Initialize the database
        etatOuvrageRepository.saveAndFlush(etatOuvrage);

        // Get the etatOuvrage
        restEtatOuvrageMockMvc.perform(get("/api/etat-ouvrages/{id}", etatOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingEtatOuvrage() throws Exception {
        // Get the etatOuvrage
        restEtatOuvrageMockMvc.perform(get("/api/etat-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatOuvrage() throws Exception {
        // Initialize the database
        etatOuvrageRepository.saveAndFlush(etatOuvrage);

        int databaseSizeBeforeUpdate = etatOuvrageRepository.findAll().size();

        // Update the etatOuvrage
        EtatOuvrage updatedEtatOuvrage = etatOuvrageRepository.findById(etatOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedEtatOuvrage are not directly saved in db
        em.detach(updatedEtatOuvrage);
        updatedEtatOuvrage
            .libelle(UPDATED_LIBELLE);
        EtatOuvrageDTO etatOuvrageDTO = etatOuvrageMapper.toDto(updatedEtatOuvrage);

        restEtatOuvrageMockMvc.perform(put("/api/etat-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the EtatOuvrage in the database
        List<EtatOuvrage> etatOuvrageList = etatOuvrageRepository.findAll();
        assertThat(etatOuvrageList).hasSize(databaseSizeBeforeUpdate);
        EtatOuvrage testEtatOuvrage = etatOuvrageList.get(etatOuvrageList.size() - 1);
        assertThat(testEtatOuvrage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = etatOuvrageRepository.findAll().size();

        // Create the EtatOuvrage
        EtatOuvrageDTO etatOuvrageDTO = etatOuvrageMapper.toDto(etatOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatOuvrageMockMvc.perform(put("/api/etat-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatOuvrage in the database
        List<EtatOuvrage> etatOuvrageList = etatOuvrageRepository.findAll();
        assertThat(etatOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatOuvrage() throws Exception {
        // Initialize the database
        etatOuvrageRepository.saveAndFlush(etatOuvrage);

        int databaseSizeBeforeDelete = etatOuvrageRepository.findAll().size();

        // Delete the etatOuvrage
        restEtatOuvrageMockMvc.perform(delete("/api/etat-ouvrages/{id}", etatOuvrage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatOuvrage> etatOuvrageList = etatOuvrageRepository.findAll();
        assertThat(etatOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
