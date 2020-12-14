package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoRegion;
import bf.onea.repository.GeoRegionRepository;
import bf.onea.service.GeoRegionService;
import bf.onea.service.dto.GeoRegionDTO;
import bf.onea.service.mapper.GeoRegionMapper;

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
 * Integration tests for the {@link GeoRegionResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoRegionResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoRegionRepository geoRegionRepository;

    @Autowired
    private GeoRegionMapper geoRegionMapper;

    @Autowired
    private GeoRegionService geoRegionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoRegionMockMvc;

    private GeoRegion geoRegion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoRegion createEntity(EntityManager em) {
        GeoRegion geoRegion = new GeoRegion()
            .libelle(DEFAULT_LIBELLE);
        return geoRegion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoRegion createUpdatedEntity(EntityManager em) {
        GeoRegion geoRegion = new GeoRegion()
            .libelle(UPDATED_LIBELLE);
        return geoRegion;
    }

    @BeforeEach
    public void initTest() {
        geoRegion = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoRegion() throws Exception {
        int databaseSizeBeforeCreate = geoRegionRepository.findAll().size();
        // Create the GeoRegion
        GeoRegionDTO geoRegionDTO = geoRegionMapper.toDto(geoRegion);
        restGeoRegionMockMvc.perform(post("/api/geo-regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoRegionDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoRegion in the database
        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeCreate + 1);
        GeoRegion testGeoRegion = geoRegionList.get(geoRegionList.size() - 1);
        assertThat(testGeoRegion.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoRegionRepository.findAll().size();

        // Create the GeoRegion with an existing ID
        geoRegion.setId(1L);
        GeoRegionDTO geoRegionDTO = geoRegionMapper.toDto(geoRegion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoRegionMockMvc.perform(post("/api/geo-regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoRegionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoRegion in the database
        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = geoRegionRepository.findAll().size();
        // set the field null
        geoRegion.setLibelle(null);

        // Create the GeoRegion, which fails.
        GeoRegionDTO geoRegionDTO = geoRegionMapper.toDto(geoRegion);


        restGeoRegionMockMvc.perform(post("/api/geo-regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoRegionDTO)))
            .andExpect(status().isBadRequest());

        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeoRegions() throws Exception {
        // Initialize the database
        geoRegionRepository.saveAndFlush(geoRegion);

        // Get all the geoRegionList
        restGeoRegionMockMvc.perform(get("/api/geo-regions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoRegion.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoRegion() throws Exception {
        // Initialize the database
        geoRegionRepository.saveAndFlush(geoRegion);

        // Get the geoRegion
        restGeoRegionMockMvc.perform(get("/api/geo-regions/{id}", geoRegion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoRegion.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoRegion() throws Exception {
        // Get the geoRegion
        restGeoRegionMockMvc.perform(get("/api/geo-regions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoRegion() throws Exception {
        // Initialize the database
        geoRegionRepository.saveAndFlush(geoRegion);

        int databaseSizeBeforeUpdate = geoRegionRepository.findAll().size();

        // Update the geoRegion
        GeoRegion updatedGeoRegion = geoRegionRepository.findById(geoRegion.getId()).get();
        // Disconnect from session so that the updates on updatedGeoRegion are not directly saved in db
        em.detach(updatedGeoRegion);
        updatedGeoRegion
            .libelle(UPDATED_LIBELLE);
        GeoRegionDTO geoRegionDTO = geoRegionMapper.toDto(updatedGeoRegion);

        restGeoRegionMockMvc.perform(put("/api/geo-regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoRegionDTO)))
            .andExpect(status().isOk());

        // Validate the GeoRegion in the database
        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeUpdate);
        GeoRegion testGeoRegion = geoRegionList.get(geoRegionList.size() - 1);
        assertThat(testGeoRegion.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoRegion() throws Exception {
        int databaseSizeBeforeUpdate = geoRegionRepository.findAll().size();

        // Create the GeoRegion
        GeoRegionDTO geoRegionDTO = geoRegionMapper.toDto(geoRegion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoRegionMockMvc.perform(put("/api/geo-regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoRegionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoRegion in the database
        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoRegion() throws Exception {
        // Initialize the database
        geoRegionRepository.saveAndFlush(geoRegion);

        int databaseSizeBeforeDelete = geoRegionRepository.findAll().size();

        // Delete the geoRegion
        restGeoRegionMockMvc.perform(delete("/api/geo-regions/{id}", geoRegion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoRegion> geoRegionList = geoRegionRepository.findAll();
        assertThat(geoRegionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
