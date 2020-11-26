package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.SourceApprovEp;
import bf.onea.repository.SourceApprovEpRepository;
import bf.onea.service.SourceApprovEpService;
import bf.onea.service.dto.SourceApprovEpDTO;
import bf.onea.service.mapper.SourceApprovEpMapper;

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
 * Integration tests for the {@link SourceApprovEpResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SourceApprovEpResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private SourceApprovEpRepository sourceApprovEpRepository;

    @Autowired
    private SourceApprovEpMapper sourceApprovEpMapper;

    @Autowired
    private SourceApprovEpService sourceApprovEpService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSourceApprovEpMockMvc;

    private SourceApprovEp sourceApprovEp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SourceApprovEp createEntity(EntityManager em) {
        SourceApprovEp sourceApprovEp = new SourceApprovEp()
            .libelle(DEFAULT_LIBELLE);
        return sourceApprovEp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SourceApprovEp createUpdatedEntity(EntityManager em) {
        SourceApprovEp sourceApprovEp = new SourceApprovEp()
            .libelle(UPDATED_LIBELLE);
        return sourceApprovEp;
    }

    @BeforeEach
    public void initTest() {
        sourceApprovEp = createEntity(em);
    }

    @Test
    @Transactional
    public void createSourceApprovEp() throws Exception {
        int databaseSizeBeforeCreate = sourceApprovEpRepository.findAll().size();
        // Create the SourceApprovEp
        SourceApprovEpDTO sourceApprovEpDTO = sourceApprovEpMapper.toDto(sourceApprovEp);
        restSourceApprovEpMockMvc.perform(post("/api/source-approv-eps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceApprovEpDTO)))
            .andExpect(status().isCreated());

        // Validate the SourceApprovEp in the database
        List<SourceApprovEp> sourceApprovEpList = sourceApprovEpRepository.findAll();
        assertThat(sourceApprovEpList).hasSize(databaseSizeBeforeCreate + 1);
        SourceApprovEp testSourceApprovEp = sourceApprovEpList.get(sourceApprovEpList.size() - 1);
        assertThat(testSourceApprovEp.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createSourceApprovEpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sourceApprovEpRepository.findAll().size();

        // Create the SourceApprovEp with an existing ID
        sourceApprovEp.setId(1L);
        SourceApprovEpDTO sourceApprovEpDTO = sourceApprovEpMapper.toDto(sourceApprovEp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSourceApprovEpMockMvc.perform(post("/api/source-approv-eps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceApprovEpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SourceApprovEp in the database
        List<SourceApprovEp> sourceApprovEpList = sourceApprovEpRepository.findAll();
        assertThat(sourceApprovEpList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSourceApprovEps() throws Exception {
        // Initialize the database
        sourceApprovEpRepository.saveAndFlush(sourceApprovEp);

        // Get all the sourceApprovEpList
        restSourceApprovEpMockMvc.perform(get("/api/source-approv-eps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sourceApprovEp.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getSourceApprovEp() throws Exception {
        // Initialize the database
        sourceApprovEpRepository.saveAndFlush(sourceApprovEp);

        // Get the sourceApprovEp
        restSourceApprovEpMockMvc.perform(get("/api/source-approv-eps/{id}", sourceApprovEp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sourceApprovEp.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingSourceApprovEp() throws Exception {
        // Get the sourceApprovEp
        restSourceApprovEpMockMvc.perform(get("/api/source-approv-eps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSourceApprovEp() throws Exception {
        // Initialize the database
        sourceApprovEpRepository.saveAndFlush(sourceApprovEp);

        int databaseSizeBeforeUpdate = sourceApprovEpRepository.findAll().size();

        // Update the sourceApprovEp
        SourceApprovEp updatedSourceApprovEp = sourceApprovEpRepository.findById(sourceApprovEp.getId()).get();
        // Disconnect from session so that the updates on updatedSourceApprovEp are not directly saved in db
        em.detach(updatedSourceApprovEp);
        updatedSourceApprovEp
            .libelle(UPDATED_LIBELLE);
        SourceApprovEpDTO sourceApprovEpDTO = sourceApprovEpMapper.toDto(updatedSourceApprovEp);

        restSourceApprovEpMockMvc.perform(put("/api/source-approv-eps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceApprovEpDTO)))
            .andExpect(status().isOk());

        // Validate the SourceApprovEp in the database
        List<SourceApprovEp> sourceApprovEpList = sourceApprovEpRepository.findAll();
        assertThat(sourceApprovEpList).hasSize(databaseSizeBeforeUpdate);
        SourceApprovEp testSourceApprovEp = sourceApprovEpList.get(sourceApprovEpList.size() - 1);
        assertThat(testSourceApprovEp.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingSourceApprovEp() throws Exception {
        int databaseSizeBeforeUpdate = sourceApprovEpRepository.findAll().size();

        // Create the SourceApprovEp
        SourceApprovEpDTO sourceApprovEpDTO = sourceApprovEpMapper.toDto(sourceApprovEp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSourceApprovEpMockMvc.perform(put("/api/source-approv-eps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceApprovEpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SourceApprovEp in the database
        List<SourceApprovEp> sourceApprovEpList = sourceApprovEpRepository.findAll();
        assertThat(sourceApprovEpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSourceApprovEp() throws Exception {
        // Initialize the database
        sourceApprovEpRepository.saveAndFlush(sourceApprovEp);

        int databaseSizeBeforeDelete = sourceApprovEpRepository.findAll().size();

        // Delete the sourceApprovEp
        restSourceApprovEpMockMvc.perform(delete("/api/source-approv-eps/{id}", sourceApprovEp.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SourceApprovEp> sourceApprovEpList = sourceApprovEpRepository.findAll();
        assertThat(sourceApprovEpList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
