package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuRealisationSTBV;
import bf.onea.repository.GeuRealisationSTBVRepository;
import bf.onea.service.GeuRealisationSTBVService;
import bf.onea.service.dto.GeuRealisationSTBVDTO;
import bf.onea.service.mapper.GeuRealisationSTBVMapper;

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
 * Integration tests for the {@link GeuRealisationSTBVResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuRealisationSTBVResourceIT {

    private static final String DEFAULT_NB_CAMIONS = "AAAAAAAAAA";
    private static final String UPDATED_NB_CAMIONS = "BBBBBBBBBB";

    private static final String DEFAULT_VOLUME = "AAAAAAAAAA";
    private static final String UPDATED_VOLUME = "BBBBBBBBBB";

    private static final String DEFAULT_ENERGIE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGIE = "BBBBBBBBBB";

    @Autowired
    private GeuRealisationSTBVRepository geuRealisationSTBVRepository;

    @Autowired
    private GeuRealisationSTBVMapper geuRealisationSTBVMapper;

    @Autowired
    private GeuRealisationSTBVService geuRealisationSTBVService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuRealisationSTBVMockMvc;

    private GeuRealisationSTBV geuRealisationSTBV;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRealisationSTBV createEntity(EntityManager em) {
        GeuRealisationSTBV geuRealisationSTBV = new GeuRealisationSTBV()
            .nbCamions(DEFAULT_NB_CAMIONS)
            .volume(DEFAULT_VOLUME)
            .energie(DEFAULT_ENERGIE);
        return geuRealisationSTBV;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuRealisationSTBV createUpdatedEntity(EntityManager em) {
        GeuRealisationSTBV geuRealisationSTBV = new GeuRealisationSTBV()
            .nbCamions(UPDATED_NB_CAMIONS)
            .volume(UPDATED_VOLUME)
            .energie(UPDATED_ENERGIE);
        return geuRealisationSTBV;
    }

    @BeforeEach
    public void initTest() {
        geuRealisationSTBV = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuRealisationSTBV() throws Exception {
        int databaseSizeBeforeCreate = geuRealisationSTBVRepository.findAll().size();
        // Create the GeuRealisationSTBV
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);
        restGeuRealisationSTBVMockMvc.perform(post("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuRealisationSTBV in the database
        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeCreate + 1);
        GeuRealisationSTBV testGeuRealisationSTBV = geuRealisationSTBVList.get(geuRealisationSTBVList.size() - 1);
        assertThat(testGeuRealisationSTBV.getNbCamions()).isEqualTo(DEFAULT_NB_CAMIONS);
        assertThat(testGeuRealisationSTBV.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testGeuRealisationSTBV.getEnergie()).isEqualTo(DEFAULT_ENERGIE);
    }

    @Test
    @Transactional
    public void createGeuRealisationSTBVWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuRealisationSTBVRepository.findAll().size();

        // Create the GeuRealisationSTBV with an existing ID
        geuRealisationSTBV.setId(1L);
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuRealisationSTBVMockMvc.perform(post("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRealisationSTBV in the database
        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNbCamionsIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuRealisationSTBVRepository.findAll().size();
        // set the field null
        geuRealisationSTBV.setNbCamions(null);

        // Create the GeuRealisationSTBV, which fails.
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);


        restGeuRealisationSTBVMockMvc.perform(post("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuRealisationSTBVRepository.findAll().size();
        // set the field null
        geuRealisationSTBV.setVolume(null);

        // Create the GeuRealisationSTBV, which fails.
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);


        restGeuRealisationSTBVMockMvc.perform(post("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnergieIsRequired() throws Exception {
        int databaseSizeBeforeTest = geuRealisationSTBVRepository.findAll().size();
        // set the field null
        geuRealisationSTBV.setEnergie(null);

        // Create the GeuRealisationSTBV, which fails.
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);


        restGeuRealisationSTBVMockMvc.perform(post("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isBadRequest());

        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeuRealisationSTBVS() throws Exception {
        // Initialize the database
        geuRealisationSTBVRepository.saveAndFlush(geuRealisationSTBV);

        // Get all the geuRealisationSTBVList
        restGeuRealisationSTBVMockMvc.perform(get("/api/geu-realisation-stbvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuRealisationSTBV.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbCamions").value(hasItem(DEFAULT_NB_CAMIONS)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].energie").value(hasItem(DEFAULT_ENERGIE)));
    }
    
    @Test
    @Transactional
    public void getGeuRealisationSTBV() throws Exception {
        // Initialize the database
        geuRealisationSTBVRepository.saveAndFlush(geuRealisationSTBV);

        // Get the geuRealisationSTBV
        restGeuRealisationSTBVMockMvc.perform(get("/api/geu-realisation-stbvs/{id}", geuRealisationSTBV.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuRealisationSTBV.getId().intValue()))
            .andExpect(jsonPath("$.nbCamions").value(DEFAULT_NB_CAMIONS))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME))
            .andExpect(jsonPath("$.energie").value(DEFAULT_ENERGIE));
    }
    @Test
    @Transactional
    public void getNonExistingGeuRealisationSTBV() throws Exception {
        // Get the geuRealisationSTBV
        restGeuRealisationSTBVMockMvc.perform(get("/api/geu-realisation-stbvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuRealisationSTBV() throws Exception {
        // Initialize the database
        geuRealisationSTBVRepository.saveAndFlush(geuRealisationSTBV);

        int databaseSizeBeforeUpdate = geuRealisationSTBVRepository.findAll().size();

        // Update the geuRealisationSTBV
        GeuRealisationSTBV updatedGeuRealisationSTBV = geuRealisationSTBVRepository.findById(geuRealisationSTBV.getId()).get();
        // Disconnect from session so that the updates on updatedGeuRealisationSTBV are not directly saved in db
        em.detach(updatedGeuRealisationSTBV);
        updatedGeuRealisationSTBV
            .nbCamions(UPDATED_NB_CAMIONS)
            .volume(UPDATED_VOLUME)
            .energie(UPDATED_ENERGIE);
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(updatedGeuRealisationSTBV);

        restGeuRealisationSTBVMockMvc.perform(put("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isOk());

        // Validate the GeuRealisationSTBV in the database
        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeUpdate);
        GeuRealisationSTBV testGeuRealisationSTBV = geuRealisationSTBVList.get(geuRealisationSTBVList.size() - 1);
        assertThat(testGeuRealisationSTBV.getNbCamions()).isEqualTo(UPDATED_NB_CAMIONS);
        assertThat(testGeuRealisationSTBV.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testGeuRealisationSTBV.getEnergie()).isEqualTo(UPDATED_ENERGIE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuRealisationSTBV() throws Exception {
        int databaseSizeBeforeUpdate = geuRealisationSTBVRepository.findAll().size();

        // Create the GeuRealisationSTBV
        GeuRealisationSTBVDTO geuRealisationSTBVDTO = geuRealisationSTBVMapper.toDto(geuRealisationSTBV);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuRealisationSTBVMockMvc.perform(put("/api/geu-realisation-stbvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuRealisationSTBVDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuRealisationSTBV in the database
        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuRealisationSTBV() throws Exception {
        // Initialize the database
        geuRealisationSTBVRepository.saveAndFlush(geuRealisationSTBV);

        int databaseSizeBeforeDelete = geuRealisationSTBVRepository.findAll().size();

        // Delete the geuRealisationSTBV
        restGeuRealisationSTBVMockMvc.perform(delete("/api/geu-realisation-stbvs/{id}", geuRealisationSTBV.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuRealisationSTBV> geuRealisationSTBVList = geuRealisationSTBVRepository.findAll();
        assertThat(geuRealisationSTBVList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
