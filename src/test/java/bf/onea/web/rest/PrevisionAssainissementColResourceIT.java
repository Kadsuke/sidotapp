package bf.onea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bf.onea.SidotApp;
import bf.onea.domain.PrevisionAssainissementCol;
import bf.onea.repository.PrevisionAssainissementColRepository;
import bf.onea.service.PrevisionAssainissementColService;
import bf.onea.service.dto.PrevisionAssainissementColDTO;
import bf.onea.service.mapper.PrevisionAssainissementColMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PrevisionAssainissementColResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisionAssainissementColResourceIT {
    private static final Integer DEFAULT_NB_STEP = 1;
    private static final Integer UPDATED_NB_STEP = 2;

    private static final Integer DEFAULT_NB_STBV = 1;
    private static final Integer UPDATED_NB_STBV = 2;

    private static final Float DEFAULT_RESEAUX = 1F;
    private static final Float UPDATED_RESEAUX = 2F;

    private static final Integer DEFAULT_NB_RACCORDEMENT = 1;
    private static final Integer UPDATED_NB_RACCORDEMENT = 2;

    @Autowired
    private PrevisionAssainissementColRepository previsionAssainissementColRepository;

    @Autowired
    private PrevisionAssainissementColMapper previsionAssainissementColMapper;

    @Autowired
    private PrevisionAssainissementColService previsionAssainissementColService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisionAssainissementColMockMvc;

    private PrevisionAssainissementCol previsionAssainissementCol;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionAssainissementCol createEntity(EntityManager em) {
        PrevisionAssainissementCol previsionAssainissementCol = new PrevisionAssainissementCol()
            .nbStep(DEFAULT_NB_STEP)
            .nbStbv(DEFAULT_NB_STBV)
            .reseaux(DEFAULT_RESEAUX)
            .nbRaccordement(DEFAULT_NB_RACCORDEMENT);
        return previsionAssainissementCol;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionAssainissementCol createUpdatedEntity(EntityManager em) {
        PrevisionAssainissementCol previsionAssainissementCol = new PrevisionAssainissementCol()
            .nbStep(UPDATED_NB_STEP)
            .nbStbv(UPDATED_NB_STBV)
            .reseaux(UPDATED_RESEAUX)
            .nbRaccordement(UPDATED_NB_RACCORDEMENT);
        return previsionAssainissementCol;
    }

    @BeforeEach
    public void initTest() {
        previsionAssainissementCol = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisionAssainissementCol() throws Exception {
        int databaseSizeBeforeCreate = previsionAssainissementColRepository.findAll().size();
        // Create the PrevisionAssainissementCol
        PrevisionAssainissementColDTO previsionAssainissementColDTO = previsionAssainissementColMapper.toDto(previsionAssainissementCol);
        restPrevisionAssainissementColMockMvc
            .perform(
                post("/api/prevision-assainissement-cols")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementColDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PrevisionAssainissementCol in the database
        List<PrevisionAssainissementCol> previsionAssainissementColList = previsionAssainissementColRepository.findAll();
        assertThat(previsionAssainissementColList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisionAssainissementCol testPrevisionAssainissementCol = previsionAssainissementColList.get(
            previsionAssainissementColList.size() - 1
        );
        assertThat(testPrevisionAssainissementCol.getNbStep()).isEqualTo(DEFAULT_NB_STEP);
        assertThat(testPrevisionAssainissementCol.getNbStbv()).isEqualTo(DEFAULT_NB_STBV);
        assertThat(testPrevisionAssainissementCol.getReseaux()).isEqualTo(DEFAULT_RESEAUX);
        assertThat(testPrevisionAssainissementCol.getNbRaccordement()).isEqualTo(DEFAULT_NB_RACCORDEMENT);
    }

    @Test
    @Transactional
    public void createPrevisionAssainissementColWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsionAssainissementColRepository.findAll().size();

        // Create the PrevisionAssainissementCol with an existing ID
        previsionAssainissementCol.setId(1L);
        PrevisionAssainissementColDTO previsionAssainissementColDTO = previsionAssainissementColMapper.toDto(previsionAssainissementCol);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisionAssainissementColMockMvc
            .perform(
                post("/api/prevision-assainissement-cols")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementColDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrevisionAssainissementCol in the database
        List<PrevisionAssainissementCol> previsionAssainissementColList = previsionAssainissementColRepository.findAll();
        assertThat(previsionAssainissementColList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrevisionAssainissementCols() throws Exception {
        // Initialize the database
        previsionAssainissementColRepository.saveAndFlush(previsionAssainissementCol);

        // Get all the previsionAssainissementColList
        restPrevisionAssainissementColMockMvc
            .perform(get("/api/prevision-assainissement-cols?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsionAssainissementCol.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbStep").value(hasItem(DEFAULT_NB_STEP)))
            .andExpect(jsonPath("$.[*].nbStbv").value(hasItem(DEFAULT_NB_STBV)))
            .andExpect(jsonPath("$.[*].reseaux").value(hasItem(DEFAULT_RESEAUX.doubleValue())))
            .andExpect(jsonPath("$.[*].nbRaccordement").value(hasItem(DEFAULT_NB_RACCORDEMENT)));
    }

    @Test
    @Transactional
    public void getPrevisionAssainissementCol() throws Exception {
        // Initialize the database
        previsionAssainissementColRepository.saveAndFlush(previsionAssainissementCol);

        // Get the previsionAssainissementCol
        restPrevisionAssainissementColMockMvc
            .perform(get("/api/prevision-assainissement-cols/{id}", previsionAssainissementCol.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsionAssainissementCol.getId().intValue()))
            .andExpect(jsonPath("$.nbStep").value(DEFAULT_NB_STEP))
            .andExpect(jsonPath("$.nbStbv").value(DEFAULT_NB_STBV))
            .andExpect(jsonPath("$.reseaux").value(DEFAULT_RESEAUX.doubleValue()))
            .andExpect(jsonPath("$.nbRaccordement").value(DEFAULT_NB_RACCORDEMENT));
    }

    @Test
    @Transactional
    public void getNonExistingPrevisionAssainissementCol() throws Exception {
        // Get the previsionAssainissementCol
        restPrevisionAssainissementColMockMvc
            .perform(get("/api/prevision-assainissement-cols/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisionAssainissementCol() throws Exception {
        // Initialize the database
        previsionAssainissementColRepository.saveAndFlush(previsionAssainissementCol);

        int databaseSizeBeforeUpdate = previsionAssainissementColRepository.findAll().size();

        // Update the previsionAssainissementCol
        PrevisionAssainissementCol updatedPrevisionAssainissementCol = previsionAssainissementColRepository
            .findById(previsionAssainissementCol.getId())
            .get();
        // Disconnect from session so that the updates on updatedPrevisionAssainissementCol are not directly saved in db
        em.detach(updatedPrevisionAssainissementCol);
        updatedPrevisionAssainissementCol
            .nbStep(UPDATED_NB_STEP)
            .nbStbv(UPDATED_NB_STBV)
            .reseaux(UPDATED_RESEAUX)
            .nbRaccordement(UPDATED_NB_RACCORDEMENT);
        PrevisionAssainissementColDTO previsionAssainissementColDTO = previsionAssainissementColMapper.toDto(
            updatedPrevisionAssainissementCol
        );

        restPrevisionAssainissementColMockMvc
            .perform(
                put("/api/prevision-assainissement-cols")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementColDTO))
            )
            .andExpect(status().isOk());

        // Validate the PrevisionAssainissementCol in the database
        List<PrevisionAssainissementCol> previsionAssainissementColList = previsionAssainissementColRepository.findAll();
        assertThat(previsionAssainissementColList).hasSize(databaseSizeBeforeUpdate);
        PrevisionAssainissementCol testPrevisionAssainissementCol = previsionAssainissementColList.get(
            previsionAssainissementColList.size() - 1
        );
        assertThat(testPrevisionAssainissementCol.getNbStep()).isEqualTo(UPDATED_NB_STEP);
        assertThat(testPrevisionAssainissementCol.getNbStbv()).isEqualTo(UPDATED_NB_STBV);
        assertThat(testPrevisionAssainissementCol.getReseaux()).isEqualTo(UPDATED_RESEAUX);
        assertThat(testPrevisionAssainissementCol.getNbRaccordement()).isEqualTo(UPDATED_NB_RACCORDEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisionAssainissementCol() throws Exception {
        int databaseSizeBeforeUpdate = previsionAssainissementColRepository.findAll().size();

        // Create the PrevisionAssainissementCol
        PrevisionAssainissementColDTO previsionAssainissementColDTO = previsionAssainissementColMapper.toDto(previsionAssainissementCol);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisionAssainissementColMockMvc
            .perform(
                put("/api/prevision-assainissement-cols")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionAssainissementColDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrevisionAssainissementCol in the database
        List<PrevisionAssainissementCol> previsionAssainissementColList = previsionAssainissementColRepository.findAll();
        assertThat(previsionAssainissementColList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrevisionAssainissementCol() throws Exception {
        // Initialize the database
        previsionAssainissementColRepository.saveAndFlush(previsionAssainissementCol);

        int databaseSizeBeforeDelete = previsionAssainissementColRepository.findAll().size();

        // Delete the previsionAssainissementCol
        restPrevisionAssainissementColMockMvc
            .perform(
                delete("/api/prevision-assainissement-cols/{id}", previsionAssainissementCol.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisionAssainissementCol> previsionAssainissementColList = previsionAssainissementColRepository.findAll();
        assertThat(previsionAssainissementColList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
