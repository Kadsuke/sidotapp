package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.DirectionRegionale;
import bf.onea.repository.DirectionRegionaleRepository;
import bf.onea.service.DirectionRegionaleService;
import bf.onea.service.dto.DirectionRegionaleDTO;
import bf.onea.service.mapper.DirectionRegionaleMapper;

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
 * Integration tests for the {@link DirectionRegionaleResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DirectionRegionaleResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private DirectionRegionaleRepository directionRegionaleRepository;

    @Autowired
    private DirectionRegionaleMapper directionRegionaleMapper;

    @Autowired
    private DirectionRegionaleService directionRegionaleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDirectionRegionaleMockMvc;

    private DirectionRegionale directionRegionale;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DirectionRegionale createEntity(EntityManager em) {
        DirectionRegionale directionRegionale = new DirectionRegionale()
            .libelle(DEFAULT_LIBELLE)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return directionRegionale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DirectionRegionale createUpdatedEntity(EntityManager em) {
        DirectionRegionale directionRegionale = new DirectionRegionale()
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return directionRegionale;
    }

    @BeforeEach
    public void initTest() {
        directionRegionale = createEntity(em);
    }

    @Test
    @Transactional
    public void createDirectionRegionale() throws Exception {
        int databaseSizeBeforeCreate = directionRegionaleRepository.findAll().size();
        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);
        restDirectionRegionaleMockMvc.perform(post("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isCreated());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeCreate + 1);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testDirectionRegionale.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testDirectionRegionale.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createDirectionRegionaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = directionRegionaleRepository.findAll().size();

        // Create the DirectionRegionale with an existing ID
        directionRegionale.setId(1L);
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDirectionRegionaleMockMvc.perform(post("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = directionRegionaleRepository.findAll().size();
        // set the field null
        directionRegionale.setLibelle(null);

        // Create the DirectionRegionale, which fails.
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);


        restDirectionRegionaleMockMvc.perform(post("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isBadRequest());

        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = directionRegionaleRepository.findAll().size();
        // set the field null
        directionRegionale.setResponsable(null);

        // Create the DirectionRegionale, which fails.
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);


        restDirectionRegionaleMockMvc.perform(post("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isBadRequest());

        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = directionRegionaleRepository.findAll().size();
        // set the field null
        directionRegionale.setContact(null);

        // Create the DirectionRegionale, which fails.
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);


        restDirectionRegionaleMockMvc.perform(post("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isBadRequest());

        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDirectionRegionales() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        // Get all the directionRegionaleList
        restDirectionRegionaleMockMvc.perform(get("/api/direction-regionales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(directionRegionale.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        // Get the directionRegionale
        restDirectionRegionaleMockMvc.perform(get("/api/direction-regionales/{id}", directionRegionale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(directionRegionale.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingDirectionRegionale() throws Exception {
        // Get the directionRegionale
        restDirectionRegionaleMockMvc.perform(get("/api/direction-regionales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();

        // Update the directionRegionale
        DirectionRegionale updatedDirectionRegionale = directionRegionaleRepository.findById(directionRegionale.getId()).get();
        // Disconnect from session so that the updates on updatedDirectionRegionale are not directly saved in db
        em.detach(updatedDirectionRegionale);
        updatedDirectionRegionale
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(updatedDirectionRegionale);

        restDirectionRegionaleMockMvc.perform(put("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isOk());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testDirectionRegionale.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testDirectionRegionale.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc.perform(put("/api/direction-regionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeDelete = directionRegionaleRepository.findAll().size();

        // Delete the directionRegionale
        restDirectionRegionaleMockMvc.perform(delete("/api/direction-regionales/{id}", directionRegionale.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
