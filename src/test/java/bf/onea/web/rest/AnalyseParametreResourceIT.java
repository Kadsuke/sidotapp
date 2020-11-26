package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.AnalyseParametre;
import bf.onea.repository.AnalyseParametreRepository;
import bf.onea.service.AnalyseParametreService;
import bf.onea.service.dto.AnalyseParametreDTO;
import bf.onea.service.mapper.AnalyseParametreMapper;

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
 * Integration tests for the {@link AnalyseParametreResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AnalyseParametreResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private AnalyseParametreRepository analyseParametreRepository;

    @Autowired
    private AnalyseParametreMapper analyseParametreMapper;

    @Autowired
    private AnalyseParametreService analyseParametreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnalyseParametreMockMvc;

    private AnalyseParametre analyseParametre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalyseParametre createEntity(EntityManager em) {
        AnalyseParametre analyseParametre = new AnalyseParametre()
            .libelle(DEFAULT_LIBELLE);
        return analyseParametre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnalyseParametre createUpdatedEntity(EntityManager em) {
        AnalyseParametre analyseParametre = new AnalyseParametre()
            .libelle(UPDATED_LIBELLE);
        return analyseParametre;
    }

    @BeforeEach
    public void initTest() {
        analyseParametre = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnalyseParametre() throws Exception {
        int databaseSizeBeforeCreate = analyseParametreRepository.findAll().size();
        // Create the AnalyseParametre
        AnalyseParametreDTO analyseParametreDTO = analyseParametreMapper.toDto(analyseParametre);
        restAnalyseParametreMockMvc.perform(post("/api/analyse-parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseParametreDTO)))
            .andExpect(status().isCreated());

        // Validate the AnalyseParametre in the database
        List<AnalyseParametre> analyseParametreList = analyseParametreRepository.findAll();
        assertThat(analyseParametreList).hasSize(databaseSizeBeforeCreate + 1);
        AnalyseParametre testAnalyseParametre = analyseParametreList.get(analyseParametreList.size() - 1);
        assertThat(testAnalyseParametre.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createAnalyseParametreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = analyseParametreRepository.findAll().size();

        // Create the AnalyseParametre with an existing ID
        analyseParametre.setId(1L);
        AnalyseParametreDTO analyseParametreDTO = analyseParametreMapper.toDto(analyseParametre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnalyseParametreMockMvc.perform(post("/api/analyse-parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseParametreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnalyseParametre in the database
        List<AnalyseParametre> analyseParametreList = analyseParametreRepository.findAll();
        assertThat(analyseParametreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAnalyseParametres() throws Exception {
        // Initialize the database
        analyseParametreRepository.saveAndFlush(analyseParametre);

        // Get all the analyseParametreList
        restAnalyseParametreMockMvc.perform(get("/api/analyse-parametres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(analyseParametre.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getAnalyseParametre() throws Exception {
        // Initialize the database
        analyseParametreRepository.saveAndFlush(analyseParametre);

        // Get the analyseParametre
        restAnalyseParametreMockMvc.perform(get("/api/analyse-parametres/{id}", analyseParametre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(analyseParametre.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingAnalyseParametre() throws Exception {
        // Get the analyseParametre
        restAnalyseParametreMockMvc.perform(get("/api/analyse-parametres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnalyseParametre() throws Exception {
        // Initialize the database
        analyseParametreRepository.saveAndFlush(analyseParametre);

        int databaseSizeBeforeUpdate = analyseParametreRepository.findAll().size();

        // Update the analyseParametre
        AnalyseParametre updatedAnalyseParametre = analyseParametreRepository.findById(analyseParametre.getId()).get();
        // Disconnect from session so that the updates on updatedAnalyseParametre are not directly saved in db
        em.detach(updatedAnalyseParametre);
        updatedAnalyseParametre
            .libelle(UPDATED_LIBELLE);
        AnalyseParametreDTO analyseParametreDTO = analyseParametreMapper.toDto(updatedAnalyseParametre);

        restAnalyseParametreMockMvc.perform(put("/api/analyse-parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseParametreDTO)))
            .andExpect(status().isOk());

        // Validate the AnalyseParametre in the database
        List<AnalyseParametre> analyseParametreList = analyseParametreRepository.findAll();
        assertThat(analyseParametreList).hasSize(databaseSizeBeforeUpdate);
        AnalyseParametre testAnalyseParametre = analyseParametreList.get(analyseParametreList.size() - 1);
        assertThat(testAnalyseParametre.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingAnalyseParametre() throws Exception {
        int databaseSizeBeforeUpdate = analyseParametreRepository.findAll().size();

        // Create the AnalyseParametre
        AnalyseParametreDTO analyseParametreDTO = analyseParametreMapper.toDto(analyseParametre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnalyseParametreMockMvc.perform(put("/api/analyse-parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(analyseParametreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnalyseParametre in the database
        List<AnalyseParametre> analyseParametreList = analyseParametreRepository.findAll();
        assertThat(analyseParametreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnalyseParametre() throws Exception {
        // Initialize the database
        analyseParametreRepository.saveAndFlush(analyseParametre);

        int databaseSizeBeforeDelete = analyseParametreRepository.findAll().size();

        // Delete the analyseParametre
        restAnalyseParametreMockMvc.perform(delete("/api/analyse-parametres/{id}", analyseParametre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnalyseParametre> analyseParametreList = analyseParametreRepository.findAll();
        assertThat(analyseParametreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
