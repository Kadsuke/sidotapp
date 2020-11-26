package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.CaracteristiqueOuvrage;
import bf.onea.repository.CaracteristiqueOuvrageRepository;
import bf.onea.service.CaracteristiqueOuvrageService;
import bf.onea.service.dto.CaracteristiqueOuvrageDTO;
import bf.onea.service.mapper.CaracteristiqueOuvrageMapper;

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
 * Integration tests for the {@link CaracteristiqueOuvrageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaracteristiqueOuvrageResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_UNITE = "AAAAAAAAAA";
    private static final String UPDATED_UNITE = "BBBBBBBBBB";

    @Autowired
    private CaracteristiqueOuvrageRepository caracteristiqueOuvrageRepository;

    @Autowired
    private CaracteristiqueOuvrageMapper caracteristiqueOuvrageMapper;

    @Autowired
    private CaracteristiqueOuvrageService caracteristiqueOuvrageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaracteristiqueOuvrageMockMvc;

    private CaracteristiqueOuvrage caracteristiqueOuvrage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaracteristiqueOuvrage createEntity(EntityManager em) {
        CaracteristiqueOuvrage caracteristiqueOuvrage = new CaracteristiqueOuvrage()
            .libelle(DEFAULT_LIBELLE)
            .unite(DEFAULT_UNITE);
        return caracteristiqueOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaracteristiqueOuvrage createUpdatedEntity(EntityManager em) {
        CaracteristiqueOuvrage caracteristiqueOuvrage = new CaracteristiqueOuvrage()
            .libelle(UPDATED_LIBELLE)
            .unite(UPDATED_UNITE);
        return caracteristiqueOuvrage;
    }

    @BeforeEach
    public void initTest() {
        caracteristiqueOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaracteristiqueOuvrage() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueOuvrageRepository.findAll().size();
        // Create the CaracteristiqueOuvrage
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO = caracteristiqueOuvrageMapper.toDto(caracteristiqueOuvrage);
        restCaracteristiqueOuvrageMockMvc.perform(post("/api/caracteristique-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the CaracteristiqueOuvrage in the database
        List<CaracteristiqueOuvrage> caracteristiqueOuvrageList = caracteristiqueOuvrageRepository.findAll();
        assertThat(caracteristiqueOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        CaracteristiqueOuvrage testCaracteristiqueOuvrage = caracteristiqueOuvrageList.get(caracteristiqueOuvrageList.size() - 1);
        assertThat(testCaracteristiqueOuvrage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCaracteristiqueOuvrage.getUnite()).isEqualTo(DEFAULT_UNITE);
    }

    @Test
    @Transactional
    public void createCaracteristiqueOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueOuvrageRepository.findAll().size();

        // Create the CaracteristiqueOuvrage with an existing ID
        caracteristiqueOuvrage.setId(1L);
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO = caracteristiqueOuvrageMapper.toDto(caracteristiqueOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaracteristiqueOuvrageMockMvc.perform(post("/api/caracteristique-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristiqueOuvrage in the database
        List<CaracteristiqueOuvrage> caracteristiqueOuvrageList = caracteristiqueOuvrageRepository.findAll();
        assertThat(caracteristiqueOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaracteristiqueOuvrages() throws Exception {
        // Initialize the database
        caracteristiqueOuvrageRepository.saveAndFlush(caracteristiqueOuvrage);

        // Get all the caracteristiqueOuvrageList
        restCaracteristiqueOuvrageMockMvc.perform(get("/api/caracteristique-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caracteristiqueOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].unite").value(hasItem(DEFAULT_UNITE)));
    }
    
    @Test
    @Transactional
    public void getCaracteristiqueOuvrage() throws Exception {
        // Initialize the database
        caracteristiqueOuvrageRepository.saveAndFlush(caracteristiqueOuvrage);

        // Get the caracteristiqueOuvrage
        restCaracteristiqueOuvrageMockMvc.perform(get("/api/caracteristique-ouvrages/{id}", caracteristiqueOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caracteristiqueOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.unite").value(DEFAULT_UNITE));
    }
    @Test
    @Transactional
    public void getNonExistingCaracteristiqueOuvrage() throws Exception {
        // Get the caracteristiqueOuvrage
        restCaracteristiqueOuvrageMockMvc.perform(get("/api/caracteristique-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaracteristiqueOuvrage() throws Exception {
        // Initialize the database
        caracteristiqueOuvrageRepository.saveAndFlush(caracteristiqueOuvrage);

        int databaseSizeBeforeUpdate = caracteristiqueOuvrageRepository.findAll().size();

        // Update the caracteristiqueOuvrage
        CaracteristiqueOuvrage updatedCaracteristiqueOuvrage = caracteristiqueOuvrageRepository.findById(caracteristiqueOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedCaracteristiqueOuvrage are not directly saved in db
        em.detach(updatedCaracteristiqueOuvrage);
        updatedCaracteristiqueOuvrage
            .libelle(UPDATED_LIBELLE)
            .unite(UPDATED_UNITE);
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO = caracteristiqueOuvrageMapper.toDto(updatedCaracteristiqueOuvrage);

        restCaracteristiqueOuvrageMockMvc.perform(put("/api/caracteristique-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the CaracteristiqueOuvrage in the database
        List<CaracteristiqueOuvrage> caracteristiqueOuvrageList = caracteristiqueOuvrageRepository.findAll();
        assertThat(caracteristiqueOuvrageList).hasSize(databaseSizeBeforeUpdate);
        CaracteristiqueOuvrage testCaracteristiqueOuvrage = caracteristiqueOuvrageList.get(caracteristiqueOuvrageList.size() - 1);
        assertThat(testCaracteristiqueOuvrage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCaracteristiqueOuvrage.getUnite()).isEqualTo(UPDATED_UNITE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaracteristiqueOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = caracteristiqueOuvrageRepository.findAll().size();

        // Create the CaracteristiqueOuvrage
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO = caracteristiqueOuvrageMapper.toDto(caracteristiqueOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaracteristiqueOuvrageMockMvc.perform(put("/api/caracteristique-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristiqueOuvrage in the database
        List<CaracteristiqueOuvrage> caracteristiqueOuvrageList = caracteristiqueOuvrageRepository.findAll();
        assertThat(caracteristiqueOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaracteristiqueOuvrage() throws Exception {
        // Initialize the database
        caracteristiqueOuvrageRepository.saveAndFlush(caracteristiqueOuvrage);

        int databaseSizeBeforeDelete = caracteristiqueOuvrageRepository.findAll().size();

        // Delete the caracteristiqueOuvrage
        restCaracteristiqueOuvrageMockMvc.perform(delete("/api/caracteristique-ouvrages/{id}", caracteristiqueOuvrage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaracteristiqueOuvrage> caracteristiqueOuvrageList = caracteristiqueOuvrageRepository.findAll();
        assertThat(caracteristiqueOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
