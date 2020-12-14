package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuPrevisionSTBV;
import bf.onea.repository.GeuPrevisionSTBVRepository;
import bf.onea.service.GeuPrevisionSTBVService;
import bf.onea.service.dto.GeuPrevisionSTBVDTO;
import bf.onea.service.mapper.GeuPrevisionSTBVMapper;

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
 * Integration tests for the {@link GeuPrevisionSTBVResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuPrevisionSTBVResourceIT {

    private static final String DEFAULT_NB_CAMIONS = "AAAAAAAAAA";
    private static final String UPDATED_NB_CAMIONS = "BBBBBBBBBB";

    private static final String DEFAULT_VOLUME = "AAAAAAAAAA";
    private static final String UPDATED_VOLUME = "BBBBBBBBBB";

    private static final String DEFAULT_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGIE = "BBBBBBBBBB";

    @Autowired
    private GeuPrevisionSTBVRepository geuPrevisionSTBVRepository;

    @Autowired
    private GeuPrevisionSTBVMapper geuPrevisionSTBVMapper;

    @Autowired
    private GeuPrevisionSTBVService geuPrevisionSTBVService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuPrevisionSTBVMockMvc;

    private GeuPrevisionSTBV geuPrevisionSTBV;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPrevisionSTBV createEntity(EntityManager em) {
        GeuPrevisionSTBV geuPrevisionSTBV = new GeuPrevisionSTBV()
            .nbCamions(DEFAULT_NB_CAMIONS)
            .volume(DEFAULT_VOLUME)
            .energie(DEFAULT_ENERGIE);
        return geuPrevisionSTBV;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuPrevisionSTBV createUpdatedEntity(EntityManager em) {
        GeuPrevisionSTBV geuPrevisionSTBV = new GeuPrevisionSTBV()
            .nbCamions(UPDATED_NB_CAMIONS)
            .volume(UPDATED_VOLUME)
            .energie(UPDATED_ENERGIE);
        return geuPrevisionSTBV;
    }

    @BeforeEach
    public void initTest() {
        geuPrevisionSTBV = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuPrevisionSTBV() throws Exception {
        int databaseSizeBeforeCreate = geuPrevisionSTBVRepository.findAll().size();
        // Create the GeuPrevisionSTBV
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);
        restGeuPrevisionSTBVMockMvc.perform(post("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuPrevisionSTBV in the database
        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeCreate + 1);
        GeuPrevisionSTBV testGeuPrevisionSTBV = geuPrevisionSTBVList.get(geuPrevisionSTBVList.size() - 1);
        assertThat(testGeuPrevisionSTBV.getNbCamions()).isEqualTo(DEFAULT_NB_CAMIONS);
        assertThat(testGeuPrevisionSTBV.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testGeuPrevisionSTBV.getEnergie()).isEqualTo(DEFAULT_ENERGIE);
    }

    @Test
    @Transactional
    public void createGeuPrevisionSTBVWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuPrevisionSTBVRepository.findAll().size();

        // Create the GeuPrevisionSTBV with an existing ID
        geuPrevisionSTBV.setId(1L);
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuPrevisionSTBVMockMvc.perform(post("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPrevisionSTBV in the database
        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNbCamionsIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuPrevisionSTBVRepository.findAll().size();
        // set the field null
        geuPrevisionSTBV.setNbCamions(null);

        // Create the GeuPrevisionSTBV, which fails.
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);


        restGeuPrevisionSTBVMockMvc.perform(post("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuPrevisionSTBVRepository.findAll().size();
        // set the field null
        geuPrevisionSTBV.setVolume(null);

        // Create the GeuPrevisionSTBV, which fails.
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);


        restGeuPrevisionSTBVMockMvc.perform(post("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnergieIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuPrevisionSTBVRepository.findAll().size();
        // set the field null
        geuPrevisionSTBV.setEnergie(null);

        // Create the GeuPrevisionSTBV, which fails.
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);


        restGeuPrevisionSTBVMockMvc.perform(post("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeuPrevisionSTBVS() throws Exception {
        // Initialize the database
        geuPrevisionSTBVRepository.saveAndFlush(geuPrevisionSTBV);

        // Get all the geuPrevisionSTBVList
        restGeuPrevisionSTBVMockMvc.perform(get("/api/geu-prevision-stbvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuPrevisionSTBV.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbCamions").value(hasItem(DEFAULT_NB_CAMIONS)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].energie").value(hasItem(DEFAULT_ENERGIE)));
    }
    
    @Test
    @Transactional
    public void getGeuPrevisionSTBV() throws Exception {
        // Initialize the database
        geuPrevisionSTBVRepository.saveAndFlush(geuPrevisionSTBV);

        // Get the geuPrevisionSTBV
        restGeuPrevisionSTBVMockMvc.perform(get("/api/geu-prevision-stbvs/{id}", geuPrevisionSTBV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuPrevisionSTBV.getId().intValue()))
            .andExpect(jsonPath("$.nbCamions").value(DEFAULT_NB_CAMIONS))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.energie").value(DEFAULT_ENERGIE));
    }
    @Test
    @Transactional
    public void getNonExistingGeuPrevisionSTBV() throws Exception {
        // Get the geuPrevisionSTBV
        restGeuPrevisionSTBVMockMvc.perform(get("/api/geu-prevision-stbvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuPrevisionSTBV() throws Exception {
        // Initialize the database
        geuPrevisionSTBVRepository.saveAndFlush(geuPrevisionSTBV);

        int databaseSizeBeforeUpdate = geuPrevisionSTBVRepository.findAll().size();

        // Update the geuPrevisionSTBV
        GeuPrevisionSTBV updatedGeuPrevisionSTBV = geuPrevisionSTBVRepository.findById(geuPrevisionSTBV.getId()).get();
        // Disconnect from session so that the updates on updatedGeuPrevisionSTBV are not directly saved in db
        em.detach(updatedGeuPrevisionSTBV);
        updatedGeuPrevisionSTBV
            .nbCamions(UPDATED_NB_CAMIONS)
            .volume(UPDATED_VOLUME)
            .energie(UPDATED_ENERGIE);
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(updatedGeuPrevisionSTBV);

        restGeuPrevisionSTBVMockMvc.perform(put("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isOk());

        // Validate the GeuPrevisionSTBV in the database
        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeUpdate);
        GeuPrevisionSTBV testGeuPrevisionSTBV = geuPrevisionSTBVList.get(geuPrevisionSTBVList.size() - 1);
        assertThat(testGeuPrevisionSTBV.getNbCamions()).isEqualTo(UPDATED_NB_CAMIONS);
        assertThat(testGeuPrevisionSTBV.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testGeuPrevisionSTBV.getEnergie()).isEqualTo(UPDATED_ENERGIE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuPrevisionSTBV() throws Exception {
        int databaseSizeBeforeUpdate = geuPrevisionSTBVRepository.findAll().size();

        // Create the GeuPrevisionSTBV
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO = geuPrevisionSTBVMapper.toDto(geuPrevisionSTBV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuPrevisionSTBVMockMvc.perform(put("/api/geu-prevision-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuPrevisionSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuPrevisionSTBV in the database
        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuPrevisionSTBV() throws Exception {
        // Initialize the database
        geuPrevisionSTBVRepository.saveAndFlush(geuPrevisionSTBV);

        int databaseSizeBeforeDelete = geuPrevisionSTBVRepository.findAll().size();

        // Delete the geuPrevisionSTBV
        restGeuPrevisionSTBVMockMvc.perform(delete("/api/geu-prevision-stbvs/{id}", geuPrevisionSTBV.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuPrevisionSTBV> geuPrevisionSTBVList = geuPrevisionSTBVRepository.findAll();
        assertThat(geuPrevisionSTBVList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
