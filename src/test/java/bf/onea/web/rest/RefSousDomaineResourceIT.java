package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.RefSousDomaine;
import bf.onea.repository.RefSousDomaineRepository;
import bf.onea.service.RefSousDomaineService;
import bf.onea.service.dto.RefSousDomaineDTO;
import bf.onea.service.mapper.RefSousDomaineMapper;

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
 * Integration tests for the {@link RefSousDomaineResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RefSousDomaineResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefSousDomaineRepository refSousDomaineRepository;

    @Autowired
    private RefSousDomaineMapper refSousDomaineMapper;

    @Autowired
    private RefSousDomaineService refSousDomaineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefSousDomaineMockMvc;

    private RefSousDomaine refSousDomaine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefSousDomaine createEntity(EntityManager em) {
        RefSousDomaine refSousDomaine = new RefSousDomaine()
            .libelle(DEFAULT_LIBELLE);
        return refSousDomaine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefSousDomaine createUpdatedEntity(EntityManager em) {
        RefSousDomaine refSousDomaine = new RefSousDomaine()
            .libelle(UPDATED_LIBELLE);
        return refSousDomaine;
    }

    @BeforeEach
    public void initTest() {
        refSousDomaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefSousDomaine() throws Exception {
        int databaseSizeBeforeCreate = refSousDomaineRepository.findAll().size();
        // Create the RefSousDomaine
        RefSousDomaineDTO refSousDomaineDTO = refSousDomaineMapper.toDto(refSousDomaine);
        restRefSousDomaineMockMvc.perform(post("/api/ref-sous-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refSousDomaineDTO)))
            .andExpect(status().isCreated());

        // Validate the RefSousDomaine in the database
        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeCreate + 1);
        RefSousDomaine testRefSousDomaine = refSousDomaineList.get(refSousDomaineList.size() - 1);
        assertThat(testRefSousDomaine.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefSousDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refSousDomaineRepository.findAll().size();

        // Create the RefSousDomaine with an existing ID
        refSousDomaine.setId(1L);
        RefSousDomaineDTO refSousDomaineDTO = refSousDomaineMapper.toDto(refSousDomaine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefSousDomaineMockMvc.perform(post("/api/ref-sous-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refSousDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefSousDomaine in the database
        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = refSousDomaineRepository.findAll().size();
        // set the field null
        refSousDomaine.setLibelle(null);

        // Create the RefSousDomaine, which fails.
        RefSousDomaineDTO refSousDomaineDTO = refSousDomaineMapper.toDto(refSousDomaine);


        restRefSousDomaineMockMvc.perform(post("/api/ref-sous-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refSousDomaineDTO)))
            .andExpect(status().isBadRequest());

        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefSousDomaines() throws Exception {
        // Initialize the database
        refSousDomaineRepository.saveAndFlush(refSousDomaine);

        // Get all the refSousDomaineList
        restRefSousDomaineMockMvc.perform(get("/api/ref-sous-domaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refSousDomaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRefSousDomaine() throws Exception {
        // Initialize the database
        refSousDomaineRepository.saveAndFlush(refSousDomaine);

        // Get the refSousDomaine
        restRefSousDomaineMockMvc.perform(get("/api/ref-sous-domaines/{id}", refSousDomaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refSousDomaine.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRefSousDomaine() throws Exception {
        // Get the refSousDomaine
        restRefSousDomaineMockMvc.perform(get("/api/ref-sous-domaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefSousDomaine() throws Exception {
        // Initialize the database
        refSousDomaineRepository.saveAndFlush(refSousDomaine);

        int databaseSizeBeforeUpdate = refSousDomaineRepository.findAll().size();

        // Update the refSousDomaine
        RefSousDomaine updatedRefSousDomaine = refSousDomaineRepository.findById(refSousDomaine.getId()).get();
        // Disconnect from session so that the updates on updatedRefSousDomaine are not directly saved in db
        em.detach(updatedRefSousDomaine);
        updatedRefSousDomaine
            .libelle(UPDATED_LIBELLE);
        RefSousDomaineDTO refSousDomaineDTO = refSousDomaineMapper.toDto(updatedRefSousDomaine);

        restRefSousDomaineMockMvc.perform(put("/api/ref-sous-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refSousDomaineDTO)))
            .andExpect(status().isOk());

        // Validate the RefSousDomaine in the database
        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeUpdate);
        RefSousDomaine testRefSousDomaine = refSousDomaineList.get(refSousDomaineList.size() - 1);
        assertThat(testRefSousDomaine.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefSousDomaine() throws Exception {
        int databaseSizeBeforeUpdate = refSousDomaineRepository.findAll().size();

        // Create the RefSousDomaine
        RefSousDomaineDTO refSousDomaineDTO = refSousDomaineMapper.toDto(refSousDomaine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefSousDomaineMockMvc.perform(put("/api/ref-sous-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refSousDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefSousDomaine in the database
        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefSousDomaine() throws Exception {
        // Initialize the database
        refSousDomaineRepository.saveAndFlush(refSousDomaine);

        int databaseSizeBeforeDelete = refSousDomaineRepository.findAll().size();

        // Delete the refSousDomaine
        restRefSousDomaineMockMvc.perform(delete("/api/ref-sous-domaines/{id}", refSousDomaine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefSousDomaine> refSousDomaineList = refSousDomaineRepository.findAll();
        assertThat(refSousDomaineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
