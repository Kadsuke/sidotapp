package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeHabitation;
import bf.onea.repository.TypeHabitationRepository;
import bf.onea.service.TypeHabitationService;
import bf.onea.service.dto.TypeHabitationDTO;
import bf.onea.service.mapper.TypeHabitationMapper;

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
 * Integration tests for the {@link TypeHabitationResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeHabitationResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeHabitationRepository typeHabitationRepository;

    @Autowired
    private TypeHabitationMapper typeHabitationMapper;

    @Autowired
    private TypeHabitationService typeHabitationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeHabitationMockMvc;

    private TypeHabitation typeHabitation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeHabitation createEntity(EntityManager em) {
        TypeHabitation typeHabitation = new TypeHabitation()
            .libelle(DEFAULT_LIBELLE);
        return typeHabitation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeHabitation createUpdatedEntity(EntityManager em) {
        TypeHabitation typeHabitation = new TypeHabitation()
            .libelle(UPDATED_LIBELLE);
        return typeHabitation;
    }

    @BeforeEach
    public void initTest() {
        typeHabitation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeHabitation() throws Exception {
        int databaseSizeBeforeCreate = typeHabitationRepository.findAll().size();
        // Create the TypeHabitation
        TypeHabitationDTO typeHabitationDTO = typeHabitationMapper.toDto(typeHabitation);
        restTypeHabitationMockMvc.perform(post("/api/type-habitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeHabitationDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeHabitation in the database
        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeCreate + 1);
        TypeHabitation testTypeHabitation = typeHabitationList.get(typeHabitationList.size() - 1);
        assertThat(testTypeHabitation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeHabitationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeHabitationRepository.findAll().size();

        // Create the TypeHabitation with an existing ID
        typeHabitation.setId(1L);
        TypeHabitationDTO typeHabitationDTO = typeHabitationMapper.toDto(typeHabitation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeHabitationMockMvc.perform(post("/api/type-habitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeHabitationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeHabitation in the database
        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeHabitationRepository.findAll().size();
        // set the field null
        typeHabitation.setLibelle(null);

        // Create the TypeHabitation, which fails.
        TypeHabitationDTO typeHabitationDTO = typeHabitationMapper.toDto(typeHabitation);


        restTypeHabitationMockMvc.perform(post("/api/type-habitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeHabitationDTO)))
            .andExpect(status().isBadRequest());

        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeHabitations() throws Exception {
        // Initialize the database
        typeHabitationRepository.saveAndFlush(typeHabitation);

        // Get all the typeHabitationList
        restTypeHabitationMockMvc.perform(get("/api/type-habitations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeHabitation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeHabitation() throws Exception {
        // Initialize the database
        typeHabitationRepository.saveAndFlush(typeHabitation);

        // Get the typeHabitation
        restTypeHabitationMockMvc.perform(get("/api/type-habitations/{id}", typeHabitation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeHabitation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeHabitation() throws Exception {
        // Get the typeHabitation
        restTypeHabitationMockMvc.perform(get("/api/type-habitations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeHabitation() throws Exception {
        // Initialize the database
        typeHabitationRepository.saveAndFlush(typeHabitation);

        int databaseSizeBeforeUpdate = typeHabitationRepository.findAll().size();

        // Update the typeHabitation
        TypeHabitation updatedTypeHabitation = typeHabitationRepository.findById(typeHabitation.getId()).get();
        // Disconnect from session so that the updates on updatedTypeHabitation are not directly saved in db
        em.detach(updatedTypeHabitation);
        updatedTypeHabitation
            .libelle(UPDATED_LIBELLE);
        TypeHabitationDTO typeHabitationDTO = typeHabitationMapper.toDto(updatedTypeHabitation);

        restTypeHabitationMockMvc.perform(put("/api/type-habitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeHabitationDTO)))
            .andExpect(status().isOk());

        // Validate the TypeHabitation in the database
        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeUpdate);
        TypeHabitation testTypeHabitation = typeHabitationList.get(typeHabitationList.size() - 1);
        assertThat(testTypeHabitation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeHabitation() throws Exception {
        int databaseSizeBeforeUpdate = typeHabitationRepository.findAll().size();

        // Create the TypeHabitation
        TypeHabitationDTO typeHabitationDTO = typeHabitationMapper.toDto(typeHabitation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeHabitationMockMvc.perform(put("/api/type-habitations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeHabitationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeHabitation in the database
        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeHabitation() throws Exception {
        // Initialize the database
        typeHabitationRepository.saveAndFlush(typeHabitation);

        int databaseSizeBeforeDelete = typeHabitationRepository.findAll().size();

        // Delete the typeHabitation
        restTypeHabitationMockMvc.perform(delete("/api/type-habitations/{id}", typeHabitation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeHabitation> typeHabitationList = typeHabitationRepository.findAll();
        assertThat(typeHabitationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
