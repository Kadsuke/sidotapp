package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.RefMois;
import bf.onea.repository.RefMoisRepository;
import bf.onea.service.RefMoisService;
import bf.onea.service.dto.RefMoisDTO;
import bf.onea.service.mapper.RefMoisMapper;

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
 * Integration tests for the {@link RefMoisResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RefMoisResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefMoisRepository refMoisRepository;

    @Autowired
    private RefMoisMapper refMoisMapper;

    @Autowired
    private RefMoisService refMoisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefMoisMockMvc;

    private RefMois refMois;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefMois createEntity(EntityManager em) {
        RefMois refMois = new RefMois()
            .libelle(DEFAULT_LIBELLE);
        return refMois;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefMois createUpdatedEntity(EntityManager em) {
        RefMois refMois = new RefMois()
            .libelle(UPDATED_LIBELLE);
        return refMois;
    }

    @BeforeEach
    public void initTest() {
        refMois = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefMois() throws Exception {
        int databaseSizeBeforeCreate = refMoisRepository.findAll().size();
        // Create the RefMois
        RefMoisDTO refMoisDTO = refMoisMapper.toDto(refMois);
        restRefMoisMockMvc.perform(post("/api/ref-mois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refMoisDTO)))
            .andExpect(status().isCreated());

        // Validate the RefMois in the database
        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeCreate + 1);
        RefMois testRefMois = refMoisList.get(refMoisList.size() - 1);
        assertThat(testRefMois.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefMoisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refMoisRepository.findAll().size();

        // Create the RefMois with an existing ID
        refMois.setId(1L);
        RefMoisDTO refMoisDTO = refMoisMapper.toDto(refMois);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefMoisMockMvc.perform(post("/api/ref-mois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refMoisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefMois in the database
        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMoisRepository.findAll().size();
        // set the field null
        refMois.setLibelle(null);

        // Create the RefMois, which fails.
        RefMoisDTO refMoisDTO = refMoisMapper.toDto(refMois);


        restRefMoisMockMvc.perform(post("/api/ref-mois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refMoisDTO)))
            .andExpect(status().isBadRequest());

        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefMois() throws Exception {
        // Initialize the database
        refMoisRepository.saveAndFlush(refMois);

        // Get all the refMoisList
        restRefMoisMockMvc.perform(get("/api/ref-mois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refMois.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRefMois() throws Exception {
        // Initialize the database
        refMoisRepository.saveAndFlush(refMois);

        // Get the refMois
        restRefMoisMockMvc.perform(get("/api/ref-mois/{id}", refMois.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refMois.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRefMois() throws Exception {
        // Get the refMois
        restRefMoisMockMvc.perform(get("/api/ref-mois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefMois() throws Exception {
        // Initialize the database
        refMoisRepository.saveAndFlush(refMois);

        int databaseSizeBeforeUpdate = refMoisRepository.findAll().size();

        // Update the refMois
        RefMois updatedRefMois = refMoisRepository.findById(refMois.getId()).get();
        // Disconnect from session so that the updates on updatedRefMois are not directly saved in db
        em.detach(updatedRefMois);
        updatedRefMois
            .libelle(UPDATED_LIBELLE);
        RefMoisDTO refMoisDTO = refMoisMapper.toDto(updatedRefMois);

        restRefMoisMockMvc.perform(put("/api/ref-mois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refMoisDTO)))
            .andExpect(status().isOk());

        // Validate the RefMois in the database
        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeUpdate);
        RefMois testRefMois = refMoisList.get(refMoisList.size() - 1);
        assertThat(testRefMois.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefMois() throws Exception {
        int databaseSizeBeforeUpdate = refMoisRepository.findAll().size();

        // Create the RefMois
        RefMoisDTO refMoisDTO = refMoisMapper.toDto(refMois);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefMoisMockMvc.perform(put("/api/ref-mois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refMoisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefMois in the database
        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefMois() throws Exception {
        // Initialize the database
        refMoisRepository.saveAndFlush(refMois);

        int databaseSizeBeforeDelete = refMoisRepository.findAll().size();

        // Delete the refMois
        restRefMoisMockMvc.perform(delete("/api/ref-mois/{id}", refMois.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefMois> refMoisList = refMoisRepository.findAll();
        assertThat(refMoisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
