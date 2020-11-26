package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.AnalyseSpecialite;
import bf.onea.repository.AnalyseSpecialiteRepository;
import bf.onea.service.AnalyseSpecialiteService;
import bf.onea.service.dto.AnalyseSpecialiteDTO;
import bf.onea.service.mapper.AnalyseSpecialiteMapper;

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
 * Integration tests for the {@link AnalyseSpecialiteResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AnalyseSpecialiteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private AnalyseSpecialiteRepository analyseSpecialiteRepository;

    @Autowired
    private AnalyseSpecialiteMapper analyseSpecialiteMapper;

    @Autowired
    private AnalyseSpecialiteService analyseSpecialiteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnalyseSpecialiteMockMvc;

    private AnalyseSpecialite analyseSpecialite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalyseSpecialite createEntity(EntityManager em) {
        AnalyseSpecialite analyseSpecialite = new AnalyseSpecialite()
            .libelle(DEFAULT_LIBELLE);
        return analyseSpecialite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalyseSpecialite createUpdatedEntity(EntityManager em) {
        AnalyseSpecialite analyseSpecialite = new AnalyseSpecialite()
            .libelle(UPDATED_LIBELLE);
        return analyseSpecialite;
    }

    @BeforeEach
    public void initTest() {
        analyseSpecialite = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnalyseSpecialite() throws Exception {
        int databaseSizeBeforeCreate = analyseSpecialiteRepository.findAll().size();
        // Create the AnalyseSpecialite
        AnalyseSpecialiteDTO analyseSpecialiteDTO = analyseSpecialiteMapper.toDto(analyseSpecialite);
        restAnalyseSpecialiteMockMvc.perform(post("/api/analyse-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseSpecialiteDTO)))
            .andExpect(status().isCreated());

        // Validate the AnalyseSpecialite in the database
        List<AnalyseSpecialite> analyseSpecialiteList = analyseSpecialiteRepository.findAll();
        assertThat(analyseSpecialiteList).hasSize(databaseSizeBeforeCreate + 1);
        AnalyseSpecialite testAnalyseSpecialite = analyseSpecialiteList.get(analyseSpecialiteList.size() - 1);
        assertThat(testAnalyseSpecialite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createAnalyseSpecialiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = analyseSpecialiteRepository.findAll().size();

        // Create the AnalyseSpecialite with an existing ID
        analyseSpecialite.setId(1L);
        AnalyseSpecialiteDTO analyseSpecialiteDTO = analyseSpecialiteMapper.toDto(analyseSpecialite);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnalyseSpecialiteMockMvc.perform(post("/api/analyse-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseSpecialiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnalyseSpecialite in the database
        List<AnalyseSpecialite> analyseSpecialiteList = analyseSpecialiteRepository.findAll();
        assertThat(analyseSpecialiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAnalyseSpecialites() throws Exception {
        // Initialize the database
        analyseSpecialiteRepository.saveAndFlush(analyseSpecialite);

        // Get all the analyseSpecialiteList
        restAnalyseSpecialiteMockMvc.perform(get("/api/analyse-specialites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(analyseSpecialite.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getAnalyseSpecialite() throws Exception {
        // Initialize the database
        analyseSpecialiteRepository.saveAndFlush(analyseSpecialite);

        // Get the analyseSpecialite
        restAnalyseSpecialiteMockMvc.perform(get("/api/analyse-specialites/{id}", analyseSpecialite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(analyseSpecialite.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingAnalyseSpecialite() throws Exception {
        // Get the analyseSpecialite
        restAnalyseSpecialiteMockMvc.perform(get("/api/analyse-specialites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnalyseSpecialite() throws Exception {
        // Initialize the database
        analyseSpecialiteRepository.saveAndFlush(analyseSpecialite);

        int databaseSizeBeforeUpdate = analyseSpecialiteRepository.findAll().size();

        // Update the analyseSpecialite
        AnalyseSpecialite updatedAnalyseSpecialite = analyseSpecialiteRepository.findById(analyseSpecialite.getId()).get();
        // Disconnect from session so that the updates on updatedAnalyseSpecialite are not directly saved in db
        em.detach(updatedAnalyseSpecialite);
        updatedAnalyseSpecialite
            .libelle(UPDATED_LIBELLE);
        AnalyseSpecialiteDTO analyseSpecialiteDTO = analyseSpecialiteMapper.toDto(updatedAnalyseSpecialite);

        restAnalyseSpecialiteMockMvc.perform(put("/api/analyse-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseSpecialiteDTO)))
            .andExpect(status().isOk());

        // Validate the AnalyseSpecialite in the database
        List<AnalyseSpecialite> analyseSpecialiteList = analyseSpecialiteRepository.findAll();
        assertThat(analyseSpecialiteList).hasSize(databaseSizeBeforeUpdate);
        AnalyseSpecialite testAnalyseSpecialite = analyseSpecialiteList.get(analyseSpecialiteList.size() - 1);
        assertThat(testAnalyseSpecialite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingAnalyseSpecialite() throws Exception {
        int databaseSizeBeforeUpdate = analyseSpecialiteRepository.findAll().size();

        // Create the AnalyseSpecialite
        AnalyseSpecialiteDTO analyseSpecialiteDTO = analyseSpecialiteMapper.toDto(analyseSpecialite);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnalyseSpecialiteMockMvc.perform(put("/api/analyse-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseSpecialiteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnalyseSpecialite in the database
        List<AnalyseSpecialite> analyseSpecialiteList = analyseSpecialiteRepository.findAll();
        assertThat(analyseSpecialiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnalyseSpecialite() throws Exception {
        // Initialize the database
        analyseSpecialiteRepository.saveAndFlush(analyseSpecialite);

        int databaseSizeBeforeDelete = analyseSpecialiteRepository.findAll().size();

        // Delete the analyseSpecialite
        restAnalyseSpecialiteMockMvc.perform(delete("/api/analyse-specialites/{id}", analyseSpecialite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnalyseSpecialite> analyseSpecialiteList = analyseSpecialiteRepository.findAll();
        assertThat(analyseSpecialiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
