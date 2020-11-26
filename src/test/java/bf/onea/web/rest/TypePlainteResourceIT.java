package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypePlainte;
import bf.onea.repository.TypePlainteRepository;
import bf.onea.service.TypePlainteService;
import bf.onea.service.dto.TypePlainteDTO;
import bf.onea.service.mapper.TypePlainteMapper;

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
 * Integration tests for the {@link TypePlainteResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypePlainteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypePlainteRepository typePlainteRepository;

    @Autowired
    private TypePlainteMapper typePlainteMapper;

    @Autowired
    private TypePlainteService typePlainteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypePlainteMockMvc;

    private TypePlainte typePlainte;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePlainte createEntity(EntityManager em) {
        TypePlainte typePlainte = new TypePlainte()
            .libelle(DEFAULT_LIBELLE);
        return typePlainte;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePlainte createUpdatedEntity(EntityManager em) {
        TypePlainte typePlainte = new TypePlainte()
            .libelle(UPDATED_LIBELLE);
        return typePlainte;
    }

    @BeforeEach
    public void initTest() {
        typePlainte = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypePlainte() throws Exception {
        int databaseSizeBeforeCreate = typePlainteRepository.findAll().size();
        // Create the TypePlainte
        TypePlainteDTO typePlainteDTO = typePlainteMapper.toDto(typePlainte);
        restTypePlainteMockMvc.perform(post("/api/type-plaintes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePlainteDTO)))
            .andExpect(status().isCreated());

        // Validate the TypePlainte in the database
        List<TypePlainte> typePlainteList = typePlainteRepository.findAll();
        assertThat(typePlainteList).hasSize(databaseSizeBeforeCreate + 1);
        TypePlainte testTypePlainte = typePlainteList.get(typePlainteList.size() - 1);
        assertThat(testTypePlainte.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypePlainteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typePlainteRepository.findAll().size();

        // Create the TypePlainte with an existing ID
        typePlainte.setId(1L);
        TypePlainteDTO typePlainteDTO = typePlainteMapper.toDto(typePlainte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypePlainteMockMvc.perform(post("/api/type-plaintes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePlainteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypePlainte in the database
        List<TypePlainte> typePlainteList = typePlainteRepository.findAll();
        assertThat(typePlainteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypePlaintes() throws Exception {
        // Initialize the database
        typePlainteRepository.saveAndFlush(typePlainte);

        // Get all the typePlainteList
        restTypePlainteMockMvc.perform(get("/api/type-plaintes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePlainte.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypePlainte() throws Exception {
        // Initialize the database
        typePlainteRepository.saveAndFlush(typePlainte);

        // Get the typePlainte
        restTypePlainteMockMvc.perform(get("/api/type-plaintes/{id}", typePlainte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typePlainte.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypePlainte() throws Exception {
        // Get the typePlainte
        restTypePlainteMockMvc.perform(get("/api/type-plaintes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypePlainte() throws Exception {
        // Initialize the database
        typePlainteRepository.saveAndFlush(typePlainte);

        int databaseSizeBeforeUpdate = typePlainteRepository.findAll().size();

        // Update the typePlainte
        TypePlainte updatedTypePlainte = typePlainteRepository.findById(typePlainte.getId()).get();
        // Disconnect from session so that the updates on updatedTypePlainte are not directly saved in db
        em.detach(updatedTypePlainte);
        updatedTypePlainte
            .libelle(UPDATED_LIBELLE);
        TypePlainteDTO typePlainteDTO = typePlainteMapper.toDto(updatedTypePlainte);

        restTypePlainteMockMvc.perform(put("/api/type-plaintes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePlainteDTO)))
            .andExpect(status().isOk());

        // Validate the TypePlainte in the database
        List<TypePlainte> typePlainteList = typePlainteRepository.findAll();
        assertThat(typePlainteList).hasSize(databaseSizeBeforeUpdate);
        TypePlainte testTypePlainte = typePlainteList.get(typePlainteList.size() - 1);
        assertThat(testTypePlainte.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypePlainte() throws Exception {
        int databaseSizeBeforeUpdate = typePlainteRepository.findAll().size();

        // Create the TypePlainte
        TypePlainteDTO typePlainteDTO = typePlainteMapper.toDto(typePlainte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypePlainteMockMvc.perform(put("/api/type-plaintes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typePlainteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypePlainte in the database
        List<TypePlainte> typePlainteList = typePlainteRepository.findAll();
        assertThat(typePlainteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypePlainte() throws Exception {
        // Initialize the database
        typePlainteRepository.saveAndFlush(typePlainte);

        int databaseSizeBeforeDelete = typePlainteRepository.findAll().size();

        // Delete the typePlainte
        restTypePlainteMockMvc.perform(delete("/api/type-plaintes/{id}", typePlainte.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypePlainte> typePlainteList = typePlainteRepository.findAll();
        assertThat(typePlainteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
