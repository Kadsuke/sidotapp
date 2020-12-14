package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Macon;
import bf.onea.repository.MaconRepository;
import bf.onea.service.MaconService;
import bf.onea.service.dto.MaconDTO;
import bf.onea.service.mapper.MaconMapper;

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
 * Integration tests for the {@link MaconResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaconResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private MaconRepository maconRepository;

    @Autowired
    private MaconMapper maconMapper;

    @Autowired
    private MaconService maconService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaconMockMvc;

    private Macon macon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Macon createEntity(EntityManager em) {
        Macon macon = new Macon()
            .libelle(DEFAULT_LIBELLE);
        return macon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Macon createUpdatedEntity(EntityManager em) {
        Macon macon = new Macon()
            .libelle(UPDATED_LIBELLE);
        return macon;
    }

    @BeforeEach
    public void initTest() {
        macon = createEntity(em);
    }

    @Test
    @Transactional
    public void createMacon() throws Exception {
        int databaseSizeBeforeCreate = maconRepository.findAll().size();
        // Create the Macon
        MaconDTO maconDTO = maconMapper.toDto(macon);
        restMaconMockMvc.perform(post("/api/macons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maconDTO)))
            .andExpect(status().isCreated());

        // Validate the Macon in the database
        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeCreate + 1);
        Macon testMacon = maconList.get(maconList.size() - 1);
        assertThat(testMacon.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createMaconWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maconRepository.findAll().size();

        // Create the Macon with an existing ID
        macon.setId(1L);
        MaconDTO maconDTO = maconMapper.toDto(macon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaconMockMvc.perform(post("/api/macons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Macon in the database
        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = maconRepository.findAll().size();
        // set the field null
        macon.setLibelle(null);

        // Create the Macon, which fails.
        MaconDTO maconDTO = maconMapper.toDto(macon);


        restMaconMockMvc.perform(post("/api/macons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maconDTO)))
            .andExpect(status().isBadRequest());

        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMacons() throws Exception {
        // Initialize the database
        maconRepository.saveAndFlush(macon);

        // Get all the maconList
        restMaconMockMvc.perform(get("/api/macons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(macon.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getMacon() throws Exception {
        // Initialize the database
        maconRepository.saveAndFlush(macon);

        // Get the macon
        restMaconMockMvc.perform(get("/api/macons/{id}", macon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(macon.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingMacon() throws Exception {
        // Get the macon
        restMaconMockMvc.perform(get("/api/macons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMacon() throws Exception {
        // Initialize the database
        maconRepository.saveAndFlush(macon);

        int databaseSizeBeforeUpdate = maconRepository.findAll().size();

        // Update the macon
        Macon updatedMacon = maconRepository.findById(macon.getId()).get();
        // Disconnect from session so that the updates on updatedMacon are not directly saved in db
        em.detach(updatedMacon);
        updatedMacon
            .libelle(UPDATED_LIBELLE);
        MaconDTO maconDTO = maconMapper.toDto(updatedMacon);

        restMaconMockMvc.perform(put("/api/macons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maconDTO)))
            .andExpect(status().isOk());

        // Validate the Macon in the database
        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeUpdate);
        Macon testMacon = maconList.get(maconList.size() - 1);
        assertThat(testMacon.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMacon() throws Exception {
        int databaseSizeBeforeUpdate = maconRepository.findAll().size();

        // Create the Macon
        MaconDTO maconDTO = maconMapper.toDto(macon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaconMockMvc.perform(put("/api/macons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Macon in the database
        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMacon() throws Exception {
        // Initialize the database
        maconRepository.saveAndFlush(macon);

        int databaseSizeBeforeDelete = maconRepository.findAll().size();

        // Delete the macon
        restMaconMockMvc.perform(delete("/api/macons/{id}", macon.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Macon> maconList = maconRepository.findAll();
        assertThat(maconList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
