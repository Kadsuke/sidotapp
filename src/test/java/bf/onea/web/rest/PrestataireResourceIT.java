package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Prestataire;
import bf.onea.repository.PrestataireRepository;
import bf.onea.service.PrestataireService;
import bf.onea.service.dto.PrestataireDTO;
import bf.onea.service.mapper.PrestataireMapper;

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
 * Integration tests for the {@link PrestataireResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrestataireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private PrestataireMapper prestataireMapper;

    @Autowired
    private PrestataireService prestataireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrestataireMockMvc;

    private Prestataire prestataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prestataire createEntity(EntityManager em) {
        Prestataire prestataire = new Prestataire()
            .libelle(DEFAULT_LIBELLE)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return prestataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prestataire createUpdatedEntity(EntityManager em) {
        Prestataire prestataire = new Prestataire()
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return prestataire;
    }

    @BeforeEach
    public void initTest() {
        prestataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrestataire() throws Exception {
        int databaseSizeBeforeCreate = prestataireRepository.findAll().size();
        // Create the Prestataire
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);
        restPrestataireMockMvc.perform(post("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isCreated());

        // Validate the Prestataire in the database
        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeCreate + 1);
        Prestataire testPrestataire = prestataireList.get(prestataireList.size() - 1);
        assertThat(testPrestataire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPrestataire.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testPrestataire.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createPrestataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prestataireRepository.findAll().size();

        // Create the Prestataire with an existing ID
        prestataire.setId(1L);
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrestataireMockMvc.perform(post("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prestataire in the database
        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = prestataireRepository.findAll().size();
        // set the field null
        prestataire.setLibelle(null);

        // Create the Prestataire, which fails.
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);


        restPrestataireMockMvc.perform(post("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isBadRequest());

        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = prestataireRepository.findAll().size();
        // set the field null
        prestataire.setResponsable(null);

        // Create the Prestataire, which fails.
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);


        restPrestataireMockMvc.perform(post("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isBadRequest());

        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = prestataireRepository.findAll().size();
        // set the field null
        prestataire.setContact(null);

        // Create the Prestataire, which fails.
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);


        restPrestataireMockMvc.perform(post("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isBadRequest());

        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrestataires() throws Exception {
        // Initialize the database
        prestataireRepository.saveAndFlush(prestataire);

        // Get all the prestataireList
        restPrestataireMockMvc.perform(get("/api/prestataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prestataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getPrestataire() throws Exception {
        // Initialize the database
        prestataireRepository.saveAndFlush(prestataire);

        // Get the prestataire
        restPrestataireMockMvc.perform(get("/api/prestataires/{id}", prestataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prestataire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingPrestataire() throws Exception {
        // Get the prestataire
        restPrestataireMockMvc.perform(get("/api/prestataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrestataire() throws Exception {
        // Initialize the database
        prestataireRepository.saveAndFlush(prestataire);

        int databaseSizeBeforeUpdate = prestataireRepository.findAll().size();

        // Update the prestataire
        Prestataire updatedPrestataire = prestataireRepository.findById(prestataire.getId()).get();
        // Disconnect from session so that the updates on updatedPrestataire are not directly saved in db
        em.detach(updatedPrestataire);
        updatedPrestataire
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(updatedPrestataire);

        restPrestataireMockMvc.perform(put("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isOk());

        // Validate the Prestataire in the database
        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeUpdate);
        Prestataire testPrestataire = prestataireList.get(prestataireList.size() - 1);
        assertThat(testPrestataire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPrestataire.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testPrestataire.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingPrestataire() throws Exception {
        int databaseSizeBeforeUpdate = prestataireRepository.findAll().size();

        // Create the Prestataire
        PrestataireDTO prestataireDTO = prestataireMapper.toDto(prestataire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrestataireMockMvc.perform(put("/api/prestataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prestataireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prestataire in the database
        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrestataire() throws Exception {
        // Initialize the database
        prestataireRepository.saveAndFlush(prestataire);

        int databaseSizeBeforeDelete = prestataireRepository.findAll().size();

        // Delete the prestataire
        restPrestataireMockMvc.perform(delete("/api/prestataires/{id}", prestataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prestataire> prestataireList = prestataireRepository.findAll();
        assertThat(prestataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
