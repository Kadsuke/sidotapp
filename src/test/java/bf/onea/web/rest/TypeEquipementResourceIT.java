package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeEquipement;
import bf.onea.repository.TypeEquipementRepository;
import bf.onea.service.TypeEquipementService;
import bf.onea.service.dto.TypeEquipementDTO;
import bf.onea.service.mapper.TypeEquipementMapper;

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
 * Integration tests for the {@link TypeEquipementResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeEquipementResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeEquipementRepository typeEquipementRepository;

    @Autowired
    private TypeEquipementMapper typeEquipementMapper;

    @Autowired
    private TypeEquipementService typeEquipementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeEquipementMockMvc;

    private TypeEquipement typeEquipement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeEquipement createEntity(EntityManager em) {
        TypeEquipement typeEquipement = new TypeEquipement()
            .libelle(DEFAULT_LIBELLE);
        return typeEquipement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeEquipement createUpdatedEntity(EntityManager em) {
        TypeEquipement typeEquipement = new TypeEquipement()
            .libelle(UPDATED_LIBELLE);
        return typeEquipement;
    }

    @BeforeEach
    public void initTest() {
        typeEquipement = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeEquipement() throws Exception {
        int databaseSizeBeforeCreate = typeEquipementRepository.findAll().size();
        // Create the TypeEquipement
        TypeEquipementDTO typeEquipementDTO = typeEquipementMapper.toDto(typeEquipement);
        restTypeEquipementMockMvc.perform(post("/api/type-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEquipementDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeEquipement in the database
        List<TypeEquipement> typeEquipementList = typeEquipementRepository.findAll();
        assertThat(typeEquipementList).hasSize(databaseSizeBeforeCreate + 1);
        TypeEquipement testTypeEquipement = typeEquipementList.get(typeEquipementList.size() - 1);
        assertThat(testTypeEquipement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeEquipementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeEquipementRepository.findAll().size();

        // Create the TypeEquipement with an existing ID
        typeEquipement.setId(1L);
        TypeEquipementDTO typeEquipementDTO = typeEquipementMapper.toDto(typeEquipement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeEquipementMockMvc.perform(post("/api/type-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEquipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEquipement in the database
        List<TypeEquipement> typeEquipementList = typeEquipementRepository.findAll();
        assertThat(typeEquipementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeEquipements() throws Exception {
        // Initialize the database
        typeEquipementRepository.saveAndFlush(typeEquipement);

        // Get all the typeEquipementList
        restTypeEquipementMockMvc.perform(get("/api/type-equipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeEquipement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeEquipement() throws Exception {
        // Initialize the database
        typeEquipementRepository.saveAndFlush(typeEquipement);

        // Get the typeEquipement
        restTypeEquipementMockMvc.perform(get("/api/type-equipements/{id}", typeEquipement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeEquipement.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeEquipement() throws Exception {
        // Get the typeEquipement
        restTypeEquipementMockMvc.perform(get("/api/type-equipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeEquipement() throws Exception {
        // Initialize the database
        typeEquipementRepository.saveAndFlush(typeEquipement);

        int databaseSizeBeforeUpdate = typeEquipementRepository.findAll().size();

        // Update the typeEquipement
        TypeEquipement updatedTypeEquipement = typeEquipementRepository.findById(typeEquipement.getId()).get();
        // Disconnect from session so that the updates on updatedTypeEquipement are not directly saved in db
        em.detach(updatedTypeEquipement);
        updatedTypeEquipement
            .libelle(UPDATED_LIBELLE);
        TypeEquipementDTO typeEquipementDTO = typeEquipementMapper.toDto(updatedTypeEquipement);

        restTypeEquipementMockMvc.perform(put("/api/type-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEquipementDTO)))
            .andExpect(status().isOk());

        // Validate the TypeEquipement in the database
        List<TypeEquipement> typeEquipementList = typeEquipementRepository.findAll();
        assertThat(typeEquipementList).hasSize(databaseSizeBeforeUpdate);
        TypeEquipement testTypeEquipement = typeEquipementList.get(typeEquipementList.size() - 1);
        assertThat(testTypeEquipement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeEquipement() throws Exception {
        int databaseSizeBeforeUpdate = typeEquipementRepository.findAll().size();

        // Create the TypeEquipement
        TypeEquipementDTO typeEquipementDTO = typeEquipementMapper.toDto(typeEquipement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeEquipementMockMvc.perform(put("/api/type-equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeEquipementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeEquipement in the database
        List<TypeEquipement> typeEquipementList = typeEquipementRepository.findAll();
        assertThat(typeEquipementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeEquipement() throws Exception {
        // Initialize the database
        typeEquipementRepository.saveAndFlush(typeEquipement);

        int databaseSizeBeforeDelete = typeEquipementRepository.findAll().size();

        // Delete the typeEquipement
        restTypeEquipementMockMvc.perform(delete("/api/type-equipements/{id}", typeEquipement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeEquipement> typeEquipementList = typeEquipementRepository.findAll();
        assertThat(typeEquipementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
