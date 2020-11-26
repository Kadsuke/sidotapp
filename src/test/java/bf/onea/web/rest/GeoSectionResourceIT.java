package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoSection;
import bf.onea.repository.GeoSectionRepository;
import bf.onea.service.GeoSectionService;
import bf.onea.service.dto.GeoSectionDTO;
import bf.onea.service.mapper.GeoSectionMapper;

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
 * Integration tests for the {@link GeoSectionResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoSectionResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoSectionRepository geoSectionRepository;

    @Autowired
    private GeoSectionMapper geoSectionMapper;

    @Autowired
    private GeoSectionService geoSectionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoSectionMockMvc;

    private GeoSection geoSection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoSection createEntity(EntityManager em) {
        GeoSection geoSection = new GeoSection()
            .libelle(DEFAULT_LIBELLE);
        return geoSection;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoSection createUpdatedEntity(EntityManager em) {
        GeoSection geoSection = new GeoSection()
            .libelle(UPDATED_LIBELLE);
        return geoSection;
    }

    @BeforeEach
    public void initTest() {
        geoSection = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoSection() throws Exception {
        int databaseSizeBeforeCreate = geoSectionRepository.findAll().size();
        // Create the GeoSection
        GeoSectionDTO geoSectionDTO = geoSectionMapper.toDto(geoSection);
        restGeoSectionMockMvc.perform(post("/api/geo-sections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSectionDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoSection in the database
        List<GeoSection> geoSectionList = geoSectionRepository.findAll();
        assertThat(geoSectionList).hasSize(databaseSizeBeforeCreate + 1);
        GeoSection testGeoSection = geoSectionList.get(geoSectionList.size() - 1);
        assertThat(testGeoSection.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoSectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoSectionRepository.findAll().size();

        // Create the GeoSection with an existing ID
        geoSection.setId(1L);
        GeoSectionDTO geoSectionDTO = geoSectionMapper.toDto(geoSection);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoSectionMockMvc.perform(post("/api/geo-sections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoSection in the database
        List<GeoSection> geoSectionList = geoSectionRepository.findAll();
        assertThat(geoSectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoSections() throws Exception {
        // Initialize the database
        geoSectionRepository.saveAndFlush(geoSection);

        // Get all the geoSectionList
        restGeoSectionMockMvc.perform(get("/api/geo-sections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoSection.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoSection() throws Exception {
        // Initialize the database
        geoSectionRepository.saveAndFlush(geoSection);

        // Get the geoSection
        restGeoSectionMockMvc.perform(get("/api/geo-sections/{id}", geoSection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoSection.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoSection() throws Exception {
        // Get the geoSection
        restGeoSectionMockMvc.perform(get("/api/geo-sections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoSection() throws Exception {
        // Initialize the database
        geoSectionRepository.saveAndFlush(geoSection);

        int databaseSizeBeforeUpdate = geoSectionRepository.findAll().size();

        // Update the geoSection
        GeoSection updatedGeoSection = geoSectionRepository.findById(geoSection.getId()).get();
        // Disconnect from session so that the updates on updatedGeoSection are not directly saved in db
        em.detach(updatedGeoSection);
        updatedGeoSection
            .libelle(UPDATED_LIBELLE);
        GeoSectionDTO geoSectionDTO = geoSectionMapper.toDto(updatedGeoSection);

        restGeoSectionMockMvc.perform(put("/api/geo-sections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSectionDTO)))
            .andExpect(status().isOk());

        // Validate the GeoSection in the database
        List<GeoSection> geoSectionList = geoSectionRepository.findAll();
        assertThat(geoSectionList).hasSize(databaseSizeBeforeUpdate);
        GeoSection testGeoSection = geoSectionList.get(geoSectionList.size() - 1);
        assertThat(testGeoSection.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoSection() throws Exception {
        int databaseSizeBeforeUpdate = geoSectionRepository.findAll().size();

        // Create the GeoSection
        GeoSectionDTO geoSectionDTO = geoSectionMapper.toDto(geoSection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoSectionMockMvc.perform(put("/api/geo-sections")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoSection in the database
        List<GeoSection> geoSectionList = geoSectionRepository.findAll();
        assertThat(geoSectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoSection() throws Exception {
        // Initialize the database
        geoSectionRepository.saveAndFlush(geoSection);

        int databaseSizeBeforeDelete = geoSectionRepository.findAll().size();

        // Delete the geoSection
        restGeoSectionMockMvc.perform(delete("/api/geo-sections/{id}", geoSection.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoSection> geoSectionList = geoSectionRepository.findAll();
        assertThat(geoSectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
