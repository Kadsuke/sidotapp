package bf.onea.web.rest;

import bf.onea.SidotApp;
import bf.onea.domain.GeuAaOuvrage;
import bf.onea.repository.GeuAaOuvrageRepository;
import bf.onea.service.GeuAaOuvrageService;
import bf.onea.service.dto.GeuAaOuvrageDTO;
import bf.onea.service.mapper.GeuAaOuvrageMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeuAaOuvrageResource} REST controller.
 */
@SpringBootTest(classes = SidotApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeuAaOuvrageResourceIT {

    private static final String DEFAULT_REF_OUVRAGE = "AAAAAAAAAA";
    private static final String UPDATED_REF_OUVRAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PRJ_APPUIS = "AAAAAAAAAA";
    private static final String UPDATED_PRJ_APPUIS = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_COMPTEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_COMPTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_BENEF = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BENEF = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_BENEF = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_BENEF = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION_BENEF = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_BENEF = "BBBBBBBBBB";

    private static final Long DEFAULT_NB_USAGERS = 1L;
    private static final Long UPDATED_NB_USAGERS = 2L;

    private static final String DEFAULT_CODE_UN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_UN = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_REMISE_DEVIS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_REMISE_DEVIS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_DEBUT_TRAVAUX = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DEBUT_TRAVAUX = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_FIN_TRAVAUX = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FIN_TRAVAUX = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NUM_NOM_RUE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NOM_RUE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_NOM_PORTE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_NOM_PORTE = "BBBBBBBBBB";

    private static final String DEFAULT_MENAGE = "AAAAAAAAAA";
    private static final String UPDATED_MENAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUBV_ONEA = 1;
    private static final Integer UPDATED_SUBV_ONEA = 2;

    private static final Integer DEFAULT_SUBV_PROJET = 1;
    private static final Integer UPDATED_SUBV_PROJET = 2;

    private static final Integer DEFAULT_AUTRE_SUBV = 1;
    private static final Integer UPDATED_AUTRE_SUBV = 2;

    private static final String DEFAULT_REF_BON_FOURNITURE = "AAAAAAAAAA";
    private static final String UPDATED_REF_BON_FOURNITURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_REALIS_PORTE = 1;
    private static final Integer UPDATED_REALIS_PORTE = 2;

    private static final Integer DEFAULT_REALIS_TOLES = 1;
    private static final Integer UPDATED_REALIS_TOLES = 2;

    private static final String DEFAULT_ANIMATEUR = "AAAAAAAAAA";
    private static final String UPDATED_ANIMATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_SUPERVISEUR = "AAAAAAAAAA";
    private static final String UPDATED_SUPERVISEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CONTROLEUR = "AAAAAAAAAA";
    private static final String UPDATED_CONTROLEUR = "BBBBBBBBBB";

    @Autowired
    private GeuAaOuvrageRepository geuAaOuvrageRepository;

    @Autowired
    private GeuAaOuvrageMapper geuAaOuvrageMapper;

    @Autowired
    private GeuAaOuvrageService geuAaOuvrageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeuAaOuvrageMockMvc;

    private GeuAaOuvrage geuAaOuvrage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuAaOuvrage createEntity(EntityManager em) {
        GeuAaOuvrage geuAaOuvrage = new GeuAaOuvrage()
            .refOuvrage(DEFAULT_REF_OUVRAGE)
            .prjAppuis(DEFAULT_PRJ_APPUIS)
            .numCompteur(DEFAULT_NUM_COMPTEUR)
            .nomBenef(DEFAULT_NOM_BENEF)
            .prenomBenef(DEFAULT_PRENOM_BENEF)
            .professionBenef(DEFAULT_PROFESSION_BENEF)
            .nbUsagers(DEFAULT_NB_USAGERS)
            .codeUn(DEFAULT_CODE_UN)
            .dateRemiseDevis(DEFAULT_DATE_REMISE_DEVIS)
            .dateDebutTravaux(DEFAULT_DATE_DEBUT_TRAVAUX)
            .dateFinTravaux(DEFAULT_DATE_FIN_TRAVAUX)
            .numNomRue(DEFAULT_NUM_NOM_RUE)
            .numNomPorte(DEFAULT_NUM_NOM_PORTE)
            .menage(DEFAULT_MENAGE)
            .subvOnea(DEFAULT_SUBV_ONEA)
            .subvProjet(DEFAULT_SUBV_PROJET)
            .autreSubv(DEFAULT_AUTRE_SUBV)
            .refBonFourniture(DEFAULT_REF_BON_FOURNITURE)
            .realisPorte(DEFAULT_REALIS_PORTE)
            .realisToles(DEFAULT_REALIS_TOLES)
            .animateur(DEFAULT_ANIMATEUR)
            .superviseur(DEFAULT_SUPERVISEUR)
            .controleur(DEFAULT_CONTROLEUR);
        return geuAaOuvrage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeuAaOuvrage createUpdatedEntity(EntityManager em) {
        GeuAaOuvrage geuAaOuvrage = new GeuAaOuvrage()
            .refOuvrage(UPDATED_REF_OUVRAGE)
            .prjAppuis(UPDATED_PRJ_APPUIS)
            .numCompteur(UPDATED_NUM_COMPTEUR)
            .nomBenef(UPDATED_NOM_BENEF)
            .prenomBenef(UPDATED_PRENOM_BENEF)
            .professionBenef(UPDATED_PROFESSION_BENEF)
            .nbUsagers(UPDATED_NB_USAGERS)
            .codeUn(UPDATED_CODE_UN)
            .dateRemiseDevis(UPDATED_DATE_REMISE_DEVIS)
            .dateDebutTravaux(UPDATED_DATE_DEBUT_TRAVAUX)
            .dateFinTravaux(UPDATED_DATE_FIN_TRAVAUX)
            .numNomRue(UPDATED_NUM_NOM_RUE)
            .numNomPorte(UPDATED_NUM_NOM_PORTE)
            .menage(UPDATED_MENAGE)
            .subvOnea(UPDATED_SUBV_ONEA)
            .subvProjet(UPDATED_SUBV_PROJET)
            .autreSubv(UPDATED_AUTRE_SUBV)
            .refBonFourniture(UPDATED_REF_BON_FOURNITURE)
            .realisPorte(UPDATED_REALIS_PORTE)
            .realisToles(UPDATED_REALIS_TOLES)
            .animateur(UPDATED_ANIMATEUR)
            .superviseur(UPDATED_SUPERVISEUR)
            .controleur(UPDATED_CONTROLEUR);
        return geuAaOuvrage;
    }

    @BeforeEach
    public void initTest() {
        geuAaOuvrage = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeuAaOuvrage() throws Exception {
        int databaseSizeBeforeCreate = geuAaOuvrageRepository.findAll().size();
        // Create the GeuAaOuvrage
        GeuAaOuvrageDTO geuAaOuvrageDTO = geuAaOuvrageMapper.toDto(geuAaOuvrage);
        restGeuAaOuvrageMockMvc.perform(post("/api/geu-aa-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuAaOuvrageDTO)))
            .andExpect(status().isCreated());

        // Validate the GeuAaOuvrage in the database
        List<GeuAaOuvrage> geuAaOuvrageList = geuAaOuvrageRepository.findAll();
        assertThat(geuAaOuvrageList).hasSize(databaseSizeBeforeCreate + 1);
        GeuAaOuvrage testGeuAaOuvrage = geuAaOuvrageList.get(geuAaOuvrageList.size() - 1);
        assertThat(testGeuAaOuvrage.getRefOuvrage()).isEqualTo(DEFAULT_REF_OUVRAGE);
        assertThat(testGeuAaOuvrage.getPrjAppuis()).isEqualTo(DEFAULT_PRJ_APPUIS);
        assertThat(testGeuAaOuvrage.getNumCompteur()).isEqualTo(DEFAULT_NUM_COMPTEUR);
        assertThat(testGeuAaOuvrage.getNomBenef()).isEqualTo(DEFAULT_NOM_BENEF);
        assertThat(testGeuAaOuvrage.getPrenomBenef()).isEqualTo(DEFAULT_PRENOM_BENEF);
        assertThat(testGeuAaOuvrage.getProfessionBenef()).isEqualTo(DEFAULT_PROFESSION_BENEF);
        assertThat(testGeuAaOuvrage.getNbUsagers()).isEqualTo(DEFAULT_NB_USAGERS);
        assertThat(testGeuAaOuvrage.getCodeUn()).isEqualTo(DEFAULT_CODE_UN);
        assertThat(testGeuAaOuvrage.getDateRemiseDevis()).isEqualTo(DEFAULT_DATE_REMISE_DEVIS);
        assertThat(testGeuAaOuvrage.getDateDebutTravaux()).isEqualTo(DEFAULT_DATE_DEBUT_TRAVAUX);
        assertThat(testGeuAaOuvrage.getDateFinTravaux()).isEqualTo(DEFAULT_DATE_FIN_TRAVAUX);
        assertThat(testGeuAaOuvrage.getNumNomRue()).isEqualTo(DEFAULT_NUM_NOM_RUE);
        assertThat(testGeuAaOuvrage.getNumNomPorte()).isEqualTo(DEFAULT_NUM_NOM_PORTE);
        assertThat(testGeuAaOuvrage.getMenage()).isEqualTo(DEFAULT_MENAGE);
        assertThat(testGeuAaOuvrage.getSubvOnea()).isEqualTo(DEFAULT_SUBV_ONEA);
        assertThat(testGeuAaOuvrage.getSubvProjet()).isEqualTo(DEFAULT_SUBV_PROJET);
        assertThat(testGeuAaOuvrage.getAutreSubv()).isEqualTo(DEFAULT_AUTRE_SUBV);
        assertThat(testGeuAaOuvrage.getRefBonFourniture()).isEqualTo(DEFAULT_REF_BON_FOURNITURE);
        assertThat(testGeuAaOuvrage.getRealisPorte()).isEqualTo(DEFAULT_REALIS_PORTE);
        assertThat(testGeuAaOuvrage.getRealisToles()).isEqualTo(DEFAULT_REALIS_TOLES);
        assertThat(testGeuAaOuvrage.getAnimateur()).isEqualTo(DEFAULT_ANIMATEUR);
        assertThat(testGeuAaOuvrage.getSuperviseur()).isEqualTo(DEFAULT_SUPERVISEUR);
        assertThat(testGeuAaOuvrage.getControleur()).isEqualTo(DEFAULT_CONTROLEUR);
    }

    @Test
    @Transactional
    public void createGeuAaOuvrageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geuAaOuvrageRepository.findAll().size();

        // Create the GeuAaOuvrage with an existing ID
        geuAaOuvrage.setId(1L);
        GeuAaOuvrageDTO geuAaOuvrageDTO = geuAaOuvrageMapper.toDto(geuAaOuvrage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeuAaOuvrageMockMvc.perform(post("/api/geu-aa-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuAaOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuAaOuvrage in the database
        List<GeuAaOuvrage> geuAaOuvrageList = geuAaOuvrageRepository.findAll();
        assertThat(geuAaOuvrageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeuAaOuvrages() throws Exception {
        // Initialize the database
        geuAaOuvrageRepository.saveAndFlush(geuAaOuvrage);

        // Get all the geuAaOuvrageList
        restGeuAaOuvrageMockMvc.perform(get("/api/geu-aa-ouvrages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geuAaOuvrage.getId().intValue())))
            .andExpect(jsonPath("$.[*].refOuvrage").value(hasItem(DEFAULT_REF_OUVRAGE)))
            .andExpect(jsonPath("$.[*].prjAppuis").value(hasItem(DEFAULT_PRJ_APPUIS)))
            .andExpect(jsonPath("$.[*].numCompteur").value(hasItem(DEFAULT_NUM_COMPTEUR)))
            .andExpect(jsonPath("$.[*].nomBenef").value(hasItem(DEFAULT_NOM_BENEF)))
            .andExpect(jsonPath("$.[*].prenomBenef").value(hasItem(DEFAULT_PRENOM_BENEF)))
            .andExpect(jsonPath("$.[*].professionBenef").value(hasItem(DEFAULT_PROFESSION_BENEF)))
            .andExpect(jsonPath("$.[*].nbUsagers").value(hasItem(DEFAULT_NB_USAGERS.intValue())))
            .andExpect(jsonPath("$.[*].codeUn").value(hasItem(DEFAULT_CODE_UN)))
            .andExpect(jsonPath("$.[*].dateRemiseDevis").value(hasItem(DEFAULT_DATE_REMISE_DEVIS.toString())))
            .andExpect(jsonPath("$.[*].dateDebutTravaux").value(hasItem(DEFAULT_DATE_DEBUT_TRAVAUX.toString())))
            .andExpect(jsonPath("$.[*].dateFinTravaux").value(hasItem(DEFAULT_DATE_FIN_TRAVAUX.toString())))
            .andExpect(jsonPath("$.[*].numNomRue").value(hasItem(DEFAULT_NUM_NOM_RUE)))
            .andExpect(jsonPath("$.[*].numNomPorte").value(hasItem(DEFAULT_NUM_NOM_PORTE)))
            .andExpect(jsonPath("$.[*].menage").value(hasItem(DEFAULT_MENAGE)))
            .andExpect(jsonPath("$.[*].subvOnea").value(hasItem(DEFAULT_SUBV_ONEA)))
            .andExpect(jsonPath("$.[*].subvProjet").value(hasItem(DEFAULT_SUBV_PROJET)))
            .andExpect(jsonPath("$.[*].autreSubv").value(hasItem(DEFAULT_AUTRE_SUBV)))
            .andExpect(jsonPath("$.[*].refBonFourniture").value(hasItem(DEFAULT_REF_BON_FOURNITURE)))
            .andExpect(jsonPath("$.[*].realisPorte").value(hasItem(DEFAULT_REALIS_PORTE)))
            .andExpect(jsonPath("$.[*].realisToles").value(hasItem(DEFAULT_REALIS_TOLES)))
            .andExpect(jsonPath("$.[*].animateur").value(hasItem(DEFAULT_ANIMATEUR)))
            .andExpect(jsonPath("$.[*].superviseur").value(hasItem(DEFAULT_SUPERVISEUR)))
            .andExpect(jsonPath("$.[*].controleur").value(hasItem(DEFAULT_CONTROLEUR)));
    }
    
    @Test
    @Transactional
    public void getGeuAaOuvrage() throws Exception {
        // Initialize the database
        geuAaOuvrageRepository.saveAndFlush(geuAaOuvrage);

        // Get the geuAaOuvrage
        restGeuAaOuvrageMockMvc.perform(get("/api/geu-aa-ouvrages/{id}", geuAaOuvrage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(geuAaOuvrage.getId().intValue()))
            .andExpect(jsonPath("$.refOuvrage").value(DEFAULT_REF_OUVRAGE))
            .andExpect(jsonPath("$.prjAppuis").value(DEFAULT_PRJ_APPUIS))
            .andExpect(jsonPath("$.numCompteur").value(DEFAULT_NUM_COMPTEUR))
            .andExpect(jsonPath("$.nomBenef").value(DEFAULT_NOM_BENEF))
            .andExpect(jsonPath("$.prenomBenef").value(DEFAULT_PRENOM_BENEF))
            .andExpect(jsonPath("$.professionBenef").value(DEFAULT_PROFESSION_BENEF))
            .andExpect(jsonPath("$.nbUsagers").value(DEFAULT_NB_USAGERS.intValue()))
            .andExpect(jsonPath("$.codeUn").value(DEFAULT_CODE_UN))
            .andExpect(jsonPath("$.dateRemiseDevis").value(DEFAULT_DATE_REMISE_DEVIS.toString()))
            .andExpect(jsonPath("$.dateDebutTravaux").value(DEFAULT_DATE_DEBUT_TRAVAUX.toString()))
            .andExpect(jsonPath("$.dateFinTravaux").value(DEFAULT_DATE_FIN_TRAVAUX.toString()))
            .andExpect(jsonPath("$.numNomRue").value(DEFAULT_NUM_NOM_RUE))
            .andExpect(jsonPath("$.numNomPorte").value(DEFAULT_NUM_NOM_PORTE))
            .andExpect(jsonPath("$.menage").value(DEFAULT_MENAGE))
            .andExpect(jsonPath("$.subvOnea").value(DEFAULT_SUBV_ONEA))
            .andExpect(jsonPath("$.subvProjet").value(DEFAULT_SUBV_PROJET))
            .andExpect(jsonPath("$.autreSubv").value(DEFAULT_AUTRE_SUBV))
            .andExpect(jsonPath("$.refBonFourniture").value(DEFAULT_REF_BON_FOURNITURE))
            .andExpect(jsonPath("$.realisPorte").value(DEFAULT_REALIS_PORTE))
            .andExpect(jsonPath("$.realisToles").value(DEFAULT_REALIS_TOLES))
            .andExpect(jsonPath("$.animateur").value(DEFAULT_ANIMATEUR))
            .andExpect(jsonPath("$.superviseur").value(DEFAULT_SUPERVISEUR))
            .andExpect(jsonPath("$.controleur").value(DEFAULT_CONTROLEUR));
    }
    @Test
    @Transactional
    public void getNonExistingGeuAaOuvrage() throws Exception {
        // Get the geuAaOuvrage
        restGeuAaOuvrageMockMvc.perform(get("/api/geu-aa-ouvrages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeuAaOuvrage() throws Exception {
        // Initialize the database
        geuAaOuvrageRepository.saveAndFlush(geuAaOuvrage);

        int databaseSizeBeforeUpdate = geuAaOuvrageRepository.findAll().size();

        // Update the geuAaOuvrage
        GeuAaOuvrage updatedGeuAaOuvrage = geuAaOuvrageRepository.findById(geuAaOuvrage.getId()).get();
        // Disconnect from session so that the updates on updatedGeuAaOuvrage are not directly saved in db
        em.detach(updatedGeuAaOuvrage);
        updatedGeuAaOuvrage
            .refOuvrage(UPDATED_REF_OUVRAGE)
            .prjAppuis(UPDATED_PRJ_APPUIS)
            .numCompteur(UPDATED_NUM_COMPTEUR)
            .nomBenef(UPDATED_NOM_BENEF)
            .prenomBenef(UPDATED_PRENOM_BENEF)
            .professionBenef(UPDATED_PROFESSION_BENEF)
            .nbUsagers(UPDATED_NB_USAGERS)
            .codeUn(UPDATED_CODE_UN)
            .dateRemiseDevis(UPDATED_DATE_REMISE_DEVIS)
            .dateDebutTravaux(UPDATED_DATE_DEBUT_TRAVAUX)
            .dateFinTravaux(UPDATED_DATE_FIN_TRAVAUX)
            .numNomRue(UPDATED_NUM_NOM_RUE)
            .numNomPorte(UPDATED_NUM_NOM_PORTE)
            .menage(UPDATED_MENAGE)
            .subvOnea(UPDATED_SUBV_ONEA)
            .subvProjet(UPDATED_SUBV_PROJET)
            .autreSubv(UPDATED_AUTRE_SUBV)
            .refBonFourniture(UPDATED_REF_BON_FOURNITURE)
            .realisPorte(UPDATED_REALIS_PORTE)
            .realisToles(UPDATED_REALIS_TOLES)
            .animateur(UPDATED_ANIMATEUR)
            .superviseur(UPDATED_SUPERVISEUR)
            .controleur(UPDATED_CONTROLEUR);
        GeuAaOuvrageDTO geuAaOuvrageDTO = geuAaOuvrageMapper.toDto(updatedGeuAaOuvrage);

        restGeuAaOuvrageMockMvc.perform(put("/api/geu-aa-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuAaOuvrageDTO)))
            .andExpect(status().isOk());

        // Validate the GeuAaOuvrage in the database
        List<GeuAaOuvrage> geuAaOuvrageList = geuAaOuvrageRepository.findAll();
        assertThat(geuAaOuvrageList).hasSize(databaseSizeBeforeUpdate);
        GeuAaOuvrage testGeuAaOuvrage = geuAaOuvrageList.get(geuAaOuvrageList.size() - 1);
        assertThat(testGeuAaOuvrage.getRefOuvrage()).isEqualTo(UPDATED_REF_OUVRAGE);
        assertThat(testGeuAaOuvrage.getPrjAppuis()).isEqualTo(UPDATED_PRJ_APPUIS);
        assertThat(testGeuAaOuvrage.getNumCompteur()).isEqualTo(UPDATED_NUM_COMPTEUR);
        assertThat(testGeuAaOuvrage.getNomBenef()).isEqualTo(UPDATED_NOM_BENEF);
        assertThat(testGeuAaOuvrage.getPrenomBenef()).isEqualTo(UPDATED_PRENOM_BENEF);
        assertThat(testGeuAaOuvrage.getProfessionBenef()).isEqualTo(UPDATED_PROFESSION_BENEF);
        assertThat(testGeuAaOuvrage.getNbUsagers()).isEqualTo(UPDATED_NB_USAGERS);
        assertThat(testGeuAaOuvrage.getCodeUn()).isEqualTo(UPDATED_CODE_UN);
        assertThat(testGeuAaOuvrage.getDateRemiseDevis()).isEqualTo(UPDATED_DATE_REMISE_DEVIS);
        assertThat(testGeuAaOuvrage.getDateDebutTravaux()).isEqualTo(UPDATED_DATE_DEBUT_TRAVAUX);
        assertThat(testGeuAaOuvrage.getDateFinTravaux()).isEqualTo(UPDATED_DATE_FIN_TRAVAUX);
        assertThat(testGeuAaOuvrage.getNumNomRue()).isEqualTo(UPDATED_NUM_NOM_RUE);
        assertThat(testGeuAaOuvrage.getNumNomPorte()).isEqualTo(UPDATED_NUM_NOM_PORTE);
        assertThat(testGeuAaOuvrage.getMenage()).isEqualTo(UPDATED_MENAGE);
        assertThat(testGeuAaOuvrage.getSubvOnea()).isEqualTo(UPDATED_SUBV_ONEA);
        assertThat(testGeuAaOuvrage.getSubvProjet()).isEqualTo(UPDATED_SUBV_PROJET);
        assertThat(testGeuAaOuvrage.getAutreSubv()).isEqualTo(UPDATED_AUTRE_SUBV);
        assertThat(testGeuAaOuvrage.getRefBonFourniture()).isEqualTo(UPDATED_REF_BON_FOURNITURE);
        assertThat(testGeuAaOuvrage.getRealisPorte()).isEqualTo(UPDATED_REALIS_PORTE);
        assertThat(testGeuAaOuvrage.getRealisToles()).isEqualTo(UPDATED_REALIS_TOLES);
        assertThat(testGeuAaOuvrage.getAnimateur()).isEqualTo(UPDATED_ANIMATEUR);
        assertThat(testGeuAaOuvrage.getSuperviseur()).isEqualTo(UPDATED_SUPERVISEUR);
        assertThat(testGeuAaOuvrage.getControleur()).isEqualTo(UPDATED_CONTROLEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingGeuAaOuvrage() throws Exception {
        int databaseSizeBeforeUpdate = geuAaOuvrageRepository.findAll().size();

        // Create the GeuAaOuvrage
        GeuAaOuvrageDTO geuAaOuvrageDTO = geuAaOuvrageMapper.toDto(geuAaOuvrage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeuAaOuvrageMockMvc.perform(put("/api/geu-aa-ouvrages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(geuAaOuvrageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeuAaOuvrage in the database
        List<GeuAaOuvrage> geuAaOuvrageList = geuAaOuvrageRepository.findAll();
        assertThat(geuAaOuvrageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeuAaOuvrage() throws Exception {
        // Initialize the database
        geuAaOuvrageRepository.saveAndFlush(geuAaOuvrage);

        int databaseSizeBeforeDelete = geuAaOuvrageRepository.findAll().size();

        // Delete the geuAaOuvrage
        restGeuAaOuvrageMockMvc.perform(delete("/api/geu-aa-ouvrages/{id}", geuAaOuvrage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeuAaOuvrage> geuAaOuvrageList = geuAaOuvrageRepository.findAll();
        assertThat(geuAaOuvrageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
