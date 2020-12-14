package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoCommune;
import bf.onea.repository.GeoCommuneRepository;
import bf.onea.service.GeoCommuneService;
import bf.onea.service.dto.GeoCommuneDTO;
import bf.onea.service.mapper.GeoCommuneMapper;

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
 * Integration tests for the {@link GeoCommuneResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoCommuneResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoCommuneRepository geoCommuneRepository;

    @Autowired
    private GeoCommuneMapper geoCommuneMapper;

    @Autowired
    private GeoCommuneService geoCommuneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoCommuneMockMvc;

    private GeoCommune geoCommune;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoCommune createEntity(EntityManager em) {
        GeoCommune geoCommune = new GeoCommune()
            .libelle(DEFAULT_LIBELLE);
        return geoCommune;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoCommune createUpdatedEntity(EntityManager em) {
        GeoCommune geoCommune = new GeoCommune()
            .libelle(UPDATED_LIBELLE);
        return geoCommune;
    }

    @BeforeEach
    public void initTest() {
        geoCommune = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoCommune() throws Exception {
        int databaseSizeBeforeCreate = geoCommuneRepository.findAll().size();
        // Create the GeoCommune
        GeoCommuneDTO geoCommuneDTO = geoCommuneMapper.toDto(geoCommune);
        restGeoCommuneMockMvc.perform(post("/api/geo-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoCommuneDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoCommune in the database
        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeCreate + 1);
        GeoCommune testGeoCommune = geoCommuneList.get(geoCommuneList.size() - 1);
        assertThat(testGeoCommune.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoCommuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoCommuneRepository.findAll().size();

        // Create the GeoCommune with an existing ID
        geoCommune.setId(1L);
        GeoCommuneDTO geoCommuneDTO = geoCommuneMapper.toDto(geoCommune);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoCommuneMockMvc.perform(post("/api/geo-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoCommuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoCommune in the database
        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = geoCommuneRepository.findAll().size();
        // set the field null
        geoCommune.setLibelle(null);

        // Create the GeoCommune, which fails.
        GeoCommuneDTO geoCommuneDTO = geoCommuneMapper.toDto(geoCommune);


        restGeoCommuneMockMvc.perform(post("/api/geo-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoCommuneDTO)))
            .andExpect(status().isBadRequest());

        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeoCommunes() throws Exception {
        // Initialize the database
        geoCommuneRepository.saveAndFlush(geoCommune);

        // Get all the geoCommuneList
        restGeoCommuneMockMvc.perform(get("/api/geo-communes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoCommune.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoCommune() throws Exception {
        // Initialize the database
        geoCommuneRepository.saveAndFlush(geoCommune);

        // Get the geoCommune
        restGeoCommuneMockMvc.perform(get("/api/geo-communes/{id}", geoCommune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoCommune.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoCommune() throws Exception {
        // Get the geoCommune
        restGeoCommuneMockMvc.perform(get("/api/geo-communes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoCommune() throws Exception {
        // Initialize the database
        geoCommuneRepository.saveAndFlush(geoCommune);

        int databaseSizeBeforeUpdate = geoCommuneRepository.findAll().size();

        // Update the geoCommune
        GeoCommune updatedGeoCommune = geoCommuneRepository.findById(geoCommune.getId()).get();
        // Disconnect from session so that the updates on updatedGeoCommune are not directly saved in db
        em.detach(updatedGeoCommune);
        updatedGeoCommune
            .libelle(UPDATED_LIBELLE);
        GeoCommuneDTO geoCommuneDTO = geoCommuneMapper.toDto(updatedGeoCommune);

        restGeoCommuneMockMvc.perform(put("/api/geo-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoCommuneDTO)))
            .andExpect(status().isOk());

        // Validate the GeoCommune in the database
        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeUpdate);
        GeoCommune testGeoCommune = geoCommuneList.get(geoCommuneList.size() - 1);
        assertThat(testGeoCommune.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoCommune() throws Exception {
        int databaseSizeBeforeUpdate = geoCommuneRepository.findAll().size();

        // Create the GeoCommune
        GeoCommuneDTO geoCommuneDTO = geoCommuneMapper.toDto(geoCommune);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoCommuneMockMvc.perform(put("/api/geo-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoCommuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoCommune in the database
        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoCommune() throws Exception {
        // Initialize the database
        geoCommuneRepository.saveAndFlush(geoCommune);

        int databaseSizeBeforeDelete = geoCommuneRepository.findAll().size();

        // Delete the geoCommune
        restGeoCommuneMockMvc.perform(delete("/api/geo-communes/{id}", geoCommune.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoCommune> geoCommuneList = geoCommuneRepository.findAll();
        assertThat(geoCommuneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
