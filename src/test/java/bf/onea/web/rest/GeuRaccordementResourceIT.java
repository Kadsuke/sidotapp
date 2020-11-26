package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuRaccordement;
import bf.onea.repository.GeuRaccordementRepository;
import bf.onea.service.GeuRaccordementService;
import bf.onea.service.dto.GeuRaccordementDTO;
import bf.onea.service.mapper.GeuRaccordementMapper;

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
 * Integration tests for the {@link GeuRaccordementResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuRaccordementResourceIT {

    private static final Long DEFAULT_NUM_ABONNEMENT = 1L;
    private static final Long UPDATED_NUM_ABONNEMENT = 2L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPRIETAIRE_PARACELLE = "AAAAAAAAAA";
    private static final String UPDATED_PROPRIETAIRE_PARACELLE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_USAGE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_USAGE = "BBBBBBBBBB";

    @Autowired
    private GeuRaccordementRepository geuRaccordementRepository;

    @Autowired
    private GeuRaccordementMapper geuRaccordementMapper;

    @Autowired
    private GeuRaccordementService geuRaccordementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuRaccordementMockMvc;

    private GeuRaccordement geuRaccordement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRaccordement createEntity(EntityManager em) {
        GeuRaccordement geuRaccordement = new GeuRaccordement()
            .numAbonnement(DEFAULT_NUM_ABONNEMENT)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .proprietaireParacelle(DEFAULT_PROPRIETAIRE_PARACELLE)
            .entreprise(DEFAULT_ENTREPRISE)
            .otherUsage(DEFAULT_OTHER_USAGE);
        return geuRaccordement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRaccordement createUpdatedEntity(EntityManager em) {
        GeuRaccordement geuRaccordement = new GeuRaccordement()
            .numAbonnement(UPDATED_NUM_ABONNEMENT)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .proprietaireParacelle(UPDATED_PROPRIETAIRE_PARACELLE)
            .entreprise(UPDATED_ENTREPRISE)
            .otherUsage(UPDATED_OTHER_USAGE);
        return geuRaccordement;
    }

    @BeforeEach
    public void initTest() {
        geuRaccordement = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuRaccordement() throws Exception {
        int databaseSizeBeforeCreate = geuRaccordementRepository.findAll().size();
        // Create the GeuRaccordement
        GeuRaccordementDTO geuRaccordementDTO = geuRaccordementMapper.toDto(geuRaccordement);
        restGeuRaccordementMockMvc.perform(post("/api/geu-raccordements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRaccordementDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuRaccordement in the database
        List<GeuRaccordement> geuRaccordementList = geuRaccordementRepository.findAll();
        assertThat(geuRaccordementList).hasSize(databaseSizeBeforeCreate + 1);
        GeuRaccordement testGeuRaccordement = geuRaccordementList.get(geuRaccordementList.size() - 1);
        assertThat(testGeuRaccordement.getNumAbonnement()).isEqualTo(DEFAULT_NUM_ABONNEMENT);
        assertThat(testGeuRaccordement.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testGeuRaccordement.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testGeuRaccordement.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testGeuRaccordement.getProprietaireParacelle()).isEqualTo(DEFAULT_PROPRIETAIRE_PARACELLE);
        assertThat(testGeuRaccordement.getEntreprise()).isEqualTo(DEFAULT_ENTREPRISE);
        assertThat(testGeuRaccordement.getOtherUsage()).isEqualTo(DEFAULT_OTHER_USAGE);
    }

    @Test
    @Transactional
    public void createGeuRaccordementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuRaccordementRepository.findAll().size();

        // Create the GeuRaccordement with an existing ID
        geuRaccordement.setId(1L);
        GeuRaccordementDTO geuRaccordementDTO = geuRaccordementMapper.toDto(geuRaccordement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuRaccordementMockMvc.perform(post("/api/geu-raccordements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRaccordementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRaccordement in the database
        List<GeuRaccordement> geuRaccordementList = geuRaccordementRepository.findAll();
        assertThat(geuRaccordementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeuRaccordements() throws Exception {
        // Initialize the database
        geuRaccordementRepository.saveAndFlush(geuRaccordement);

        // Get all the geuRaccordementList
        restGeuRaccordementMockMvc.perform(get("/api/geu-raccordements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuRaccordement.getId().intValue())))
            .andExpect(jsonPath("$.[*].numAbonnement").value(hasItem(DEFAULT_NUM_ABONNEMENT.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].proprietaireParacelle").value(hasItem(DEFAULT_PROPRIETAIRE_PARACELLE)))
            .andExpect(jsonPath("$.[*].entreprise").value(hasItem(DEFAULT_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].otherUsage").value(hasItem(DEFAULT_OTHER_USAGE)));
    }
    
    @Test
    @Transactional
    public void getGeuRaccordement() throws Exception {
        // Initialize the database
        geuRaccordementRepository.saveAndFlush(geuRaccordement);

        // Get the geuRaccordement
        restGeuRaccordementMockMvc.perform(get("/api/geu-raccordements/{id}", geuRaccordement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuRaccordement.getId().intValue()))
            .andExpect(jsonPath("$.numAbonnement").value(DEFAULT_NUM_ABONNEMENT.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.proprietaireParacelle").value(DEFAULT_PROPRIETAIRE_PARACELLE))
            .andExpect(jsonPath("$.entreprise").value(DEFAULT_ENTREPRISE))
            .andExpect(jsonPath("$.otherUsage").value(DEFAULT_OTHER_USAGE));
    }
    @Test
    @Transactional
    public void getNonExistingGeuRaccordement() throws Exception {
        // Get the geuRaccordement
        restGeuRaccordementMockMvc.perform(get("/api/geu-raccordements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuRaccordement() throws Exception {
        // Initialize the database
        geuRaccordementRepository.saveAndFlush(geuRaccordement);

        int databaseSizeBeforeUpdate = geuRaccordementRepository.findAll().size();

        // Update the geuRaccordement
        GeuRaccordement updatedGeuRaccordement = geuRaccordementRepository.findById(geuRaccordement.getId()).get();
        // Disconnect from session so that the updates on updatedGeuRaccordement are not directly saved in db
        em.detach(updatedGeuRaccordement);
        updatedGeuRaccordement
            .numAbonnement(UPDATED_NUM_ABONNEMENT)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .proprietaireParacelle(UPDATED_PROPRIETAIRE_PARACELLE)
            .entreprise(UPDATED_ENTREPRISE)
            .otherUsage(UPDATED_OTHER_USAGE);
        GeuRaccordementDTO geuRaccordementDTO = geuRaccordementMapper.toDto(updatedGeuRaccordement);

        restGeuRaccordementMockMvc.perform(put("/api/geu-raccordements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRaccordementDTO)))
            .andExpect(status().isOk());

        // Validate the GeuRaccordement in the database
        List<GeuRaccordement> geuRaccordementList = geuRaccordementRepository.findAll();
        assertThat(geuRaccordementList).hasSize(databaseSizeBeforeUpdate);
        GeuRaccordement testGeuRaccordement = geuRaccordementList.get(geuRaccordementList.size() - 1);
        assertThat(testGeuRaccordement.getNumAbonnement()).isEqualTo(UPDATED_NUM_ABONNEMENT);
        assertThat(testGeuRaccordement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testGeuRaccordement.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testGeuRaccordement.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testGeuRaccordement.getProprietaireParacelle()).isEqualTo(UPDATED_PROPRIETAIRE_PARACELLE);
        assertThat(testGeuRaccordement.getEntreprise()).isEqualTo(UPDATED_ENTREPRISE);
        assertThat(testGeuRaccordement.getOtherUsage()).isEqualTo(UPDATED_OTHER_USAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuRaccordement() throws Exception {
        int databaseSizeBeforeUpdate = geuRaccordementRepository.findAll().size();

        // Create the GeuRaccordement
        GeuRaccordementDTO geuRaccordementDTO = geuRaccordementMapper.toDto(geuRaccordement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuRaccordementMockMvc.perform(put("/api/geu-raccordements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRaccordementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRaccordement in the database
        List<GeuRaccordement> geuRaccordementList = geuRaccordementRepository.findAll();
        assertThat(geuRaccordementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuRaccordement() throws Exception {
        // Initialize the database
        geuRaccordementRepository.saveAndFlush(geuRaccordement);

        int databaseSizeBeforeDelete = geuRaccordementRepository.findAll().size();

        // Delete the geuRaccordement
        restGeuRaccordementMockMvc.perform(delete("/api/geu-raccordements/{id}", geuRaccordement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuRaccordement> geuRaccordementList = geuRaccordementRepository.findAll();
        assertThat(geuRaccordementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
