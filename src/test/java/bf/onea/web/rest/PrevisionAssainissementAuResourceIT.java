package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.PrevisionAssainissementAu;
import bf.onea.repository.PrevisionAssainissementAuRepository;
import bf.onea.service.PrevisionAssainissementAuService;
import bf.onea.service.dto.PrevisionAssainissementAuDTO;
import bf.onea.service.mapper.PrevisionAssainissementAuMapper;

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
 * Integration tests for the {@link PrevisionAssainissementAuResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisionAssainissementAuResourceIT {

    private static final Integer DEFAULT_NB_LATRINE = 1;
    private static final Integer UPDATED_NB_LATRINE = 2;

    private static final Integer DEFAULT_NB_PUISARD = 1;
    private static final Integer UPDATED_NB_PUISARD = 2;

    private static final Integer DEFAULT_NB_PUBLIC = 1;
    private static final Integer UPDATED_NB_PUBLIC = 2;

    private static final Integer DEFAULT_NB_SCOLAIRE = 1;
    private static final Integer UPDATED_NB_SCOLAIRE = 2;

    private static final Integer DEFAULT_CENTRE_DE_SANTE = 1;
    private static final Integer UPDATED_CENTRE_DE_SANTE = 2;

    private static final Integer DEFAULT_POPULATION = 1;
    private static final Integer UPDATED_POPULATION = 2;

    @Autowired
    private PrevisionAssainissementAuRepository previsionAssainissementAuRepository;

    @Autowired
    private PrevisionAssainissementAuMapper previsionAssainissementAuMapper;

    @Autowired
    private PrevisionAssainissementAuService previsionAssainissementAuService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisionAssainissementAuMockMvc;

    private PrevisionAssainissementAu previsionAssainissementAu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionAssainissementAu createEntity(EntityManager em) {
        PrevisionAssainissementAu previsionAssainissementAu = new PrevisionAssainissementAu()
            .nbLatrine(DEFAULT_NB_LATRINE)
            .nbPuisard(DEFAULT_NB_PUISARD)
            .nbPublic(DEFAULT_NB_PUBLIC)
            .nbScolaire(DEFAULT_NB_SCOLAIRE)
            .centreDeSante(DEFAULT_CENTRE_DE_SANTE)
            .population(DEFAULT_POPULATION);
        return previsionAssainissementAu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionAssainissementAu createUpdatedEntity(EntityManager em) {
        PrevisionAssainissementAu previsionAssainissementAu = new PrevisionAssainissementAu()
            .nbLatrine(UPDATED_NB_LATRINE)
            .nbPuisard(UPDATED_NB_PUISARD)
            .nbPublic(UPDATED_NB_PUBLIC)
            .nbScolaire(UPDATED_NB_SCOLAIRE)
            .centreDeSante(UPDATED_CENTRE_DE_SANTE)
            .population(UPDATED_POPULATION);
        return previsionAssainissementAu;
    }

    @BeforeEach
    public void initTest() {
        previsionAssainissementAu = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisionAssainissementAu() throws Exception {
        int databaseSizeBeforeCreate = previsionAssainissementAuRepository.findAll().size();
        // Create the PrevisionAssainissementAu
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);
        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isCreated());

        // Validate the PrevisionAssainissementAu in the database
        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisionAssainissementAu testPrevisionAssainissementAu = previsionAssainissementAuList.get(previsionAssainissementAuList.size() - 1);
        assertThat(testPrevisionAssainissementAu.getNbLatrine()).isEqualTo(DEFAULT_NB_LATRINE);
        assertThat(testPrevisionAssainissementAu.getNbPuisard()).isEqualTo(DEFAULT_NB_PUISARD);
        assertThat(testPrevisionAssainissementAu.getNbPublic()).isEqualTo(DEFAULT_NB_PUBLIC);
        assertThat(testPrevisionAssainissementAu.getNbScolaire()).isEqualTo(DEFAULT_NB_SCOLAIRE);
        assertThat(testPrevisionAssainissementAu.getCentreDeSante()).isEqualTo(DEFAULT_CENTRE_DE_SANTE);
        assertThat(testPrevisionAssainissementAu.getPopulation()).isEqualTo(DEFAULT_POPULATION);
    }

    @Test
    @Transactional
    public void createPrevisionAssainissementAuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsionAssainissementAuRepository.findAll().size();

        // Create the PrevisionAssainissementAu with an existing ID
        previsionAssainissementAu.setId(1L);
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisionAssainissementAu in the database
        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNbLatrineIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setNbLatrine(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNbPuisardIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setNbPuisard(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNbPublicIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setNbPublic(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNbScolaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setNbScolaire(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCentreDeSanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setCentreDeSante(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPopulationIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsionAssainissementAuRepository.findAll().size();
        // set the field null
        previsionAssainissementAu.setPopulation(null);

        // Create the PrevisionAssainissementAu, which fails.
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);


        restPrevisionAssainissementAuMockMvc.perform(post("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrevisionAssainissementAus() throws Exception {
        // Initialize the database
        previsionAssainissementAuRepository.saveAndFlush(previsionAssainissementAu);

        // Get all the previsionAssainissementAuList
        restPrevisionAssainissementAuMockMvc.perform(get("/api/prevision-assainissement-aus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsionAssainissementAu.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbLatrine").value(hasItem(DEFAULT_NB_LATRINE)))
            .andExpect(jsonPath("$.[*].nbPuisard").value(hasItem(DEFAULT_NB_PUISARD)))
            .andExpect(jsonPath("$.[*].nbPublic").value(hasItem(DEFAULT_NB_PUBLIC)))
            .andExpect(jsonPath("$.[*].nbScolaire").value(hasItem(DEFAULT_NB_SCOLAIRE)))
            .andExpect(jsonPath("$.[*].centreDeSante").value(hasItem(DEFAULT_CENTRE_DE_SANTE)))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION)));
    }
    
    @Test
    @Transactional
    public void getPrevisionAssainissementAu() throws Exception {
        // Initialize the database
        previsionAssainissementAuRepository.saveAndFlush(previsionAssainissementAu);

        // Get the previsionAssainissementAu
        restPrevisionAssainissementAuMockMvc.perform(get("/api/prevision-assainissement-aus/{id}", previsionAssainissementAu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsionAssainissementAu.getId().intValue()))
            .andExpect(jsonPath("$.nbLatrine").value(DEFAULT_NB_LATRINE))
            .andExpect(jsonPath("$.nbPuisard").value(DEFAULT_NB_PUISARD))
            .andExpect(jsonPath("$.nbPublic").value(DEFAULT_NB_PUBLIC))
            .andExpect(jsonPath("$.nbScolaire").value(DEFAULT_NB_SCOLAIRE))
            .andExpect(jsonPath("$.centreDeSante").value(DEFAULT_CENTRE_DE_SANTE))
            .andExpect(jsonPath("$.population").value(DEFAULT_POPULATION));
    }
    @Test
    @Transactional
    public void getNonExistingPrevisionAssainissementAu() throws Exception {
        // Get the previsionAssainissementAu
        restPrevisionAssainissementAuMockMvc.perform(get("/api/prevision-assainissement-aus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisionAssainissementAu() throws Exception {
        // Initialize the database
        previsionAssainissementAuRepository.saveAndFlush(previsionAssainissementAu);

        int databaseSizeBeforeUpdate = previsionAssainissementAuRepository.findAll().size();

        // Update the previsionAssainissementAu
        PrevisionAssainissementAu updatedPrevisionAssainissementAu = previsionAssainissementAuRepository.findById(previsionAssainissementAu.getId()).get();
        // Disconnect from session so that the updates on updatedPrevisionAssainissementAu are not directly saved in db
        em.detach(updatedPrevisionAssainissementAu);
        updatedPrevisionAssainissementAu
            .nbLatrine(UPDATED_NB_LATRINE)
            .nbPuisard(UPDATED_NB_PUISARD)
            .nbPublic(UPDATED_NB_PUBLIC)
            .nbScolaire(UPDATED_NB_SCOLAIRE)
            .centreDeSante(UPDATED_CENTRE_DE_SANTE)
            .population(UPDATED_POPULATION);
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(updatedPrevisionAssainissementAu);

        restPrevisionAssainissementAuMockMvc.perform(put("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isOk());

        // Validate the PrevisionAssainissementAu in the database
        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeUpdate);
        PrevisionAssainissementAu testPrevisionAssainissementAu = previsionAssainissementAuList.get(previsionAssainissementAuList.size() - 1);
        assertThat(testPrevisionAssainissementAu.getNbLatrine()).isEqualTo(UPDATED_NB_LATRINE);
        assertThat(testPrevisionAssainissementAu.getNbPuisard()).isEqualTo(UPDATED_NB_PUISARD);
        assertThat(testPrevisionAssainissementAu.getNbPublic()).isEqualTo(UPDATED_NB_PUBLIC);
        assertThat(testPrevisionAssainissementAu.getNbScolaire()).isEqualTo(UPDATED_NB_SCOLAIRE);
        assertThat(testPrevisionAssainissementAu.getCentreDeSante()).isEqualTo(UPDATED_CENTRE_DE_SANTE);
        assertThat(testPrevisionAssainissementAu.getPopulation()).isEqualTo(UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisionAssainissementAu() throws Exception {
        int databaseSizeBeforeUpdate = previsionAssainissementAuRepository.findAll().size();

        // Create the PrevisionAssainissementAu
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO = previsionAssainissementAuMapper.toDto(previsionAssainissementAu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisionAssainissementAuMockMvc.perform(put("/api/prevision-assainissement-aus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementAuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisionAssainissementAu in the database
        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrevisionAssainissementAu() throws Exception {
        // Initialize the database
        previsionAssainissementAuRepository.saveAndFlush(previsionAssainissementAu);

        int databaseSizeBeforeDelete = previsionAssainissementAuRepository.findAll().size();

        // Delete the previsionAssainissementAu
        restPrevisionAssainissementAuMockMvc.perform(delete("/api/prevision-assainissement-aus/{id}", previsionAssainissementAu.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisionAssainissementAu> previsionAssainissementAuList = previsionAssainissementAuRepository.findAll();
        assertThat(previsionAssainissementAuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
