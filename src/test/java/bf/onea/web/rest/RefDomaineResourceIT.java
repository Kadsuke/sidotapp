package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.RefDomaine;
import bf.onea.repository.RefDomaineRepository;
import bf.onea.service.RefDomaineService;
import bf.onea.service.dto.RefDomaineDTO;
import bf.onea.service.mapper.RefDomaineMapper;

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
 * Integration tests for the {@link RefDomaineResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RefDomaineResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private RefDomaineRepository refDomaineRepository;

    @Autowired
    private RefDomaineMapper refDomaineMapper;

    @Autowired
    private RefDomaineService refDomaineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRefDomaineMockMvc;

    private RefDomaine refDomaine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefDomaine createEntity(EntityManager em) {
        RefDomaine refDomaine = new RefDomaine()
            .libelle(DEFAULT_LIBELLE);
        return refDomaine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefDomaine createUpdatedEntity(EntityManager em) {
        RefDomaine refDomaine = new RefDomaine()
            .libelle(UPDATED_LIBELLE);
        return refDomaine;
    }

    @BeforeEach
    public void initTest() {
        refDomaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefDomaine() throws Exception {
        int databaseSizeBeforeCreate = refDomaineRepository.findAll().size();
        // Create the RefDomaine
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);
        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isCreated());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeCreate + 1);
        RefDomaine testRefDomaine = refDomaineList.get(refDomaineList.size() - 1);
        assertThat(testRefDomaine.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createRefDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refDomaineRepository.findAll().size();

        // Create the RefDomaine with an existing ID
        refDomaine.setId(1L);
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefDomaineMockMvc.perform(post("/api/ref-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRefDomaines() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        // Get all the refDomaineList
        restRefDomaineMockMvc.perform(get("/api/ref-domaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refDomaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        // Get the refDomaine
        restRefDomaineMockMvc.perform(get("/api/ref-domaines/{id}", refDomaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(refDomaine.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingRefDomaine() throws Exception {
        // Get the refDomaine
        restRefDomaineMockMvc.perform(get("/api/ref-domaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        int databaseSizeBeforeUpdate = refDomaineRepository.findAll().size();

        // Update the refDomaine
        RefDomaine updatedRefDomaine = refDomaineRepository.findById(refDomaine.getId()).get();
        // Disconnect from session so that the updates on updatedRefDomaine are not directly saved in db
        em.detach(updatedRefDomaine);
        updatedRefDomaine
            .libelle(UPDATED_LIBELLE);
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(updatedRefDomaine);

        restRefDomaineMockMvc.perform(put("/api/ref-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isOk());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeUpdate);
        RefDomaine testRefDomaine = refDomaineList.get(refDomaineList.size() - 1);
        assertThat(testRefDomaine.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingRefDomaine() throws Exception {
        int databaseSizeBeforeUpdate = refDomaineRepository.findAll().size();

        // Create the RefDomaine
        RefDomaineDTO refDomaineDTO = refDomaineMapper.toDto(refDomaine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefDomaineMockMvc.perform(put("/api/ref-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(refDomaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RefDomaine in the database
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefDomaine() throws Exception {
        // Initialize the database
        refDomaineRepository.saveAndFlush(refDomaine);

        int databaseSizeBeforeDelete = refDomaineRepository.findAll().size();

        // Delete the refDomaine
        restRefDomaineMockMvc.perform(delete("/api/ref-domaines/{id}", refDomaine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefDomaine> refDomaineList = refDomaineRepository.findAll();
        assertThat(refDomaineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
