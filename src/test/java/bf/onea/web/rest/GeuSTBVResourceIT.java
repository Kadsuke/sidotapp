package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuSTBV;
import bf.onea.repository.GeuSTBVRepository;
import bf.onea.service.GeuSTBVService;
import bf.onea.service.dto.GeuSTBVDTO;
import bf.onea.service.mapper.GeuSTBVMapper;

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
 * Integration tests for the {@link GeuSTBVResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuSTBVResourceIT {

    private static final String DEFAULT_LIBEL_STBV = "AAAAAAAAAA";
    private static final String UPDATED_LIBEL_STBV = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private GeuSTBVRepository geuSTBVRepository;

    @Autowired
    private GeuSTBVMapper geuSTBVMapper;

    @Autowired
    private GeuSTBVService geuSTBVService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuSTBVMockMvc;

    private GeuSTBV geuSTBV;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuSTBV createEntity(EntityManager em) {
        GeuSTBV geuSTBV = new GeuSTBV()
            .libelStbv(DEFAULT_LIBEL_STBV)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return geuSTBV;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuSTBV createUpdatedEntity(EntityManager em) {
        GeuSTBV geuSTBV = new GeuSTBV()
            .libelStbv(UPDATED_LIBEL_STBV)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return geuSTBV;
    }

    @BeforeEach
    public void initTest() {
        geuSTBV = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuSTBV() throws Exception {
        int databaseSizeBeforeCreate = geuSTBVRepository.findAll().size();
        // Create the GeuSTBV
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);
        restGeuSTBVMockMvc.perform(post("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuSTBV in the database
        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeCreate + 1);
        GeuSTBV testGeuSTBV = geuSTBVList.get(geuSTBVList.size() - 1);
        assertThat(testGeuSTBV.getLibelStbv()).isEqualTo(DEFAULT_LIBEL_STBV);
        assertThat(testGeuSTBV.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testGeuSTBV.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createGeuSTBVWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuSTBVRepository.findAll().size();

        // Create the GeuSTBV with an existing ID
        geuSTBV.setId(1L);
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuSTBVMockMvc.perform(post("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuSTBV in the database
        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelStbvIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTBVRepository.findAll().size();
        // set the field null
        geuSTBV.setLibelStbv(null);

        // Create the GeuSTBV, which fails.
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);


        restGeuSTBVMockMvc.perform(post("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTBVRepository.findAll().size();
        // set the field null
        geuSTBV.setResponsable(null);

        // Create the GeuSTBV, which fails.
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);


        restGeuSTBVMockMvc.perform(post("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuSTBVRepository.findAll().size();
        // set the field null
        geuSTBV.setContact(null);

        // Create the GeuSTBV, which fails.
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);


        restGeuSTBVMockMvc.perform(post("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeuSTBVS() throws Exception {
        // Initialize the database
        geuSTBVRepository.saveAndFlush(geuSTBV);

        // Get all the geuSTBVList
        restGeuSTBVMockMvc.perform(get("/api/geu-stbvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuSTBV.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelStbv").value(hasItem(DEFAULT_LIBEL_STBV)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getGeuSTBV() throws Exception {
        // Initialize the database
        geuSTBVRepository.saveAndFlush(geuSTBV);

        // Get the geuSTBV
        restGeuSTBVMockMvc.perform(get("/api/geu-stbvs/{id}", geuSTBV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuSTBV.getId().intValue()))
            .andExpect(jsonPath("$.libelStbv").value(DEFAULT_LIBEL_STBV))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingGeuSTBV() throws Exception {
        // Get the geuSTBV
        restGeuSTBVMockMvc.perform(get("/api/geu-stbvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuSTBV() throws Exception {
        // Initialize the database
        geuSTBVRepository.saveAndFlush(geuSTBV);

        int databaseSizeBeforeUpdate = geuSTBVRepository.findAll().size();

        // Update the geuSTBV
        GeuSTBV updatedGeuSTBV = geuSTBVRepository.findById(geuSTBV.getId()).get();
        // Disconnect from session so that the updates on updatedGeuSTBV are not directly saved in db
        em.detach(updatedGeuSTBV);
        updatedGeuSTBV
            .libelStbv(UPDATED_LIBEL_STBV)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(updatedGeuSTBV);

        restGeuSTBVMockMvc.perform(put("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isOk());

        // Validate the GeuSTBV in the database
        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeUpdate);
        GeuSTBV testGeuSTBV = geuSTBVList.get(geuSTBVList.size() - 1);
        assertThat(testGeuSTBV.getLibelStbv()).isEqualTo(UPDATED_LIBEL_STBV);
        assertThat(testGeuSTBV.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testGeuSTBV.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuSTBV() throws Exception {
        int databaseSizeBeforeUpdate = geuSTBVRepository.findAll().size();

        // Create the GeuSTBV
        GeuSTBVDTO geuSTBVDTO = geuSTBVMapper.toDto(geuSTBV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuSTBVMockMvc.perform(put("/api/geu-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuSTBV in the database
        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuSTBV() throws Exception {
        // Initialize the database
        geuSTBVRepository.saveAndFlush(geuSTBV);

        int databaseSizeBeforeDelete = geuSTBVRepository.findAll().size();

        // Delete the geuSTBV
        restGeuSTBVMockMvc.perform(delete("/api/geu-stbvs/{id}", geuSTBV.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuSTBV> geuSTBVList = geuSTBVRepository.findAll();
        assertThat(geuSTBVList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
