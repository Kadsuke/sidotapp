package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeuAaOuvrage.
 */
@Entity
@Table(name = "geu_aa_ouvrage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuAaOuvrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref_ouvrage")
    private String refOuvrage;

    @Column(name = "prj_appuis")
    private String prjAppuis;

    @Column(name = "num_compteur")
    private String numCompteur;

    @Column(name = "nom_benef")
    private String nomBenef;

    @Column(name = "prenom_benef")
    private String prenomBenef;

    @Column(name = "profession_benef")
    private String professionBenef;

    @Column(name = "nb_usagers")
    private Long nbUsagers;

    @Column(name = "code_un")
    private String codeUn;

    @Column(name = "date_remise_devis")
    private Instant dateRemiseDevis;

    @Column(name = "date_debut_travaux")
    private Instant dateDebutTravaux;

    @Column(name = "date_fin_travaux")
    private Instant dateFinTravaux;

    @Column(name = "num_nom_rue")
    private String numNomRue;

    @Column(name = "num_nom_porte")
    private String numNomPorte;

    @Column(name = "menage")
    private String menage;

    @Column(name = "subv_onea")
    private Integer subvOnea;

    @Column(name = "subv_projet")
    private Integer subvProjet;

    @Column(name = "autre_subv")
    private Integer autreSubv;

    @Column(name = "ref_bon_fourniture")
    private String refBonFourniture;

    @Column(name = "realis_porte")
    private Integer realisPorte;

    @Column(name = "realis_toles")
    private Integer realisToles;

    @Column(name = "animateur")
    private String animateur;

    @Column(name = "superviseur")
    private String superviseur;

    @Column(name = "controleur")
    private String controleur;

    @OneToMany(mappedBy = "geuaaouvrage")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRealisation> geuRealisations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private GeoParcelle geoparcelle;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private NatureOuvrage natureouvrage;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private TypeHabitation typehabitation;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private SourceApprovEp sourceapprovep;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private ModeEvacuationEauUsee modeevacuationeauusee;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private ModeEvacExcreta modeevacexcreta;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private Macon macon;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuAaOuvrages", allowSetters = true)
    private Prefabricant prefabricant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefOuvrage() {
        return refOuvrage;
    }

    public GeuAaOuvrage refOuvrage(String refOuvrage) {
        this.refOuvrage = refOuvrage;
        return this;
    }

    public void setRefOuvrage(String refOuvrage) {
        this.refOuvrage = refOuvrage;
    }

    public String getPrjAppuis() {
        return prjAppuis;
    }

    public GeuAaOuvrage prjAppuis(String prjAppuis) {
        this.prjAppuis = prjAppuis;
        return this;
    }

    public void setPrjAppuis(String prjAppuis) {
        this.prjAppuis = prjAppuis;
    }

    public String getNumCompteur() {
        return numCompteur;
    }

    public GeuAaOuvrage numCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
        return this;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

    public String getNomBenef() {
        return nomBenef;
    }

    public GeuAaOuvrage nomBenef(String nomBenef) {
        this.nomBenef = nomBenef;
        return this;
    }

    public void setNomBenef(String nomBenef) {
        this.nomBenef = nomBenef;
    }

    public String getPrenomBenef() {
        return prenomBenef;
    }

    public GeuAaOuvrage prenomBenef(String prenomBenef) {
        this.prenomBenef = prenomBenef;
        return this;
    }

    public void setPrenomBenef(String prenomBenef) {
        this.prenomBenef = prenomBenef;
    }

    public String getProfessionBenef() {
        return professionBenef;
    }

    public GeuAaOuvrage professionBenef(String professionBenef) {
        this.professionBenef = professionBenef;
        return this;
    }

    public void setProfessionBenef(String professionBenef) {
        this.professionBenef = professionBenef;
    }

    public Long getNbUsagers() {
        return nbUsagers;
    }

    public GeuAaOuvrage nbUsagers(Long nbUsagers) {
        this.nbUsagers = nbUsagers;
        return this;
    }

    public void setNbUsagers(Long nbUsagers) {
        this.nbUsagers = nbUsagers;
    }

    public String getCodeUn() {
        return codeUn;
    }

    public GeuAaOuvrage codeUn(String codeUn) {
        this.codeUn = codeUn;
        return this;
    }

    public void setCodeUn(String codeUn) {
        this.codeUn = codeUn;
    }

    public Instant getDateRemiseDevis() {
        return dateRemiseDevis;
    }

    public GeuAaOuvrage dateRemiseDevis(Instant dateRemiseDevis) {
        this.dateRemiseDevis = dateRemiseDevis;
        return this;
    }

    public void setDateRemiseDevis(Instant dateRemiseDevis) {
        this.dateRemiseDevis = dateRemiseDevis;
    }

    public Instant getDateDebutTravaux() {
        return dateDebutTravaux;
    }

    public GeuAaOuvrage dateDebutTravaux(Instant dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
        return this;
    }

    public void setDateDebutTravaux(Instant dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
    }

    public Instant getDateFinTravaux() {
        return dateFinTravaux;
    }

    public GeuAaOuvrage dateFinTravaux(Instant dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
        return this;
    }

    public void setDateFinTravaux(Instant dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
    }

    public String getNumNomRue() {
        return numNomRue;
    }

    public GeuAaOuvrage numNomRue(String numNomRue) {
        this.numNomRue = numNomRue;
        return this;
    }

    public void setNumNomRue(String numNomRue) {
        this.numNomRue = numNomRue;
    }

    public String getNumNomPorte() {
        return numNomPorte;
    }

    public GeuAaOuvrage numNomPorte(String numNomPorte) {
        this.numNomPorte = numNomPorte;
        return this;
    }

    public void setNumNomPorte(String numNomPorte) {
        this.numNomPorte = numNomPorte;
    }

    public String getMenage() {
        return menage;
    }

    public GeuAaOuvrage menage(String menage) {
        this.menage = menage;
        return this;
    }

    public void setMenage(String menage) {
        this.menage = menage;
    }

    public Integer getSubvOnea() {
        return subvOnea;
    }

    public GeuAaOuvrage subvOnea(Integer subvOnea) {
        this.subvOnea = subvOnea;
        return this;
    }

    public void setSubvOnea(Integer subvOnea) {
        this.subvOnea = subvOnea;
    }

    public Integer getSubvProjet() {
        return subvProjet;
    }

    public GeuAaOuvrage subvProjet(Integer subvProjet) {
        this.subvProjet = subvProjet;
        return this;
    }

    public void setSubvProjet(Integer subvProjet) {
        this.subvProjet = subvProjet;
    }

    public Integer getAutreSubv() {
        return autreSubv;
    }

    public GeuAaOuvrage autreSubv(Integer autreSubv) {
        this.autreSubv = autreSubv;
        return this;
    }

    public void setAutreSubv(Integer autreSubv) {
        this.autreSubv = autreSubv;
    }

    public String getRefBonFourniture() {
        return refBonFourniture;
    }

    public GeuAaOuvrage refBonFourniture(String refBonFourniture) {
        this.refBonFourniture = refBonFourniture;
        return this;
    }

    public void setRefBonFourniture(String refBonFourniture) {
        this.refBonFourniture = refBonFourniture;
    }

    public Integer getRealisPorte() {
        return realisPorte;
    }

    public GeuAaOuvrage realisPorte(Integer realisPorte) {
        this.realisPorte = realisPorte;
        return this;
    }

    public void setRealisPorte(Integer realisPorte) {
        this.realisPorte = realisPorte;
    }

    public Integer getRealisToles() {
        return realisToles;
    }

    public GeuAaOuvrage realisToles(Integer realisToles) {
        this.realisToles = realisToles;
        return this;
    }

    public void setRealisToles(Integer realisToles) {
        this.realisToles = realisToles;
    }

    public String getAnimateur() {
        return animateur;
    }

    public GeuAaOuvrage animateur(String animateur) {
        this.animateur = animateur;
        return this;
    }

    public void setAnimateur(String animateur) {
        this.animateur = animateur;
    }

    public String getSuperviseur() {
        return superviseur;
    }

    public GeuAaOuvrage superviseur(String superviseur) {
        this.superviseur = superviseur;
        return this;
    }

    public void setSuperviseur(String superviseur) {
        this.superviseur = superviseur;
    }

    public String getControleur() {
        return controleur;
    }

    public GeuAaOuvrage controleur(String controleur) {
        this.controleur = controleur;
        return this;
    }

    public void setControleur(String controleur) {
        this.controleur = controleur;
    }

    public Set<GeuRealisation> getGeuRealisations() {
        return geuRealisations;
    }

    public GeuAaOuvrage geuRealisations(Set<GeuRealisation> geuRealisations) {
        this.geuRealisations = geuRealisations;
        return this;
    }

    public GeuAaOuvrage addGeuRealisation(GeuRealisation geuRealisation) {
        this.geuRealisations.add(geuRealisation);
        geuRealisation.setGeuaaouvrage(this);
        return this;
    }

    public GeuAaOuvrage removeGeuRealisation(GeuRealisation geuRealisation) {
        this.geuRealisations.remove(geuRealisation);
        geuRealisation.setGeuaaouvrage(null);
        return this;
    }

    public void setGeuRealisations(Set<GeuRealisation> geuRealisations) {
        this.geuRealisations = geuRealisations;
    }

    public GeoParcelle getGeoparcelle() {
        return geoparcelle;
    }

    public GeuAaOuvrage geoparcelle(GeoParcelle geoParcelle) {
        this.geoparcelle = geoParcelle;
        return this;
    }

    public void setGeoparcelle(GeoParcelle geoParcelle) {
        this.geoparcelle = geoParcelle;
    }

    public NatureOuvrage getNatureouvrage() {
        return natureouvrage;
    }

    public GeuAaOuvrage natureouvrage(NatureOuvrage natureOuvrage) {
        this.natureouvrage = natureOuvrage;
        return this;
    }

    public void setNatureouvrage(NatureOuvrage natureOuvrage) {
        this.natureouvrage = natureOuvrage;
    }

    public TypeHabitation getTypehabitation() {
        return typehabitation;
    }

    public GeuAaOuvrage typehabitation(TypeHabitation typeHabitation) {
        this.typehabitation = typeHabitation;
        return this;
    }

    public void setTypehabitation(TypeHabitation typeHabitation) {
        this.typehabitation = typeHabitation;
    }

    public SourceApprovEp getSourceapprovep() {
        return sourceapprovep;
    }

    public GeuAaOuvrage sourceapprovep(SourceApprovEp sourceApprovEp) {
        this.sourceapprovep = sourceApprovEp;
        return this;
    }

    public void setSourceapprovep(SourceApprovEp sourceApprovEp) {
        this.sourceapprovep = sourceApprovEp;
    }

    public ModeEvacuationEauUsee getModeevacuationeauusee() {
        return modeevacuationeauusee;
    }

    public GeuAaOuvrage modeevacuationeauusee(ModeEvacuationEauUsee modeEvacuationEauUsee) {
        this.modeevacuationeauusee = modeEvacuationEauUsee;
        return this;
    }

    public void setModeevacuationeauusee(ModeEvacuationEauUsee modeEvacuationEauUsee) {
        this.modeevacuationeauusee = modeEvacuationEauUsee;
    }

    public ModeEvacExcreta getModeevacexcreta() {
        return modeevacexcreta;
    }

    public GeuAaOuvrage modeevacexcreta(ModeEvacExcreta modeEvacExcreta) {
        this.modeevacexcreta = modeEvacExcreta;
        return this;
    }

    public void setModeevacexcreta(ModeEvacExcreta modeEvacExcreta) {
        this.modeevacexcreta = modeEvacExcreta;
    }

    public Macon getMacon() {
        return macon;
    }

    public GeuAaOuvrage macon(Macon macon) {
        this.macon = macon;
        return this;
    }

    public void setMacon(Macon macon) {
        this.macon = macon;
    }

    public Prefabricant getPrefabricant() {
        return prefabricant;
    }

    public GeuAaOuvrage prefabricant(Prefabricant prefabricant) {
        this.prefabricant = prefabricant;
        return this;
    }

    public void setPrefabricant(Prefabricant prefabricant) {
        this.prefabricant = prefabricant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuAaOuvrage)) {
            return false;
        }
        return id != null && id.equals(((GeuAaOuvrage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuAaOuvrage{" +
            "id=" + getId() +
            ", refOuvrage='" + getRefOuvrage() + "'" +
            ", prjAppuis='" + getPrjAppuis() + "'" +
            ", numCompteur='" + getNumCompteur() + "'" +
            ", nomBenef='" + getNomBenef() + "'" +
            ", prenomBenef='" + getPrenomBenef() + "'" +
            ", professionBenef='" + getProfessionBenef() + "'" +
            ", nbUsagers=" + getNbUsagers() +
            ", codeUn='" + getCodeUn() + "'" +
            ", dateRemiseDevis='" + getDateRemiseDevis() + "'" +
            ", dateDebutTravaux='" + getDateDebutTravaux() + "'" +
            ", dateFinTravaux='" + getDateFinTravaux() + "'" +
            ", numNomRue='" + getNumNomRue() + "'" +
            ", numNomPorte='" + getNumNomPorte() + "'" +
            ", menage='" + getMenage() + "'" +
            ", subvOnea=" + getSubvOnea() +
            ", subvProjet=" + getSubvProjet() +
            ", autreSubv=" + getAutreSubv() +
            ", refBonFourniture='" + getRefBonFourniture() + "'" +
            ", realisPorte=" + getRealisPorte() +
            ", realisToles=" + getRealisToles() +
            ", animateur='" + getAnimateur() + "'" +
            ", superviseur='" + getSuperviseur() + "'" +
            ", controleur='" + getControleur() + "'" +
            "}";
    }
}
