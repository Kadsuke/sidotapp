package bf.onea.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bf.onea.SidotApp;
import bf.onea.domain.PrevisionPsa;
import bf.onea.repository.PrevisionPsaRepository;
import bf.onea.service.PrevisionPsaService;
import bf.onea.service.dto.PrevisionPsaDTO;
import bf.onea.service.mapper.PrevisionPsaMapper;
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
 * Integration tests for the {@link PrevisionPsaResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisionPsaResourceIT {
    private static final Integer DEFAULT_ELABORE = 1;
    private static final Integer UPDATED_ELABORE = 2;

    private static final Integer DEFAULT_MISE_EN_OEUVRE = 1;
    private static final Integer UPDATED_MISE_EN_OEUVRE = 2;

    @Autowired
    private PrevisionPsaRepository previsionPsaRepository;

    @Autowired
    private PrevisionPsaMapper previsionPsaMapper;

    @Autowired
    private PrevisionPsaService previsionPsaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisionPsaMockMvc;

    private PrevisionPsa previsionPsa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionPsa createEntity(EntityManager em) {
        PrevisionPsa previsionPsa = new PrevisionPsa().elabore(DEFAULT_ELABORE).miseEnOeuvre(DEFAULT_MISE_EN_OEUVRE);
        return previsionPsa;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisionPsa createUpdatedEntity(EntityManager em) {
        PrevisionPsa previsionPsa = new PrevisionPsa().elabore(UPDATED_ELABORE).miseEnOeuvre(UPDATED_MISE_EN_OEUVRE);
        return previsionPsa;
    }

    @BeforeEach
    public void initTest() {
        previsionPsa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisionPsa() throws Exception {
        int databaseSizeBeforeCreate = previsionPsaRepository.findAll().size();
        // Create the PrevisionPsa
        PrevisionPsaDTO previsionPsaDTO = previsionPsaMapper.toDto(previsionPsa);
        restPrevisionPsaMockMvc
            .perform(
                post("/api/prevision-psas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionPsaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the PrevisionPsa in the database
        List<PrevisionPsa> previsionPsaList = previsionPsaRepository.findAll();
        assertThat(previsionPsaList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisionPsa testPrevisionPsa = previsionPsaList.get(previsionPsaList.size() - 1);
        assertThat(testPrevisionPsa.getElabore()).isEqualTo(DEFAULT_ELABORE);
        assertThat(testPrevisionPsa.getMiseEnOeuvre()).isEqualTo(DEFAULT_MISE_EN_OEUVRE);
    }

    @Test
    @Transactional
    public void createPrevisionPsaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsionPsaRepository.findAll().size();

        // Create the PrevisionPsa with an existing ID
        previsionPsa.setId(1L);
        PrevisionPsaDTO previsionPsaDTO = previsionPsaMapper.toDto(previsionPsa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisionPsaMockMvc
            .perform(
                post("/api/prevision-psas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionPsaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrevisionPsa in the database
        List<PrevisionPsa> previsionPsaList = previsionPsaRepository.findAll();
        assertThat(previsionPsaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrevisionPsas() throws Exception {
        // Initialize the database
        previsionPsaRepository.saveAndFlush(previsionPsa);

        // Get all the previsionPsaList
        restPrevisionPsaMockMvc
            .perform(get("/api/prevision-psas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsionPsa.getId().intValue())))
            .andExpect(jsonPath("$.[*].elabore").value(hasItem(DEFAULT_ELABORE)))
            .andExpect(jsonPath("$.[*].miseEnOeuvre").value(hasItem(DEFAULT_MISE_EN_OEUVRE)));
    }

    @Test
    @Transactional
    public void getPrevisionPsa() throws Exception {
        // Initialize the database
        previsionPsaRepository.saveAndFlush(previsionPsa);

        // Get the previsionPsa
        restPrevisionPsaMockMvc
            .perform(get("/api/prevision-psas/{id}", previsionPsa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsionPsa.getId().intValue()))
            .andExpect(jsonPath("$.elabore").value(DEFAULT_ELABORE))
            .andExpect(jsonPath("$.miseEnOeuvre").value(DEFAULT_MISE_EN_OEUVRE));
    }

    @Test
    @Transactional
    public void getNonExistingPrevisionPsa() throws Exception {
        // Get the previsionPsa
        restPrevisionPsaMockMvc.perform(get("/api/prevision-psas/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisionPsa() throws Exception {
        // Initialize the database
        previsionPsaRepository.saveAndFlush(previsionPsa);

        int databaseSizeBeforeUpdate = previsionPsaRepository.findAll().size();

        // Update the previsionPsa
        PrevisionPsa updatedPrevisionPsa = previsionPsaRepository.findById(previsionPsa.getId()).get();
        // Disconnect from session so that the updates on updatedPrevisionPsa are not directly saved in db
        em.detach(updatedPrevisionPsa);
        updatedPrevisionPsa.elabore(UPDATED_ELABORE).miseEnOeuvre(UPDATED_MISE_EN_OEUVRE);
        PrevisionPsaDTO previsionPsaDTO = previsionPsaMapper.toDto(updatedPrevisionPsa);

        restPrevisionPsaMockMvc
            .perform(
                put("/api/prevision-psas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionPsaDTO))
            )
            .andExpect(status().isOk());

        // Validate the PrevisionPsa in the database
        List<PrevisionPsa> previsionPsaList = previsionPsaRepository.findAll();
        assertThat(previsionPsaList).hasSize(databaseSizeBeforeUpdate);
        PrevisionPsa testPrevisionPsa = previsionPsaList.get(previsionPsaList.size() - 1);
        assertThat(testPrevisionPsa.getElabore()).isEqualTo(UPDATED_ELABORE);
        assertThat(testPrevisionPsa.getMiseEnOeuvre()).isEqualTo(UPDATED_MISE_EN_OEUVRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisionPsa() throws Exception {
        int databaseSizeBeforeUpdate = previsionPsaRepository.findAll().size();

        // Create the PrevisionPsa
        PrevisionPsaDTO previsionPsaDTO = previsionPsaMapper.toDto(previsionPsa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisionPsaMockMvc
            .perform(
                put("/api/prevision-psas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(previsionPsaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PrevisionPsa in the database
        List<PrevisionPsa> previsionPsaList = previsionPsaRepository.findAll();
        assertThat(previsionPsaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrevisionPsa() throws Exception {
        // Initialize the database
        previsionPsaRepository.saveAndFlush(previsionPsa);

        int databaseSizeBeforeDelete = previsionPsaRepository.findAll().size();

        // Delete the previsionPsa
        restPrevisionPsaMockMvc
            .perform(delete("/api/prevision-psas/{id}", previsionPsa.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisionPsa> previsionPsaList = previsionPsaRepository.findAll();
        assertThat(previsionPsaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
