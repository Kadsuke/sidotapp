package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.RefPeriodicite;
import bf.onea.repository.RefPeriodiciteRepository;
import bf.onea.service.RefPeriodiciteService;
import bf.onea.service.dto.RefPeriodiciteDTO;
import bf.onea.service.mapper.RefPeriodiciteMapper;

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
 * Integration tests for the {@link RefPeriodiciteResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RefPeriodiciteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefPeriodiciteRepository refPeriodiciteRepository;

    @Autowired
    private RefPeriodiciteMapper refPeriodiciteMapper;

    @Autowired
    private RefPeriodiciteService refPeriodiciteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefPeriodiciteMockMvc;

    private RefPeriodicite refPeriodicite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefPeriodicite createEntity(EntityManager em) {
        RefPeriodicite refPeriodicite = new RefPeriodicite()
            .libelle(DEFAULT_LIBELLE);
        return refPeriodicite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefPeriodicite createUpdatedEntity(EntityManager em) {
        RefPeriodicite refPeriodicite = new RefPeriodicite()
            .libelle(UPDATED_LIBELLE);
        return refPeriodicite;
    }

    @BeforeEach
    public void initTest() {
        refPeriodicite = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefPeriodicite() throws Exception {
        int databaseSizeBeforeCreate = refPeriodiciteRepository.findAll().size();
        // Create the RefPeriodicite
        RefPeriodiciteDTO refPeriodiciteDTO = refPeriodiciteMapper.toDto(refPeriodicite);
        restRefPeriodiciteMockMvc.perform(post("/api/ref-periodicites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refPeriodiciteDTO)))
            .andExpect(status().isCreated());

        // Validate the RefPeriodicite in the database
        List<RefPeriodicite> refPeriodiciteList = refPeriodiciteRepository.findAll();
        assertThat(refPeriodiciteList).hasSize(databaseSizeBeforeCreate + 1);
        RefPeriodicite testRefPeriodicite = refPeriodiciteList.get(refPeriodiciteList.size() - 1);
        assertThat(testRefPeriodicite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefPeriodiciteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refPeriodiciteRepository.findAll().size();

        // Create the RefPeriodicite with an existing ID
        refPeriodicite.setId(1L);
        RefPeriodiciteDTO refPeriodiciteDTO = refPeriodiciteMapper.toDto(refPeriodicite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefPeriodiciteMockMvc.perform(post("/api/ref-periodicites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refPeriodiciteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefPeriodicite in the database
        List<RefPeriodicite> refPeriodiciteList = refPeriodiciteRepository.findAll();
        assertThat(refPeriodiciteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRefPeriodicites() throws Exception {
        // Initialize the database
        refPeriodiciteRepository.saveAndFlush(refPeriodicite);

        // Get all the refPeriodiciteList
        restRefPeriodiciteMockMvc.perform(get("/api/ref-periodicites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refPeriodicite.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRefPeriodicite() throws Exception {
        // Initialize the database
        refPeriodiciteRepository.saveAndFlush(refPeriodicite);

        // Get the refPeriodicite
        restRefPeriodiciteMockMvc.perform(get("/api/ref-periodicites/{id}", refPeriodicite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refPeriodicite.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRefPeriodicite() throws Exception {
        // Get the refPeriodicite
        restRefPeriodiciteMockMvc.perform(get("/api/ref-periodicites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefPeriodicite() throws Exception {
        // Initialize the database
        refPeriodiciteRepository.saveAndFlush(refPeriodicite);

        int databaseSizeBeforeUpdate = refPeriodiciteRepository.findAll().size();

        // Update the refPeriodicite
        RefPeriodicite updatedRefPeriodicite = refPeriodiciteRepository.findById(refPeriodicite.getId()).get();
        // Disconnect from session so that the updates on updatedRefPeriodicite are not directly saved in db
        em.detach(updatedRefPeriodicite);
        updatedRefPeriodicite
            .libelle(UPDATED_LIBELLE);
        RefPeriodiciteDTO refPeriodiciteDTO = refPeriodiciteMapper.toDto(updatedRefPeriodicite);

        restRefPeriodiciteMockMvc.perform(put("/api/ref-periodicites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refPeriodiciteDTO)))
            .andExpect(status().isOk());

        // Validate the RefPeriodicite in the database
        List<RefPeriodicite> refPeriodiciteList = refPeriodiciteRepository.findAll();
        assertThat(refPeriodiciteList).hasSize(databaseSizeBeforeUpdate);
        RefPeriodicite testRefPeriodicite = refPeriodiciteList.get(refPeriodiciteList.size() - 1);
        assertThat(testRefPeriodicite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefPeriodicite() throws Exception {
        int databaseSizeBeforeUpdate = refPeriodiciteRepository.findAll().size();

        // Create the RefPeriodicite
        RefPeriodiciteDTO refPeriodiciteDTO = refPeriodiciteMapper.toDto(refPeriodicite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefPeriodiciteMockMvc.perform(put("/api/ref-periodicites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refPeriodiciteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefPeriodicite in the database
        List<RefPeriodicite> refPeriodiciteList = refPeriodiciteRepository.findAll();
        assertThat(refPeriodiciteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefPeriodicite() throws Exception {
        // Initialize the database
        refPeriodiciteRepository.saveAndFlush(refPeriodicite);

        int databaseSizeBeforeDelete = refPeriodiciteRepository.findAll().size();

        // Delete the refPeriodicite
        restRefPeriodiciteMockMvc.perform(delete("/api/ref-periodicites/{id}", refPeriodicite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefPeriodicite> refPeriodiciteList = refPeriodiciteRepository.findAll();
        assertThat(refPeriodiciteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
