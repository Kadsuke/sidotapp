package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeAnalyse;
import bf.onea.repository.TypeAnalyseRepository;
import bf.onea.service.TypeAnalyseService;
import bf.onea.service.dto.TypeAnalyseDTO;
import bf.onea.service.mapper.TypeAnalyseMapper;

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
 * Integration tests for the {@link TypeAnalyseResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeAnalyseResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeAnalyseRepository typeAnalyseRepository;

    @Autowired
    private TypeAnalyseMapper typeAnalyseMapper;

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeAnalyseMockMvc;

    private TypeAnalyse typeAnalyse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAnalyse createEntity(EntityManager em) {
        TypeAnalyse typeAnalyse = new TypeAnalyse()
            .libelle(DEFAULT_LIBELLE);
        return typeAnalyse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAnalyse createUpdatedEntity(EntityManager em) {
        TypeAnalyse typeAnalyse = new TypeAnalyse()
            .libelle(UPDATED_LIBELLE);
        return typeAnalyse;
    }

    @BeforeEach
    public void initTest() {
        typeAnalyse = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeAnalyse() throws Exception {
        int databaseSizeBeforeCreate = typeAnalyseRepository.findAll().size();
        // Create the TypeAnalyse
        TypeAnalyseDTO typeAnalyseDTO = typeAnalyseMapper.toDto(typeAnalyse);
        restTypeAnalyseMockMvc.perform(post("/api/type-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAnalyseDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeAnalyse in the database
        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeCreate + 1);
        TypeAnalyse testTypeAnalyse = typeAnalyseList.get(typeAnalyseList.size() - 1);
        assertThat(testTypeAnalyse.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeAnalyseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeAnalyseRepository.findAll().size();

        // Create the TypeAnalyse with an existing ID
        typeAnalyse.setId(1L);
        TypeAnalyseDTO typeAnalyseDTO = typeAnalyseMapper.toDto(typeAnalyse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAnalyseMockMvc.perform(post("/api/type-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAnalyseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAnalyse in the database
        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeAnalyseRepository.findAll().size();
        // set the field null
        typeAnalyse.setLibelle(null);

        // Create the TypeAnalyse, which fails.
        TypeAnalyseDTO typeAnalyseDTO = typeAnalyseMapper.toDto(typeAnalyse);


        restTypeAnalyseMockMvc.perform(post("/api/type-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAnalyseDTO)))
            .andExpect(status().isBadRequest());

        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeAnalyses() throws Exception {
        // Initialize the database
        typeAnalyseRepository.saveAndFlush(typeAnalyse);

        // Get all the typeAnalyseList
        restTypeAnalyseMockMvc.perform(get("/api/type-analyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAnalyse.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeAnalyse() throws Exception {
        // Initialize the database
        typeAnalyseRepository.saveAndFlush(typeAnalyse);

        // Get the typeAnalyse
        restTypeAnalyseMockMvc.perform(get("/api/type-analyses/{id}", typeAnalyse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeAnalyse.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeAnalyse() throws Exception {
        // Get the typeAnalyse
        restTypeAnalyseMockMvc.perform(get("/api/type-analyses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeAnalyse() throws Exception {
        // Initialize the database
        typeAnalyseRepository.saveAndFlush(typeAnalyse);

        int databaseSizeBeforeUpdate = typeAnalyseRepository.findAll().size();

        // Update the typeAnalyse
        TypeAnalyse updatedTypeAnalyse = typeAnalyseRepository.findById(typeAnalyse.getId()).get();
        // Disconnect from session so that the updates on updatedTypeAnalyse are not directly saved in db
        em.detach(updatedTypeAnalyse);
        updatedTypeAnalyse
            .libelle(UPDATED_LIBELLE);
        TypeAnalyseDTO typeAnalyseDTO = typeAnalyseMapper.toDto(updatedTypeAnalyse);

        restTypeAnalyseMockMvc.perform(put("/api/type-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAnalyseDTO)))
            .andExpect(status().isOk());

        // Validate the TypeAnalyse in the database
        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeUpdate);
        TypeAnalyse testTypeAnalyse = typeAnalyseList.get(typeAnalyseList.size() - 1);
        assertThat(testTypeAnalyse.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeAnalyse() throws Exception {
        int databaseSizeBeforeUpdate = typeAnalyseRepository.findAll().size();

        // Create the TypeAnalyse
        TypeAnalyseDTO typeAnalyseDTO = typeAnalyseMapper.toDto(typeAnalyse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAnalyseMockMvc.perform(put("/api/type-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAnalyseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAnalyse in the database
        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeAnalyse() throws Exception {
        // Initialize the database
        typeAnalyseRepository.saveAndFlush(typeAnalyse);

        int databaseSizeBeforeDelete = typeAnalyseRepository.findAll().size();

        // Delete the typeAnalyse
        restTypeAnalyseMockMvc.perform(delete("/api/type-analyses/{id}", typeAnalyse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeAnalyse> typeAnalyseList = typeAnalyseRepository.findAll();
        assertThat(typeAnalyseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
