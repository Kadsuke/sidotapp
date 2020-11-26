package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoLocalite;
import bf.onea.repository.GeoLocaliteRepository;
import bf.onea.service.GeoLocaliteService;
import bf.onea.service.dto.GeoLocaliteDTO;
import bf.onea.service.mapper.GeoLocaliteMapper;

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
 * Integration tests for the {@link GeoLocaliteResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoLocaliteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoLocaliteRepository geoLocaliteRepository;

    @Autowired
    private GeoLocaliteMapper geoLocaliteMapper;

    @Autowired
    private GeoLocaliteService geoLocaliteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoLocaliteMockMvc;

    private GeoLocalite geoLocalite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoLocalite createEntity(EntityManager em) {
        GeoLocalite geoLocalite = new GeoLocalite()
            .libelle(DEFAULT_LIBELLE);
        return geoLocalite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoLocalite createUpdatedEntity(EntityManager em) {
        GeoLocalite geoLocalite = new GeoLocalite()
            .libelle(UPDATED_LIBELLE);
        return geoLocalite;
    }

    @BeforeEach
    public void initTest() {
        geoLocalite = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoLocalite() throws Exception {
        int databaseSizeBeforeCreate = geoLocaliteRepository.findAll().size();
        // Create the GeoLocalite
        GeoLocaliteDTO geoLocaliteDTO = geoLocaliteMapper.toDto(geoLocalite);
        restGeoLocaliteMockMvc.perform(post("/api/geo-localites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLocaliteDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoLocalite in the database
        List<GeoLocalite> geoLocaliteList = geoLocaliteRepository.findAll();
        assertThat(geoLocaliteList).hasSize(databaseSizeBeforeCreate + 1);
        GeoLocalite testGeoLocalite = geoLocaliteList.get(geoLocaliteList.size() - 1);
        assertThat(testGeoLocalite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoLocaliteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoLocaliteRepository.findAll().size();

        // Create the GeoLocalite with an existing ID
        geoLocalite.setId(1L);
        GeoLocaliteDTO geoLocaliteDTO = geoLocaliteMapper.toDto(geoLocalite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoLocaliteMockMvc.perform(post("/api/geo-localites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLocaliteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoLocalite in the database
        List<GeoLocalite> geoLocaliteList = geoLocaliteRepository.findAll();
        assertThat(geoLocaliteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoLocalites() throws Exception {
        // Initialize the database
        geoLocaliteRepository.saveAndFlush(geoLocalite);

        // Get all the geoLocaliteList
        restGeoLocaliteMockMvc.perform(get("/api/geo-localites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoLocalite.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoLocalite() throws Exception {
        // Initialize the database
        geoLocaliteRepository.saveAndFlush(geoLocalite);

        // Get the geoLocalite
        restGeoLocaliteMockMvc.perform(get("/api/geo-localites/{id}", geoLocalite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoLocalite.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoLocalite() throws Exception {
        // Get the geoLocalite
        restGeoLocaliteMockMvc.perform(get("/api/geo-localites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoLocalite() throws Exception {
        // Initialize the database
        geoLocaliteRepository.saveAndFlush(geoLocalite);

        int databaseSizeBeforeUpdate = geoLocaliteRepository.findAll().size();

        // Update the geoLocalite
        GeoLocalite updatedGeoLocalite = geoLocaliteRepository.findById(geoLocalite.getId()).get();
        // Disconnect from session so that the updates on updatedGeoLocalite are not directly saved in db
        em.detach(updatedGeoLocalite);
        updatedGeoLocalite
            .libelle(UPDATED_LIBELLE);
        GeoLocaliteDTO geoLocaliteDTO = geoLocaliteMapper.toDto(updatedGeoLocalite);

        restGeoLocaliteMockMvc.perform(put("/api/geo-localites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLocaliteDTO)))
            .andExpect(status().isOk());

        // Validate the GeoLocalite in the database
        List<GeoLocalite> geoLocaliteList = geoLocaliteRepository.findAll();
        assertThat(geoLocaliteList).hasSize(databaseSizeBeforeUpdate);
        GeoLocalite testGeoLocalite = geoLocaliteList.get(geoLocaliteList.size() - 1);
        assertThat(testGeoLocalite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoLocalite() throws Exception {
        int databaseSizeBeforeUpdate = geoLocaliteRepository.findAll().size();

        // Create the GeoLocalite
        GeoLocaliteDTO geoLocaliteDTO = geoLocaliteMapper.toDto(geoLocalite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoLocaliteMockMvc.perform(put("/api/geo-localites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLocaliteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoLocalite in the database
        List<GeoLocalite> geoLocaliteList = geoLocaliteRepository.findAll();
        assertThat(geoLocaliteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoLocalite() throws Exception {
        // Initialize the database
        geoLocaliteRepository.saveAndFlush(geoLocalite);

        int databaseSizeBeforeDelete = geoLocaliteRepository.findAll().size();

        // Delete the geoLocalite
        restGeoLocaliteMockMvc.perform(delete("/api/geo-localites/{id}", geoLocalite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoLocalite> geoLocaliteList = geoLocaliteRepository.findAll();
        assertThat(geoLocaliteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
