package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeoSecteur;
import bf.onea.repository.GeoSecteurRepository;
import bf.onea.service.GeoSecteurService;
import bf.onea.service.dto.GeoSecteurDTO;
import bf.onea.service.mapper.GeoSecteurMapper;

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
 * Integration tests for the {@link GeoSecteurResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeoSecteurResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GeoSecteurRepository geoSecteurRepository;

    @Autowired
    private GeoSecteurMapper geoSecteurMapper;

    @Autowired
    private GeoSecteurService geoSecteurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeoSecteurMockMvc;

    private GeoSecteur geoSecteur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoSecteur createEntity(EntityManager em) {
        GeoSecteur geoSecteur = new GeoSecteur()
            .libelle(DEFAULT_LIBELLE);
        return geoSecteur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoSecteur createUpdatedEntity(EntityManager em) {
        GeoSecteur geoSecteur = new GeoSecteur()
            .libelle(UPDATED_LIBELLE);
        return geoSecteur;
    }

    @BeforeEach
    public void initTest() {
        geoSecteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoSecteur() throws Exception {
        int databaseSizeBeforeCreate = geoSecteurRepository.findAll().size();
        // Create the GeoSecteur
        GeoSecteurDTO geoSecteurDTO = geoSecteurMapper.toDto(geoSecteur);
        restGeoSecteurMockMvc.perform(post("/api/geo-secteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSecteurDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoSecteur in the database
        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeCreate + 1);
        GeoSecteur testGeoSecteur = geoSecteurList.get(geoSecteurList.size() - 1);
        assertThat(testGeoSecteur.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGeoSecteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoSecteurRepository.findAll().size();

        // Create the GeoSecteur with an existing ID
        geoSecteur.setId(1L);
        GeoSecteurDTO geoSecteurDTO = geoSecteurMapper.toDto(geoSecteur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoSecteurMockMvc.perform(post("/api/geo-secteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSecteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoSecteur in the database
        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = geoSecteurRepository.findAll().size();
        // set the field null
        geoSecteur.setLibelle(null);

        // Create the GeoSecteur, which fails.
        GeoSecteurDTO geoSecteurDTO = geoSecteurMapper.toDto(geoSecteur);


        restGeoSecteurMockMvc.perform(post("/api/geo-secteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSecteurDTO)))
            .andExpect(status().isBadRequest());

        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeoSecteurs() throws Exception {
        // Initialize the database
        geoSecteurRepository.saveAndFlush(geoSecteur);

        // Get all the geoSecteurList
        restGeoSecteurMockMvc.perform(get("/api/geo-secteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoSecteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getGeoSecteur() throws Exception {
        // Initialize the database
        geoSecteurRepository.saveAndFlush(geoSecteur);

        // Get the geoSecteur
        restGeoSecteurMockMvc.perform(get("/api/geo-secteurs/{id}", geoSecteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geoSecteur.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingGeoSecteur() throws Exception {
        // Get the geoSecteur
        restGeoSecteurMockMvc.perform(get("/api/geo-secteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoSecteur() throws Exception {
        // Initialize the database
        geoSecteurRepository.saveAndFlush(geoSecteur);

        int databaseSizeBeforeUpdate = geoSecteurRepository.findAll().size();

        // Update the geoSecteur
        GeoSecteur updatedGeoSecteur = geoSecteurRepository.findById(geoSecteur.getId()).get();
        // Disconnect from session so that the updates on updatedGeoSecteur are not directly saved in db
        em.detach(updatedGeoSecteur);
        updatedGeoSecteur
            .libelle(UPDATED_LIBELLE);
        GeoSecteurDTO geoSecteurDTO = geoSecteurMapper.toDto(updatedGeoSecteur);

        restGeoSecteurMockMvc.perform(put("/api/geo-secteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSecteurDTO)))
            .andExpect(status().isOk());

        // Validate the GeoSecteur in the database
        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeUpdate);
        GeoSecteur testGeoSecteur = geoSecteurList.get(geoSecteurList.size() - 1);
        assertThat(testGeoSecteur.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoSecteur() throws Exception {
        int databaseSizeBeforeUpdate = geoSecteurRepository.findAll().size();

        // Create the GeoSecteur
        GeoSecteurDTO geoSecteurDTO = geoSecteurMapper.toDto(geoSecteur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoSecteurMockMvc.perform(put("/api/geo-secteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geoSecteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoSecteur in the database
        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoSecteur() throws Exception {
        // Initialize the database
        geoSecteurRepository.saveAndFlush(geoSecteur);

        int databaseSizeBeforeDelete = geoSecteurRepository.findAll().size();

        // Delete the geoSecteur
        restGeoSecteurMockMvc.perform(delete("/api/geo-secteurs/{id}", geoSecteur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoSecteur> geoSecteurList = geoSecteurRepository.findAll();
        assertThat(geoSecteurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
