package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.TypeBeneficiaire;
import bf.onea.repository.TypeBeneficiaireRepository;
import bf.onea.service.TypeBeneficiaireService;
import bf.onea.service.dto.TypeBeneficiaireDTO;
import bf.onea.service.mapper.TypeBeneficiaireMapper;

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
 * Integration tests for the {@link TypeBeneficiaireResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeBeneficiaireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeBeneficiaireRepository typeBeneficiaireRepository;

    @Autowired
    private TypeBeneficiaireMapper typeBeneficiaireMapper;

    @Autowired
    private TypeBeneficiaireService typeBeneficiaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeBeneficiaireMockMvc;

    private TypeBeneficiaire typeBeneficiaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeBeneficiaire createEntity(EntityManager em) {
        TypeBeneficiaire typeBeneficiaire = new TypeBeneficiaire()
            .libelle(DEFAULT_LIBELLE);
        return typeBeneficiaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeBeneficiaire createUpdatedEntity(EntityManager em) {
        TypeBeneficiaire typeBeneficiaire = new TypeBeneficiaire()
            .libelle(UPDATED_LIBELLE);
        return typeBeneficiaire;
    }

    @BeforeEach
    public void initTest() {
        typeBeneficiaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeBeneficiaire() throws Exception {
        int databaseSizeBeforeCreate = typeBeneficiaireRepository.findAll().size();
        // Create the TypeBeneficiaire
        TypeBeneficiaireDTO typeBeneficiaireDTO = typeBeneficiaireMapper.toDto(typeBeneficiaire);
        restTypeBeneficiaireMockMvc.perform(post("/api/type-beneficiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeBeneficiaireDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeBeneficiaire in the database
        List<TypeBeneficiaire> typeBeneficiaireList = typeBeneficiaireRepository.findAll();
        assertThat(typeBeneficiaireList).hasSize(databaseSizeBeforeCreate + 1);
        TypeBeneficiaire testTypeBeneficiaire = typeBeneficiaireList.get(typeBeneficiaireList.size() - 1);
        assertThat(testTypeBeneficiaire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeBeneficiaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeBeneficiaireRepository.findAll().size();

        // Create the TypeBeneficiaire with an existing ID
        typeBeneficiaire.setId(1L);
        TypeBeneficiaireDTO typeBeneficiaireDTO = typeBeneficiaireMapper.toDto(typeBeneficiaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeBeneficiaireMockMvc.perform(post("/api/type-beneficiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeBeneficiaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeBeneficiaire in the database
        List<TypeBeneficiaire> typeBeneficiaireList = typeBeneficiaireRepository.findAll();
        assertThat(typeBeneficiaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeBeneficiaires() throws Exception {
        // Initialize the database
        typeBeneficiaireRepository.saveAndFlush(typeBeneficiaire);

        // Get all the typeBeneficiaireList
        restTypeBeneficiaireMockMvc.perform(get("/api/type-beneficiaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeBeneficiaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTypeBeneficiaire() throws Exception {
        // Initialize the database
        typeBeneficiaireRepository.saveAndFlush(typeBeneficiaire);

        // Get the typeBeneficiaire
        restTypeBeneficiaireMockMvc.perform(get("/api/type-beneficiaires/{id}", typeBeneficiaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeBeneficiaire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeBeneficiaire() throws Exception {
        // Get the typeBeneficiaire
        restTypeBeneficiaireMockMvc.perform(get("/api/type-beneficiaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeBeneficiaire() throws Exception {
        // Initialize the database
        typeBeneficiaireRepository.saveAndFlush(typeBeneficiaire);

        int databaseSizeBeforeUpdate = typeBeneficiaireRepository.findAll().size();

        // Update the typeBeneficiaire
        TypeBeneficiaire updatedTypeBeneficiaire = typeBeneficiaireRepository.findById(typeBeneficiaire.getId()).get();
        // Disconnect from session so that the updates on updatedTypeBeneficiaire are not directly saved in db
        em.detach(updatedTypeBeneficiaire);
        updatedTypeBeneficiaire
            .libelle(UPDATED_LIBELLE);
        TypeBeneficiaireDTO typeBeneficiaireDTO = typeBeneficiaireMapper.toDto(updatedTypeBeneficiaire);

        restTypeBeneficiaireMockMvc.perform(put("/api/type-beneficiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeBeneficiaireDTO)))
            .andExpect(status().isOk());

        // Validate the TypeBeneficiaire in the database
        List<TypeBeneficiaire> typeBeneficiaireList = typeBeneficiaireRepository.findAll();
        assertThat(typeBeneficiaireList).hasSize(databaseSizeBeforeUpdate);
        TypeBeneficiaire testTypeBeneficiaire = typeBeneficiaireList.get(typeBeneficiaireList.size() - 1);
        assertThat(testTypeBeneficiaire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeBeneficiaire() throws Exception {
        int databaseSizeBeforeUpdate = typeBeneficiaireRepository.findAll().size();

        // Create the TypeBeneficiaire
        TypeBeneficiaireDTO typeBeneficiaireDTO = typeBeneficiaireMapper.toDto(typeBeneficiaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeBeneficiaireMockMvc.perform(put("/api/type-beneficiaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeBeneficiaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeBeneficiaire in the database
        List<TypeBeneficiaire> typeBeneficiaireList = typeBeneficiaireRepository.findAll();
        assertThat(typeBeneficiaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeBeneficiaire() throws Exception {
        // Initialize the database
        typeBeneficiaireRepository.saveAndFlush(typeBeneficiaire);

        int databaseSizeBeforeDelete = typeBeneficiaireRepository.findAll().size();

        // Delete the typeBeneficiaire
        restTypeBeneficiaireMockMvc.perform(delete("/api/type-beneficiaires/{id}", typeBeneficiaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeBeneficiaire> typeBeneficiaireList = typeBeneficiaireRepository.findAll();
        assertThat(typeBeneficiaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
