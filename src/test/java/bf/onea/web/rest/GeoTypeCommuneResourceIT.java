package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoTypeCommune;
import bf.onea.repository.GeoTypeCommuneRepository;
import bf.onea.service.GeoTypeCommuneService;
import bf.onea.service.dto.GeoTypeCommuneDTO;
import bf.onea.service.mapper.GeoTypeCommuneMapper;

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
 * Integration tests for the {@link GeoTypeCommuneResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoTypeCommuneResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoTypeCommuneRepository geoTypeCommuneRepository;

    @Autowired
    private GeoTypeCommuneMapper geoTypeCommuneMapper;

    @Autowired
    private GeoTypeCommuneService geoTypeCommuneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoTypeCommuneMockMvc;

    private GeoTypeCommune geoTypeCommune;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoTypeCommune createEntity(EntityManager em) {
        GeoTypeCommune geoTypeCommune = new GeoTypeCommune()
            .libelle(DEFAULT_LIBELLE);
        return geoTypeCommune;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoTypeCommune createUpdatedEntity(EntityManager em) {
        GeoTypeCommune geoTypeCommune = new GeoTypeCommune()
            .libelle(UPDATED_LIBELLE);
        return geoTypeCommune;
    }

    @BeforeEach
    public void initTest() {
        geoTypeCommune = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoTypeCommune() throws Exception {
        int databaseSizeBeforeCreate = geoTypeCommuneRepository.findAll().size();
        // Create the GeoTypeCommune
        GeoTypeCommuneDTO geoTypeCommuneDTO = geoTypeCommuneMapper.toDto(geoTypeCommune);
        restGeoTypeCommuneMockMvc.perform(post("/api/geo-type-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoTypeCommuneDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoTypeCommune in the database
        List<GeoTypeCommune> geoTypeCommuneList = geoTypeCommuneRepository.findAll();
        assertThat(geoTypeCommuneList).hasSize(databaseSizeBeforeCreate + 1);
        GeoTypeCommune testGeoTypeCommune = geoTypeCommuneList.get(geoTypeCommuneList.size() - 1);
        assertThat(testGeoTypeCommune.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoTypeCommuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoTypeCommuneRepository.findAll().size();

        // Create the GeoTypeCommune with an existing ID
        geoTypeCommune.setId(1L);
        GeoTypeCommuneDTO geoTypeCommuneDTO = geoTypeCommuneMapper.toDto(geoTypeCommune);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoTypeCommuneMockMvc.perform(post("/api/geo-type-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoTypeCommuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoTypeCommune in the database
        List<GeoTypeCommune> geoTypeCommuneList = geoTypeCommuneRepository.findAll();
        assertThat(geoTypeCommuneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoTypeCommunes() throws Exception {
        // Initialize the database
        geoTypeCommuneRepository.saveAndFlush(geoTypeCommune);

        // Get all the geoTypeCommuneList
        restGeoTypeCommuneMockMvc.perform(get("/api/geo-type-communes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoTypeCommune.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoTypeCommune() throws Exception {
        // Initialize the database
        geoTypeCommuneRepository.saveAndFlush(geoTypeCommune);

        // Get the geoTypeCommune
        restGeoTypeCommuneMockMvc.perform(get("/api/geo-type-communes/{id}", geoTypeCommune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoTypeCommune.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoTypeCommune() throws Exception {
        // Get the geoTypeCommune
        restGeoTypeCommuneMockMvc.perform(get("/api/geo-type-communes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoTypeCommune() throws Exception {
        // Initialize the database
        geoTypeCommuneRepository.saveAndFlush(geoTypeCommune);

        int databaseSizeBeforeUpdate = geoTypeCommuneRepository.findAll().size();

        // Update the geoTypeCommune
        GeoTypeCommune updatedGeoTypeCommune = geoTypeCommuneRepository.findById(geoTypeCommune.getId()).get();
        // Disconnect from session so that the updates on updatedGeoTypeCommune are not directly saved in db
        em.detach(updatedGeoTypeCommune);
        updatedGeoTypeCommune
            .libelle(UPDATED_LIBELLE);
        GeoTypeCommuneDTO geoTypeCommuneDTO = geoTypeCommuneMapper.toDto(updatedGeoTypeCommune);

        restGeoTypeCommuneMockMvc.perform(put("/api/geo-type-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoTypeCommuneDTO)))
            .andExpect(status().isOk());

        // Validate the GeoTypeCommune in the database
        List<GeoTypeCommune> geoTypeCommuneList = geoTypeCommuneRepository.findAll();
        assertThat(geoTypeCommuneList).hasSize(databaseSizeBeforeUpdate);
        GeoTypeCommune testGeoTypeCommune = geoTypeCommuneList.get(geoTypeCommuneList.size() - 1);
        assertThat(testGeoTypeCommune.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoTypeCommune() throws Exception {
        int databaseSizeBeforeUpdate = geoTypeCommuneRepository.findAll().size();

        // Create the GeoTypeCommune
        GeoTypeCommuneDTO geoTypeCommuneDTO = geoTypeCommuneMapper.toDto(geoTypeCommune);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoTypeCommuneMockMvc.perform(put("/api/geo-type-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoTypeCommuneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoTypeCommune in the database
        List<GeoTypeCommune> geoTypeCommuneList = geoTypeCommuneRepository.findAll();
        assertThat(geoTypeCommuneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoTypeCommune() throws Exception {
        // Initialize the database
        geoTypeCommuneRepository.saveAndFlush(geoTypeCommune);

        int databaseSizeBeforeDelete = geoTypeCommuneRepository.findAll().size();

        // Delete the geoTypeCommune
        restGeoTypeCommuneMockMvc.perform(delete("/api/geo-type-communes/{id}", geoTypeCommune.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoTypeCommune> geoTypeCommuneList = geoTypeCommuneRepository.findAll();
        assertThat(geoTypeCommuneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
