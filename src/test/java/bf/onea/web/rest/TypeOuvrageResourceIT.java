package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeOuvrage;
import bf.onea.repository.TypeOuvrageRepository;
import bf.onea.service.TypeOuvrageService;
import bf.onea.service.dto.TypeOuvrageDTO;
import bf.onea.service.mapper.TypeOuvrageMapper;

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
 * Integration tests for the {@link TypeOuvrageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeOuvrageResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeOuvrageRepository typeOuvrageRepository;

    @Autowired
    private TypeOuvrageMapper typeOuvrageMapper;

    @Autowired
    private TypeOuvrageService typeOuvrageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeOuvrageMockMvc;

    private TypeOuvrage typeOuvrage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeOuvrage createEntity(EntityManager em) {
        TypeOuvrage typeOuvrage = new TypeOuvrage()
            .libelle(DEFAULT_LIBELLE);
        return typeOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeOuvrage createUpdatedEntity(EntityManager em) {
        TypeOuvrage typeOuvrage = new TypeOuvrage()
            .libelle(UPDATED_LIBELLE);
        return typeOuvrage;
    }

    @BeforeEach
    public void initTest() {
        typeOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeOuvrage() throws Exception {
        int databaseSizeBeforeCreate = typeOuvrageRepository.findAll().size();
        // Create the TypeOuvrage
        TypeOuvrageDTO typeOuvrageDTO = typeOuvrageMapper.toDto(typeOuvrage);
        restTypeOuvrageMockMvc.perform(post("/api/type-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeOuvrage in the database
        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        TypeOuvrage testTypeOuvrage = typeOuvrageList.get(typeOuvrageList.size() - 1);
        assertThat(testTypeOuvrage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeOuvrageRepository.findAll().size();

        // Create the TypeOuvrage with an existing ID
        typeOuvrage.setId(1L);
        TypeOuvrageDTO typeOuvrageDTO = typeOuvrageMapper.toDto(typeOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeOuvrageMockMvc.perform(post("/api/type-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOuvrage in the database
        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeOuvrageRepository.findAll().size();
        // set the field null
        typeOuvrage.setLibelle(null);

        // Create the TypeOuvrage, which fails.
        TypeOuvrageDTO typeOuvrageDTO = typeOuvrageMapper.toDto(typeOuvrage);


        restTypeOuvrageMockMvc.perform(post("/api/type-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOuvrageDTO)))
            .andExpect(status().isBadRequest());

        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeOuvrages() throws Exception {
        // Initialize the database
        typeOuvrageRepository.saveAndFlush(typeOuvrage);

        // Get all the typeOuvrageList
        restTypeOuvrageMockMvc.perform(get("/api/type-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeOuvrage() throws Exception {
        // Initialize the database
        typeOuvrageRepository.saveAndFlush(typeOuvrage);

        // Get the typeOuvrage
        restTypeOuvrageMockMvc.perform(get("/api/type-ouvrages/{id}", typeOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeOuvrage() throws Exception {
        // Get the typeOuvrage
        restTypeOuvrageMockMvc.perform(get("/api/type-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeOuvrage() throws Exception {
        // Initialize the database
        typeOuvrageRepository.saveAndFlush(typeOuvrage);

        int databaseSizeBeforeUpdate = typeOuvrageRepository.findAll().size();

        // Update the typeOuvrage
        TypeOuvrage updatedTypeOuvrage = typeOuvrageRepository.findById(typeOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedTypeOuvrage are not directly saved in db
        em.detach(updatedTypeOuvrage);
        updatedTypeOuvrage
            .libelle(UPDATED_LIBELLE);
        TypeOuvrageDTO typeOuvrageDTO = typeOuvrageMapper.toDto(updatedTypeOuvrage);

        restTypeOuvrageMockMvc.perform(put("/api/type-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the TypeOuvrage in the database
        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeUpdate);
        TypeOuvrage testTypeOuvrage = typeOuvrageList.get(typeOuvrageList.size() - 1);
        assertThat(testTypeOuvrage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = typeOuvrageRepository.findAll().size();

        // Create the TypeOuvrage
        TypeOuvrageDTO typeOuvrageDTO = typeOuvrageMapper.toDto(typeOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeOuvrageMockMvc.perform(put("/api/type-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeOuvrage in the database
        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeOuvrage() throws Exception {
        // Initialize the database
        typeOuvrageRepository.saveAndFlush(typeOuvrage);

        int databaseSizeBeforeDelete = typeOuvrageRepository.findAll().size();

        // Delete the typeOuvrage
        restTypeOuvrageMockMvc.perform(delete("/api/type-ouvrages/{id}", typeOuvrage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeOuvrage> typeOuvrageList = typeOuvrageRepository.findAll();
        assertThat(typeOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
