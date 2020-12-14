package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Laboratoire;
import bf.onea.repository.LaboratoireRepository;
import bf.onea.service.LaboratoireService;
import bf.onea.service.dto.LaboratoireDTO;
import bf.onea.service.mapper.LaboratoireMapper;

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
 * Integration tests for the {@link LaboratoireResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LaboratoireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @Autowired
    private LaboratoireMapper laboratoireMapper;

    @Autowired
    private LaboratoireService laboratoireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoireMockMvc;

    private Laboratoire laboratoire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Laboratoire createEntity(EntityManager em) {
        Laboratoire laboratoire = new Laboratoire()
            .libelle(DEFAULT_LIBELLE);
        return laboratoire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Laboratoire createUpdatedEntity(EntityManager em) {
        Laboratoire laboratoire = new Laboratoire()
            .libelle(UPDATED_LIBELLE);
        return laboratoire;
    }

    @BeforeEach
    public void initTest() {
        laboratoire = createEntity(em);
    }

    @Test
    @Transactional
    public void createLaboratoire() throws Exception {
        int databaseSizeBeforeCreate = laboratoireRepository.findAll().size();
        // Create the Laboratoire
        LaboratoireDTO laboratoireDTO = laboratoireMapper.toDto(laboratoire);
        restLaboratoireMockMvc.perform(post("/api/laboratoires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoireDTO)))
            .andExpect(status().isCreated());

        // Validate the Laboratoire in the database
        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeCreate + 1);
        Laboratoire testLaboratoire = laboratoireList.get(laboratoireList.size() - 1);
        assertThat(testLaboratoire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createLaboratoireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = laboratoireRepository.findAll().size();

        // Create the Laboratoire with an existing ID
        laboratoire.setId(1L);
        LaboratoireDTO laboratoireDTO = laboratoireMapper.toDto(laboratoire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoireMockMvc.perform(post("/api/laboratoires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoireRepository.findAll().size();
        // set the field null
        laboratoire.setLibelle(null);

        // Create the Laboratoire, which fails.
        LaboratoireDTO laboratoireDTO = laboratoireMapper.toDto(laboratoire);


        restLaboratoireMockMvc.perform(post("/api/laboratoires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoireDTO)))
            .andExpect(status().isBadRequest());

        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLaboratoires() throws Exception {
        // Initialize the database
        laboratoireRepository.saveAndFlush(laboratoire);

        // Get all the laboratoireList
        restLaboratoireMockMvc.perform(get("/api/laboratoires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getLaboratoire() throws Exception {
        // Initialize the database
        laboratoireRepository.saveAndFlush(laboratoire);

        // Get the laboratoire
        restLaboratoireMockMvc.perform(get("/api/laboratoires/{id}", laboratoire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingLaboratoire() throws Exception {
        // Get the laboratoire
        restLaboratoireMockMvc.perform(get("/api/laboratoires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLaboratoire() throws Exception {
        // Initialize the database
        laboratoireRepository.saveAndFlush(laboratoire);

        int databaseSizeBeforeUpdate = laboratoireRepository.findAll().size();

        // Update the laboratoire
        Laboratoire updatedLaboratoire = laboratoireRepository.findById(laboratoire.getId()).get();
        // Disconnect from session so that the updates on updatedLaboratoire are not directly saved in db
        em.detach(updatedLaboratoire);
        updatedLaboratoire
            .libelle(UPDATED_LIBELLE);
        LaboratoireDTO laboratoireDTO = laboratoireMapper.toDto(updatedLaboratoire);

        restLaboratoireMockMvc.perform(put("/api/laboratoires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoireDTO)))
            .andExpect(status().isOk());

        // Validate the Laboratoire in the database
        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeUpdate);
        Laboratoire testLaboratoire = laboratoireList.get(laboratoireList.size() - 1);
        assertThat(testLaboratoire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingLaboratoire() throws Exception {
        int databaseSizeBeforeUpdate = laboratoireRepository.findAll().size();

        // Create the Laboratoire
        LaboratoireDTO laboratoireDTO = laboratoireMapper.toDto(laboratoire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc.perform(put("/api/laboratoires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(laboratoireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLaboratoire() throws Exception {
        // Initialize the database
        laboratoireRepository.saveAndFlush(laboratoire);

        int databaseSizeBeforeDelete = laboratoireRepository.findAll().size();

        // Delete the laboratoire
        restLaboratoireMockMvc.perform(delete("/api/laboratoires/{id}", laboratoire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();
        assertThat(laboratoireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
