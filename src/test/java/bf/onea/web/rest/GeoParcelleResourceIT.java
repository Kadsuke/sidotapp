package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoParcelle;
import bf.onea.repository.GeoParcelleRepository;
import bf.onea.service.GeoParcelleService;
import bf.onea.service.dto.GeoParcelleDTO;
import bf.onea.service.mapper.GeoParcelleMapper;

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
 * Integration tests for the {@link GeoParcelleResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoParcelleResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoParcelleRepository geoParcelleRepository;

    @Autowired
    private GeoParcelleMapper geoParcelleMapper;

    @Autowired
    private GeoParcelleService geoParcelleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoParcelleMockMvc;

    private GeoParcelle geoParcelle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoParcelle createEntity(EntityManager em) {
        GeoParcelle geoParcelle = new GeoParcelle()
            .libelle(DEFAULT_LIBELLE);
        return geoParcelle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoParcelle createUpdatedEntity(EntityManager em) {
        GeoParcelle geoParcelle = new GeoParcelle()
            .libelle(UPDATED_LIBELLE);
        return geoParcelle;
    }

    @BeforeEach
    public void initTest() {
        geoParcelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoParcelle() throws Exception {
        int databaseSizeBeforeCreate = geoParcelleRepository.findAll().size();
        // Create the GeoParcelle
        GeoParcelleDTO geoParcelleDTO = geoParcelleMapper.toDto(geoParcelle);
        restGeoParcelleMockMvc.perform(post("/api/geo-parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoParcelleDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoParcelle in the database
        List<GeoParcelle> geoParcelleList = geoParcelleRepository.findAll();
        assertThat(geoParcelleList).hasSize(databaseSizeBeforeCreate + 1);
        GeoParcelle testGeoParcelle = geoParcelleList.get(geoParcelleList.size() - 1);
        assertThat(testGeoParcelle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoParcelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoParcelleRepository.findAll().size();

        // Create the GeoParcelle with an existing ID
        geoParcelle.setId(1L);
        GeoParcelleDTO geoParcelleDTO = geoParcelleMapper.toDto(geoParcelle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoParcelleMockMvc.perform(post("/api/geo-parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoParcelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoParcelle in the database
        List<GeoParcelle> geoParcelleList = geoParcelleRepository.findAll();
        assertThat(geoParcelleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoParcelles() throws Exception {
        // Initialize the database
        geoParcelleRepository.saveAndFlush(geoParcelle);

        // Get all the geoParcelleList
        restGeoParcelleMockMvc.perform(get("/api/geo-parcelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoParcelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoParcelle() throws Exception {
        // Initialize the database
        geoParcelleRepository.saveAndFlush(geoParcelle);

        // Get the geoParcelle
        restGeoParcelleMockMvc.perform(get("/api/geo-parcelles/{id}", geoParcelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoParcelle.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoParcelle() throws Exception {
        // Get the geoParcelle
        restGeoParcelleMockMvc.perform(get("/api/geo-parcelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoParcelle() throws Exception {
        // Initialize the database
        geoParcelleRepository.saveAndFlush(geoParcelle);

        int databaseSizeBeforeUpdate = geoParcelleRepository.findAll().size();

        // Update the geoParcelle
        GeoParcelle updatedGeoParcelle = geoParcelleRepository.findById(geoParcelle.getId()).get();
        // Disconnect from session so that the updates on updatedGeoParcelle are not directly saved in db
        em.detach(updatedGeoParcelle);
        updatedGeoParcelle
            .libelle(UPDATED_LIBELLE);
        GeoParcelleDTO geoParcelleDTO = geoParcelleMapper.toDto(updatedGeoParcelle);

        restGeoParcelleMockMvc.perform(put("/api/geo-parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoParcelleDTO)))
            .andExpect(status().isOk());

        // Validate the GeoParcelle in the database
        List<GeoParcelle> geoParcelleList = geoParcelleRepository.findAll();
        assertThat(geoParcelleList).hasSize(databaseSizeBeforeUpdate);
        GeoParcelle testGeoParcelle = geoParcelleList.get(geoParcelleList.size() - 1);
        assertThat(testGeoParcelle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoParcelle() throws Exception {
        int databaseSizeBeforeUpdate = geoParcelleRepository.findAll().size();

        // Create the GeoParcelle
        GeoParcelleDTO geoParcelleDTO = geoParcelleMapper.toDto(geoParcelle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoParcelleMockMvc.perform(put("/api/geo-parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoParcelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoParcelle in the database
        List<GeoParcelle> geoParcelleList = geoParcelleRepository.findAll();
        assertThat(geoParcelleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoParcelle() throws Exception {
        // Initialize the database
        geoParcelleRepository.saveAndFlush(geoParcelle);

        int databaseSizeBeforeDelete = geoParcelleRepository.findAll().size();

        // Delete the geoParcelle
        restGeoParcelleMockMvc.perform(delete("/api/geo-parcelles/{id}", geoParcelle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoParcelle> geoParcelleList = geoParcelleRepository.findAll();
        assertThat(geoParcelleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
