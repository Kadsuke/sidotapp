package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Bailleur;
import bf.onea.repository.BailleurRepository;
import bf.onea.service.BailleurService;
import bf.onea.service.dto.BailleurDTO;
import bf.onea.service.mapper.BailleurMapper;

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
 * Integration tests for the {@link BailleurResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BailleurResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSBALE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSBALE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private BailleurRepository bailleurRepository;

    @Autowired
    private BailleurMapper bailleurMapper;

    @Autowired
    private BailleurService bailleurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBailleurMockMvc;

    private Bailleur bailleur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bailleur createEntity(EntityManager em) {
        Bailleur bailleur = new Bailleur()
            .libelle(DEFAULT_LIBELLE)
            .responsbale(DEFAULT_RESPONSBALE)
            .contact(DEFAULT_CONTACT);
        return bailleur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bailleur createUpdatedEntity(EntityManager em) {
        Bailleur bailleur = new Bailleur()
            .libelle(UPDATED_LIBELLE)
            .responsbale(UPDATED_RESPONSBALE)
            .contact(UPDATED_CONTACT);
        return bailleur;
    }

    @BeforeEach
    public void initTest() {
        bailleur = createEntity(em);
    }

    @Test
    @Transactional
    public void createBailleur() throws Exception {
        int databaseSizeBeforeCreate = bailleurRepository.findAll().size();
        // Create the Bailleur
        BailleurDTO bailleurDTO = bailleurMapper.toDto(bailleur);
        restBailleurMockMvc.perform(post("/api/bailleurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bailleurDTO)))
            .andExpect(status().isCreated());

        // Validate the Bailleur in the database
        List<Bailleur> bailleurList = bailleurRepository.findAll();
        assertThat(bailleurList).hasSize(databaseSizeBeforeCreate + 1);
        Bailleur testBailleur = bailleurList.get(bailleurList.size() - 1);
        assertThat(testBailleur.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testBailleur.getResponsbale()).isEqualTo(DEFAULT_RESPONSBALE);
        assertThat(testBailleur.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createBailleurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bailleurRepository.findAll().size();

        // Create the Bailleur with an existing ID
        bailleur.setId(1L);
        BailleurDTO bailleurDTO = bailleurMapper.toDto(bailleur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBailleurMockMvc.perform(post("/api/bailleurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bailleurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bailleur in the database
        List<Bailleur> bailleurList = bailleurRepository.findAll();
        assertThat(bailleurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBailleurs() throws Exception {
        // Initialize the database
        bailleurRepository.saveAndFlush(bailleur);

        // Get all the bailleurList
        restBailleurMockMvc.perform(get("/api/bailleurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bailleur.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].responsbale").value(hasItem(DEFAULT_RESPONSBALE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getBailleur() throws Exception {
        // Initialize the database
        bailleurRepository.saveAndFlush(bailleur);

        // Get the bailleur
        restBailleurMockMvc.perform(get("/api/bailleurs/{id}", bailleur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bailleur.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.responsbale").value(DEFAULT_RESPONSBALE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingBailleur() throws Exception {
        // Get the bailleur
        restBailleurMockMvc.perform(get("/api/bailleurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBailleur() throws Exception {
        // Initialize the database
        bailleurRepository.saveAndFlush(bailleur);

        int databaseSizeBeforeUpdate = bailleurRepository.findAll().size();

        // Update the bailleur
        Bailleur updatedBailleur = bailleurRepository.findById(bailleur.getId()).get();
        // Disconnect from session so that the updates on updatedBailleur are not directly saved in db
        em.detach(updatedBailleur);
        updatedBailleur
            .libelle(UPDATED_LIBELLE)
            .responsbale(UPDATED_RESPONSBALE)
            .contact(UPDATED_CONTACT);
        BailleurDTO bailleurDTO = bailleurMapper.toDto(updatedBailleur);

        restBailleurMockMvc.perform(put("/api/bailleurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bailleurDTO)))
            .andExpect(status().isOk());

        // Validate the Bailleur in the database
        List<Bailleur> bailleurList = bailleurRepository.findAll();
        assertThat(bailleurList).hasSize(databaseSizeBeforeUpdate);
        Bailleur testBailleur = bailleurList.get(bailleurList.size() - 1);
        assertThat(testBailleur.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testBailleur.getResponsbale()).isEqualTo(UPDATED_RESPONSBALE);
        assertThat(testBailleur.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingBailleur() throws Exception {
        int databaseSizeBeforeUpdate = bailleurRepository.findAll().size();

        // Create the Bailleur
        BailleurDTO bailleurDTO = bailleurMapper.toDto(bailleur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBailleurMockMvc.perform(put("/api/bailleurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bailleurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bailleur in the database
        List<Bailleur> bailleurList = bailleurRepository.findAll();
        assertThat(bailleurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBailleur() throws Exception {
        // Initialize the database
        bailleurRepository.saveAndFlush(bailleur);

        int databaseSizeBeforeDelete = bailleurRepository.findAll().size();

        // Delete the bailleur
        restBailleurMockMvc.perform(delete("/api/bailleurs/{id}", bailleur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bailleur> bailleurList = bailleurRepository.findAll();
        assertThat(bailleurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
