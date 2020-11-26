package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoLot;
import bf.onea.repository.GeoLotRepository;
import bf.onea.service.GeoLotService;
import bf.onea.service.dto.GeoLotDTO;
import bf.onea.service.mapper.GeoLotMapper;

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
 * Integration tests for the {@link GeoLotResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoLotResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoLotRepository geoLotRepository;

    @Autowired
    private GeoLotMapper geoLotMapper;

    @Autowired
    private GeoLotService geoLotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoLotMockMvc;

    private GeoLot geoLot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoLot createEntity(EntityManager em) {
        GeoLot geoLot = new GeoLot()
            .libelle(DEFAULT_LIBELLE);
        return geoLot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoLot createUpdatedEntity(EntityManager em) {
        GeoLot geoLot = new GeoLot()
            .libelle(UPDATED_LIBELLE);
        return geoLot;
    }

    @BeforeEach
    public void initTest() {
        geoLot = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoLot() throws Exception {
        int databaseSizeBeforeCreate = geoLotRepository.findAll().size();
        // Create the GeoLot
        GeoLotDTO geoLotDTO = geoLotMapper.toDto(geoLot);
        restGeoLotMockMvc.perform(post("/api/geo-lots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLotDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoLot in the database
        List<GeoLot> geoLotList = geoLotRepository.findAll();
        assertThat(geoLotList).hasSize(databaseSizeBeforeCreate + 1);
        GeoLot testGeoLot = geoLotList.get(geoLotList.size() - 1);
        assertThat(testGeoLot.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoLotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoLotRepository.findAll().size();

        // Create the GeoLot with an existing ID
        geoLot.setId(1L);
        GeoLotDTO geoLotDTO = geoLotMapper.toDto(geoLot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoLotMockMvc.perform(post("/api/geo-lots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoLot in the database
        List<GeoLot> geoLotList = geoLotRepository.findAll();
        assertThat(geoLotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoLots() throws Exception {
        // Initialize the database
        geoLotRepository.saveAndFlush(geoLot);

        // Get all the geoLotList
        restGeoLotMockMvc.perform(get("/api/geo-lots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoLot.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoLot() throws Exception {
        // Initialize the database
        geoLotRepository.saveAndFlush(geoLot);

        // Get the geoLot
        restGeoLotMockMvc.perform(get("/api/geo-lots/{id}", geoLot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoLot.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoLot() throws Exception {
        // Get the geoLot
        restGeoLotMockMvc.perform(get("/api/geo-lots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoLot() throws Exception {
        // Initialize the database
        geoLotRepository.saveAndFlush(geoLot);

        int databaseSizeBeforeUpdate = geoLotRepository.findAll().size();

        // Update the geoLot
        GeoLot updatedGeoLot = geoLotRepository.findById(geoLot.getId()).get();
        // Disconnect from session so that the updates on updatedGeoLot are not directly saved in db
        em.detach(updatedGeoLot);
        updatedGeoLot
            .libelle(UPDATED_LIBELLE);
        GeoLotDTO geoLotDTO = geoLotMapper.toDto(updatedGeoLot);

        restGeoLotMockMvc.perform(put("/api/geo-lots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLotDTO)))
            .andExpect(status().isOk());

        // Validate the GeoLot in the database
        List<GeoLot> geoLotList = geoLotRepository.findAll();
        assertThat(geoLotList).hasSize(databaseSizeBeforeUpdate);
        GeoLot testGeoLot = geoLotList.get(geoLotList.size() - 1);
        assertThat(testGeoLot.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoLot() throws Exception {
        int databaseSizeBeforeUpdate = geoLotRepository.findAll().size();

        // Create the GeoLot
        GeoLotDTO geoLotDTO = geoLotMapper.toDto(geoLot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoLotMockMvc.perform(put("/api/geo-lots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoLotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoLot in the database
        List<GeoLot> geoLotList = geoLotRepository.findAll();
        assertThat(geoLotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoLot() throws Exception {
        // Initialize the database
        geoLotRepository.saveAndFlush(geoLot);

        int databaseSizeBeforeDelete = geoLotRepository.findAll().size();

        // Delete the geoLot
        restGeoLotMockMvc.perform(delete("/api/geo-lots/{id}", geoLot.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoLot> geoLotList = geoLotRepository.findAll();
        assertThat(geoLotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
