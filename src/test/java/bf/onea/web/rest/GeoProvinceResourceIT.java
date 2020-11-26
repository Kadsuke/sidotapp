package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoProvince;
import bf.onea.repository.GeoProvinceRepository;
import bf.onea.service.GeoProvinceService;
import bf.onea.service.dto.GeoProvinceDTO;
import bf.onea.service.mapper.GeoProvinceMapper;

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
 * Integration tests for the {@link GeoProvinceResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoProvinceResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoProvinceRepository geoProvinceRepository;

    @Autowired
    private GeoProvinceMapper geoProvinceMapper;

    @Autowired
    private GeoProvinceService geoProvinceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoProvinceMockMvc;

    private GeoProvince geoProvince;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoProvince createEntity(EntityManager em) {
        GeoProvince geoProvince = new GeoProvince()
            .libelle(DEFAULT_LIBELLE);
        return geoProvince;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoProvince createUpdatedEntity(EntityManager em) {
        GeoProvince geoProvince = new GeoProvince()
            .libelle(UPDATED_LIBELLE);
        return geoProvince;
    }

    @BeforeEach
    public void initTest() {
        geoProvince = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoProvince() throws Exception {
        int databaseSizeBeforeCreate = geoProvinceRepository.findAll().size();
        // Create the GeoProvince
        GeoProvinceDTO geoProvinceDTO = geoProvinceMapper.toDto(geoProvince);
        restGeoProvinceMockMvc.perform(post("/api/geo-provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoProvinceDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoProvince in the database
        List<GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
        assertThat(geoProvinceList).hasSize(databaseSizeBeforeCreate + 1);
        GeoProvince testGeoProvince = geoProvinceList.get(geoProvinceList.size() - 1);
        assertThat(testGeoProvince.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoProvinceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoProvinceRepository.findAll().size();

        // Create the GeoProvince with an existing ID
        geoProvince.setId(1L);
        GeoProvinceDTO geoProvinceDTO = geoProvinceMapper.toDto(geoProvince);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoProvinceMockMvc.perform(post("/api/geo-provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoProvinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoProvince in the database
        List<GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
        assertThat(geoProvinceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoProvinces() throws Exception {
        // Initialize the database
        geoProvinceRepository.saveAndFlush(geoProvince);

        // Get all the geoProvinceList
        restGeoProvinceMockMvc.perform(get("/api/geo-provinces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoProvince.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoProvince() throws Exception {
        // Initialize the database
        geoProvinceRepository.saveAndFlush(geoProvince);

        // Get the geoProvince
        restGeoProvinceMockMvc.perform(get("/api/geo-provinces/{id}", geoProvince.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoProvince.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoProvince() throws Exception {
        // Get the geoProvince
        restGeoProvinceMockMvc.perform(get("/api/geo-provinces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoProvince() throws Exception {
        // Initialize the database
        geoProvinceRepository.saveAndFlush(geoProvince);

        int databaseSizeBeforeUpdate = geoProvinceRepository.findAll().size();

        // Update the geoProvince
        GeoProvince updatedGeoProvince = geoProvinceRepository.findById(geoProvince.getId()).get();
        // Disconnect from session so that the updates on updatedGeoProvince are not directly saved in db
        em.detach(updatedGeoProvince);
        updatedGeoProvince
            .libelle(UPDATED_LIBELLE);
        GeoProvinceDTO geoProvinceDTO = geoProvinceMapper.toDto(updatedGeoProvince);

        restGeoProvinceMockMvc.perform(put("/api/geo-provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoProvinceDTO)))
            .andExpect(status().isOk());

        // Validate the GeoProvince in the database
        List<GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
        assertThat(geoProvinceList).hasSize(databaseSizeBeforeUpdate);
        GeoProvince testGeoProvince = geoProvinceList.get(geoProvinceList.size() - 1);
        assertThat(testGeoProvince.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoProvince() throws Exception {
        int databaseSizeBeforeUpdate = geoProvinceRepository.findAll().size();

        // Create the GeoProvince
        GeoProvinceDTO geoProvinceDTO = geoProvinceMapper.toDto(geoProvince);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoProvinceMockMvc.perform(put("/api/geo-provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoProvinceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoProvince in the database
        List<GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
        assertThat(geoProvinceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoProvince() throws Exception {
        // Initialize the database
        geoProvinceRepository.saveAndFlush(geoProvince);

        int databaseSizeBeforeDelete = geoProvinceRepository.findAll().size();

        // Delete the geoProvince
        restGeoProvinceMockMvc.perform(delete("/api/geo-provinces/{id}", geoProvince.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
        assertThat(geoProvinceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
