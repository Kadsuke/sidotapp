package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.NatureOuvrage;
import bf.onea.repository.NatureOuvrageRepository;
import bf.onea.service.NatureOuvrageService;
import bf.onea.service.dto.NatureOuvrageDTO;
import bf.onea.service.mapper.NatureOuvrageMapper;

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
 * Integration tests for the {@link NatureOuvrageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureOuvrageResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private NatureOuvrageRepository natureOuvrageRepository;

    @Autowired
    private NatureOuvrageMapper natureOuvrageMapper;

    @Autowired
    private NatureOuvrageService natureOuvrageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureOuvrageMockMvc;

    private NatureOuvrage natureOuvrage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureOuvrage createEntity(EntityManager em) {
        NatureOuvrage natureOuvrage = new NatureOuvrage()
            .libelle(DEFAULT_LIBELLE);
        return natureOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureOuvrage createUpdatedEntity(EntityManager em) {
        NatureOuvrage natureOuvrage = new NatureOuvrage()
            .libelle(UPDATED_LIBELLE);
        return natureOuvrage;
    }

    @BeforeEach
    public void initTest() {
        natureOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureOuvrage() throws Exception {
        int databaseSizeBeforeCreate = natureOuvrageRepository.findAll().size();
        // Create the NatureOuvrage
        NatureOuvrageDTO natureOuvrageDTO = natureOuvrageMapper.toDto(natureOuvrage);
        restNatureOuvrageMockMvc.perform(post("/api/nature-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the NatureOuvrage in the database
        List<NatureOuvrage> natureOuvrageList = natureOuvrageRepository.findAll();
        assertThat(natureOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        NatureOuvrage testNatureOuvrage = natureOuvrageList.get(natureOuvrageList.size() - 1);
        assertThat(testNatureOuvrage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createNatureOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureOuvrageRepository.findAll().size();

        // Create the NatureOuvrage with an existing ID
        natureOuvrage.setId(1L);
        NatureOuvrageDTO natureOuvrageDTO = natureOuvrageMapper.toDto(natureOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureOuvrageMockMvc.perform(post("/api/nature-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureOuvrage in the database
        List<NatureOuvrage> natureOuvrageList = natureOuvrageRepository.findAll();
        assertThat(natureOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNatureOuvrages() throws Exception {
        // Initialize the database
        natureOuvrageRepository.saveAndFlush(natureOuvrage);

        // Get all the natureOuvrageList
        restNatureOuvrageMockMvc.perform(get("/api/nature-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getNatureOuvrage() throws Exception {
        // Initialize the database
        natureOuvrageRepository.saveAndFlush(natureOuvrage);

        // Get the natureOuvrage
        restNatureOuvrageMockMvc.perform(get("/api/nature-ouvrages/{id}", natureOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(natureOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingNatureOuvrage() throws Exception {
        // Get the natureOuvrage
        restNatureOuvrageMockMvc.perform(get("/api/nature-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureOuvrage() throws Exception {
        // Initialize the database
        natureOuvrageRepository.saveAndFlush(natureOuvrage);

        int databaseSizeBeforeUpdate = natureOuvrageRepository.findAll().size();

        // Update the natureOuvrage
        NatureOuvrage updatedNatureOuvrage = natureOuvrageRepository.findById(natureOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedNatureOuvrage are not directly saved in db
        em.detach(updatedNatureOuvrage);
        updatedNatureOuvrage
            .libelle(UPDATED_LIBELLE);
        NatureOuvrageDTO natureOuvrageDTO = natureOuvrageMapper.toDto(updatedNatureOuvrage);

        restNatureOuvrageMockMvc.perform(put("/api/nature-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the NatureOuvrage in the database
        List<NatureOuvrage> natureOuvrageList = natureOuvrageRepository.findAll();
        assertThat(natureOuvrageList).hasSize(databaseSizeBeforeUpdate);
        NatureOuvrage testNatureOuvrage = natureOuvrageList.get(natureOuvrageList.size() - 1);
        assertThat(testNatureOuvrage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = natureOuvrageRepository.findAll().size();

        // Create the NatureOuvrage
        NatureOuvrageDTO natureOuvrageDTO = natureOuvrageMapper.toDto(natureOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureOuvrageMockMvc.perform(put("/api/nature-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureOuvrage in the database
        List<NatureOuvrage> natureOuvrageList = natureOuvrageRepository.findAll();
        assertThat(natureOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureOuvrage() throws Exception {
        // Initialize the database
        natureOuvrageRepository.saveAndFlush(natureOuvrage);

        int databaseSizeBeforeDelete = natureOuvrageRepository.findAll().size();

        // Delete the natureOuvrage
        restNatureOuvrageMockMvc.perform(delete("/api/nature-ouvrages/{id}", natureOuvrage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NatureOuvrage> natureOuvrageList = natureOuvrageRepository.findAll();
        assertThat(natureOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
