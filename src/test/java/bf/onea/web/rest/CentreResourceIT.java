package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.Centre;
import bf.onea.repository.CentreRepository;
import bf.onea.service.CentreService;
import bf.onea.service.dto.CentreDTO;
import bf.onea.service.mapper.CentreMapper;

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
 * Integration tests for the {@link CentreResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CentreResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private CentreMapper centreMapper;

    @Autowired
    private CentreService centreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCentreMockMvc;

    private Centre centre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Centre createEntity(EntityManager em) {
        Centre centre = new Centre()
            .libelle(DEFAULT_LIBELLE)
            .responsable(DEFAULT_RESPONSABLE)
            .contact(DEFAULT_CONTACT);
        return centre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Centre createUpdatedEntity(EntityManager em) {
        Centre centre = new Centre()
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        return centre;
    }

    @BeforeEach
    public void initTest() {
        centre = createEntity(em);
    }

    @Test
    @Transactional
    public void createCentre() throws Exception {
        int databaseSizeBeforeCreate = centreRepository.findAll().size();
        // Create the Centre
        CentreDTO centreDTO = centreMapper.toDto(centre);
        restCentreMockMvc.perform(post("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isCreated());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeCreate + 1);
        Centre testCentre = centreList.get(centreList.size() - 1);
        assertThat(testCentre.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCentre.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testCentre.getContact()).isEqualTo(DEFAULT_CONTACT);
    }

    @Test
    @Transactional
    public void createCentreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = centreRepository.findAll().size();

        // Create the Centre with an existing ID
        centre.setId(1L);
        CentreDTO centreDTO = centreMapper.toDto(centre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentreMockMvc.perform(post("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreRepository.findAll().size();
        // set the field null
        centre.setLibelle(null);

        // Create the Centre, which fails.
        CentreDTO centreDTO = centreMapper.toDto(centre);


        restCentreMockMvc.perform(post("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreRepository.findAll().size();
        // set the field null
        centre.setResponsable(null);

        // Create the Centre, which fails.
        CentreDTO centreDTO = centreMapper.toDto(centre);


        restCentreMockMvc.perform(post("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreRepository.findAll().size();
        // set the field null
        centre.setContact(null);

        // Create the Centre, which fails.
        CentreDTO centreDTO = centreMapper.toDto(centre);


        restCentreMockMvc.perform(post("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCentres() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        // Get all the centreList
        restCentreMockMvc.perform(get("/api/centres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centre.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)));
    }
    
    @Test
    @Transactional
    public void getCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        // Get the centre
        restCentreMockMvc.perform(get("/api/centres/{id}", centre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(centre.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT));
    }
    @Test
    @Transactional
    public void getNonExistingCentre() throws Exception {
        // Get the centre
        restCentreMockMvc.perform(get("/api/centres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        int databaseSizeBeforeUpdate = centreRepository.findAll().size();

        // Update the centre
        Centre updatedCentre = centreRepository.findById(centre.getId()).get();
        // Disconnect from session so that the updates on updatedCentre are not directly saved in db
        em.detach(updatedCentre);
        updatedCentre
            .libelle(UPDATED_LIBELLE)
            .responsable(UPDATED_RESPONSABLE)
            .contact(UPDATED_CONTACT);
        CentreDTO centreDTO = centreMapper.toDto(updatedCentre);

        restCentreMockMvc.perform(put("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isOk());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeUpdate);
        Centre testCentre = centreList.get(centreList.size() - 1);
        assertThat(testCentre.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCentre.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testCentre.getContact()).isEqualTo(UPDATED_CONTACT);
    }

    @Test
    @Transactional
    public void updateNonExistingCentre() throws Exception {
        int databaseSizeBeforeUpdate = centreRepository.findAll().size();

        // Create the Centre
        CentreDTO centreDTO = centreMapper.toDto(centre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentreMockMvc.perform(put("/api/centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Centre in the database
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCentre() throws Exception {
        // Initialize the database
        centreRepository.saveAndFlush(centre);

        int databaseSizeBeforeDelete = centreRepository.findAll().size();

        // Delete the centre
        restCentreMockMvc.perform(delete("/api/centres/{id}", centre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Centre> centreList = centreRepository.findAll();
        assertThat(centreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
