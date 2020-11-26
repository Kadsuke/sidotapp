package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeIntervention;
import bf.onea.repository.TypeInterventionRepository;
import bf.onea.service.TypeInterventionService;
import bf.onea.service.dto.TypeInterventionDTO;
import bf.onea.service.mapper.TypeInterventionMapper;

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
 * Integration tests for the {@link TypeInterventionResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeInterventionResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeInterventionRepository typeInterventionRepository;

    @Autowired
    private TypeInterventionMapper typeInterventionMapper;

    @Autowired
    private TypeInterventionService typeInterventionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeInterventionMockMvc;

    private TypeIntervention typeIntervention;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIntervention createEntity(EntityManager em) {
        TypeIntervention typeIntervention = new TypeIntervention()
            .libelle(DEFAULT_LIBELLE);
        return typeIntervention;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIntervention createUpdatedEntity(EntityManager em) {
        TypeIntervention typeIntervention = new TypeIntervention()
            .libelle(UPDATED_LIBELLE);
        return typeIntervention;
    }

    @BeforeEach
    public void initTest() {
        typeIntervention = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeIntervention() throws Exception {
        int databaseSizeBeforeCreate = typeInterventionRepository.findAll().size();
        // Create the TypeIntervention
        TypeInterventionDTO typeInterventionDTO = typeInterventionMapper.toDto(typeIntervention);
        restTypeInterventionMockMvc.perform(post("/api/type-interventions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInterventionDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeIntervention in the database
        List<TypeIntervention> typeInterventionList = typeInterventionRepository.findAll();
        assertThat(typeInterventionList).hasSize(databaseSizeBeforeCreate + 1);
        TypeIntervention testTypeIntervention = typeInterventionList.get(typeInterventionList.size() - 1);
        assertThat(testTypeIntervention.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeInterventionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeInterventionRepository.findAll().size();

        // Create the TypeIntervention with an existing ID
        typeIntervention.setId(1L);
        TypeInterventionDTO typeInterventionDTO = typeInterventionMapper.toDto(typeIntervention);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeInterventionMockMvc.perform(post("/api/type-interventions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInterventionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIntervention in the database
        List<TypeIntervention> typeInterventionList = typeInterventionRepository.findAll();
        assertThat(typeInterventionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeInterventions() throws Exception {
        // Initialize the database
        typeInterventionRepository.saveAndFlush(typeIntervention);

        // Get all the typeInterventionList
        restTypeInterventionMockMvc.perform(get("/api/type-interventions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeIntervention.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeIntervention() throws Exception {
        // Initialize the database
        typeInterventionRepository.saveAndFlush(typeIntervention);

        // Get the typeIntervention
        restTypeInterventionMockMvc.perform(get("/api/type-interventions/{id}", typeIntervention.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeIntervention.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeIntervention() throws Exception {
        // Get the typeIntervention
        restTypeInterventionMockMvc.perform(get("/api/type-interventions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeIntervention() throws Exception {
        // Initialize the database
        typeInterventionRepository.saveAndFlush(typeIntervention);

        int databaseSizeBeforeUpdate = typeInterventionRepository.findAll().size();

        // Update the typeIntervention
        TypeIntervention updatedTypeIntervention = typeInterventionRepository.findById(typeIntervention.getId()).get();
        // Disconnect from session so that the updates on updatedTypeIntervention are not directly saved in db
        em.detach(updatedTypeIntervention);
        updatedTypeIntervention
            .libelle(UPDATED_LIBELLE);
        TypeInterventionDTO typeInterventionDTO = typeInterventionMapper.toDto(updatedTypeIntervention);

        restTypeInterventionMockMvc.perform(put("/api/type-interventions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInterventionDTO)))
            .andExpect(status().isOk());

        // Validate the TypeIntervention in the database
        List<TypeIntervention> typeInterventionList = typeInterventionRepository.findAll();
        assertThat(typeInterventionList).hasSize(databaseSizeBeforeUpdate);
        TypeIntervention testTypeIntervention = typeInterventionList.get(typeInterventionList.size() - 1);
        assertThat(testTypeIntervention.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeIntervention() throws Exception {
        int databaseSizeBeforeUpdate = typeInterventionRepository.findAll().size();

        // Create the TypeIntervention
        TypeInterventionDTO typeInterventionDTO = typeInterventionMapper.toDto(typeIntervention);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeInterventionMockMvc.perform(put("/api/type-interventions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInterventionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIntervention in the database
        List<TypeIntervention> typeInterventionList = typeInterventionRepository.findAll();
        assertThat(typeInterventionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeIntervention() throws Exception {
        // Initialize the database
        typeInterventionRepository.saveAndFlush(typeIntervention);

        int databaseSizeBeforeDelete = typeInterventionRepository.findAll().size();

        // Delete the typeIntervention
        restTypeInterventionMockMvc.perform(delete("/api/type-interventions/{id}", typeIntervention.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeIntervention> typeInterventionList = typeInterventionRepository.findAll();
        assertThat(typeInterventionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
