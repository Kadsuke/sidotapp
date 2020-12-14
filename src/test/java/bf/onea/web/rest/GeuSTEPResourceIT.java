package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuSTEP;
import bf.onea.repository.GeuSTEPRepository;
import bf.onea.service.GeuSTEPService;
import bf.onea.service.dto.GeuSTEPDTO;
import bf.onea.service.mapper.GeuSTEPMapper;

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
 * Integration tests for the {@link GeuSTEPResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuSTEPResourceIT {

    private static final String DEFAULT_LIBEL_STEP = "AAAAAAAAAA";
    private static final String UPDATED_LIBEL_STEP = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private GeuSTEPRepository geuSTEPRepository;

    @Autowired
    private GeuSTEPMapper geuSTEPMapper;

    @Autowired
    private GeuSTEPService geuSTEPService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuSTEPMockMvc;

    private GeuSTEP geuSTEP;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuSTEP createEntity(EntityManager em) {
        GeuSTEP geuSTEP = new GeuSTEP()
            .libelSTEP(DEFAULT_LIBEL_STEP)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return geuSTEP;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuSTEP createUpdatedEntity(EntityManager em) {
        GeuSTEP geuSTEP = new GeuSTEP()
            .libelSTEP(UPDATED_LIBEL_STEP)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return geuSTEP;
    }

    @BeforeEach
    public void initTest() {
        geuSTEP = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuSTEP() throws Exception {
        int databaseSizeBeforeCreate = geuSTEPRepository.findAll().size();
        // Create the GeuSTEP
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);
        restGeuSTEPMockMvc.perform(post("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuSTEP in the database
        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeCreate + 1);
        GeuSTEP testGeuSTEP = geuSTEPList.get(geuSTEPList.size() - 1);
        assertThat(testGeuSTEP.getLibelSTEP()).isEqualTo(DEFAULT_LIBEL_STEP);
        assertThat(testGeuSTEP.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testGeuSTEP.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createGeuSTEPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuSTEPRepository.findAll().size();

        // Create the GeuSTEP with an existing ID
        geuSTEP.setId(1L);
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuSTEPMockMvc.perform(post("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuSTEP in the database
        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelSTEPIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTEPRepository.findAll().size();
        // set the field null
        geuSTEP.setLibelSTEP(null);

        // Create the GeuSTEP, which fails.
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);


        restGeuSTEPMockMvc.perform(post("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTEPRepository.findAll().size();
        // set the field null
        geuSTEP.setResponsable(null);

        // Create the GeuSTEP, which fails.
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);


        restGeuSTEPMockMvc.perform(post("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTEPRepository.findAll().size();
        // set the field null
        geuSTEP.setContact(null);

        // Create the GeuSTEP, which fails.
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);


        restGeuSTEPMockMvc.perform(post("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeuSTEPS() throws Exception {
        // Initialize the database
        geuSTEPRepository.saveAndFlush(geuSTEP);

        // Get all the geuSTEPList
        restGeuSTEPMockMvc.perform(get("/api/geu-steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuSTEP.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelSTEP").value(hasItem(DEFAULT_LIBEL_STEP)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getGeuSTEP() throws Exception {
        // Initialize the database
        geuSTEPRepository.saveAndFlush(geuSTEP);

        // Get the geuSTEP
        restGeuSTEPMockMvc.perform(get("/api/geu-steps/{id}", geuSTEP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuSTEP.getId().intValue()))
            .andExpect(jsonPath("$.libelSTEP").value(DEFAULT_LIBEL_STEP))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingGeuSTEP() throws Exception {
        // Get the geuSTEP
        restGeuSTEPMockMvc.perform(get("/api/geu-steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuSTEP() throws Exception {
        // Initialize the database
        geuSTEPRepository.saveAndFlush(geuSTEP);

        int databaseSizeBeforeUpdate = geuSTEPRepository.findAll().size();

        // Update the geuSTEP
        GeuSTEP updatedGeuSTEP = geuSTEPRepository.findById(geuSTEP.getId()).get();
        // Disconnect from session so that the updates on updatedGeuSTEP are not directly saved in db
        em.detach(updatedGeuSTEP);
        updatedGeuSTEP
            .libelSTEP(UPDATED_LIBEL_STEP)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(updatedGeuSTEP);

        restGeuSTEPMockMvc.perform(put("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isOk());

        // Validate the GeuSTEP in the database
        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeUpdate);
        GeuSTEP testGeuSTEP = geuSTEPList.get(geuSTEPList.size() - 1);
        assertThat(testGeuSTEP.getLibelSTEP()).isEqualTo(UPDATED_LIBEL_STEP);
        assertThat(testGeuSTEP.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testGeuSTEP.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuSTEP() throws Exception {
        int databaseSizeBeforeUpdate = geuSTEPRepository.findAll().size();

        // Create the GeuSTEP
        GeuSTEPDTO geuSTEPDTO = geuSTEPMapper.toDto(geuSTEP);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuSTEPMockMvc.perform(put("/api/geu-steps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTEPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuSTEP in the database
        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuSTEP() throws Exception {
        // Initialize the database
        geuSTEPRepository.saveAndFlush(geuSTEP);

        int databaseSizeBeforeDelete = geuSTEPRepository.findAll().size();

        // Delete the geuSTEP
        restGeuSTEPMockMvc.perform(delete("/api/geu-steps/{id}", geuSTEP.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuSTEP> geuSTEPList = geuSTEPRepository.findAll();
        assertThat(geuSTEPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
