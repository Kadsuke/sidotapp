package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.EtatProjet;
import bf.onea.repository.EtatProjetRepository;
import bf.onea.service.EtatProjetService;
import bf.onea.service.dto.EtatProjetDTO;
import bf.onea.service.mapper.EtatProjetMapper;

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
 * Integration tests for the {@link EtatProjetResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatProjetResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private EtatProjetRepository etatProjetRepository;

    @Autowired
    private EtatProjetMapper etatProjetMapper;

    @Autowired
    private EtatProjetService etatProjetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatProjetMockMvc;

    private EtatProjet etatProjet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatProjet createEntity(EntityManager em) {
        EtatProjet etatProjet = new EtatProjet()
            .libelle(DEFAULT_LIBELLE);
        return etatProjet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatProjet createUpdatedEntity(EntityManager em) {
        EtatProjet etatProjet = new EtatProjet()
            .libelle(UPDATED_LIBELLE);
        return etatProjet;
    }

    @BeforeEach
    public void initTest() {
        etatProjet = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatProjet() throws Exception {
        int databaseSizeBeforeCreate = etatProjetRepository.findAll().size();
        // Create the EtatProjet
        EtatProjetDTO etatProjetDTO = etatProjetMapper.toDto(etatProjet);
        restEtatProjetMockMvc.perform(post("/api/etat-projets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProjetDTO)))
            .andExpect(status().isCreated());

        // Validate the EtatProjet in the database
        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeCreate + 1);
        EtatProjet testEtatProjet = etatProjetList.get(etatProjetList.size() - 1);
        assertThat(testEtatProjet.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createEtatProjetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatProjetRepository.findAll().size();

        // Create the EtatProjet with an existing ID
        etatProjet.setId(1L);
        EtatProjetDTO etatProjetDTO = etatProjetMapper.toDto(etatProjet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatProjetMockMvc.perform(post("/api/etat-projets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProjetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatProjet in the database
        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatProjetRepository.findAll().size();
        // set the field null
        etatProjet.setLibelle(null);

        // Create the EtatProjet, which fails.
        EtatProjetDTO etatProjetDTO = etatProjetMapper.toDto(etatProjet);


        restEtatProjetMockMvc.perform(post("/api/etat-projets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProjetDTO)))
            .andExpect(status().isBadRequest());

        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatProjets() throws Exception {
        // Initialize the database
        etatProjetRepository.saveAndFlush(etatProjet);

        // Get all the etatProjetList
        restEtatProjetMockMvc.perform(get("/api/etat-projets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatProjet.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getEtatProjet() throws Exception {
        // Initialize the database
        etatProjetRepository.saveAndFlush(etatProjet);

        // Get the etatProjet
        restEtatProjetMockMvc.perform(get("/api/etat-projets/{id}", etatProjet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatProjet.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingEtatProjet() throws Exception {
        // Get the etatProjet
        restEtatProjetMockMvc.perform(get("/api/etat-projets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatProjet() throws Exception {
        // Initialize the database
        etatProjetRepository.saveAndFlush(etatProjet);

        int databaseSizeBeforeUpdate = etatProjetRepository.findAll().size();

        // Update the etatProjet
        EtatProjet updatedEtatProjet = etatProjetRepository.findById(etatProjet.getId()).get();
        // Disconnect from session so that the updates on updatedEtatProjet are not directly saved in db
        em.detach(updatedEtatProjet);
        updatedEtatProjet
            .libelle(UPDATED_LIBELLE);
        EtatProjetDTO etatProjetDTO = etatProjetMapper.toDto(updatedEtatProjet);

        restEtatProjetMockMvc.perform(put("/api/etat-projets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProjetDTO)))
            .andExpect(status().isOk());

        // Validate the EtatProjet in the database
        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeUpdate);
        EtatProjet testEtatProjet = etatProjetList.get(etatProjetList.size() - 1);
        assertThat(testEtatProjet.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatProjet() throws Exception {
        int databaseSizeBeforeUpdate = etatProjetRepository.findAll().size();

        // Create the EtatProjet
        EtatProjetDTO etatProjetDTO = etatProjetMapper.toDto(etatProjet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatProjetMockMvc.perform(put("/api/etat-projets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etatProjetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtatProjet in the database
        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatProjet() throws Exception {
        // Initialize the database
        etatProjetRepository.saveAndFlush(etatProjet);

        int databaseSizeBeforeDelete = etatProjetRepository.findAll().size();

        // Delete the etatProjet
        restEtatProjetMockMvc.perform(delete("/api/etat-projets/{id}", etatProjet.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatProjet> etatProjetList = etatProjetRepository.findAll();
        assertThat(etatProjetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
