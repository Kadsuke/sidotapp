package bf.onea.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuAaOuvrage} entity.
 */
public class GeuAaOuvrageDTO implements Serializable {
    
    private Long id;

    private String refOuvrage;

    private String prjAppuis;

    private String numCompteur;

    private String nomBenef;

    private String prenomBenef;

    private String professionBenef;

    private Long nbUsagers;

    private String codeUn;

    private Instant dateRemiseDevis;

    private Instant dateDebutTravaux;

    private Instant dateFinTravaux;

    private String numNomRue;

    private String numNomPorte;

    private String menage;

    private Integer subvOnea;

    private Integer subvProjet;

    private Integer autreSubv;

    private String refBonFourniture;

    private Integer realisPorte;

    private Integer realisToles;

    private String animateur;

    private String superviseur;

    private String controleur;


    private Long geoparcelleId;

    private Long natureouvrageId;

    private Long typehabitationId;

    private Long sourceapprovepId;

    private Long modeevacuationeauuseeId;

    private Long modeevacexcretaId;

    private Long maconId;

    private Long prefabricantId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefOuvrage() {
        return refOuvrage;
    }

    public void setRefOuvrage(String refOuvrage) {
        this.refOuvrage = refOuvrage;
    }

    public String getPrjAppuis() {
        return prjAppuis;
    }

    public void setPrjAppuis(String prjAppuis) {
        this.prjAppuis = prjAppuis;
    }

    public String getNumCompteur() {
        return numCompteur;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

    public String getNomBenef() {
        return nomBenef;
    }

    public void setNomBenef(String nomBenef) {
        this.nomBenef = nomBenef;
    }

    public String getPrenomBenef() {
        return prenomBenef;
    }

    public void setPrenomBenef(String prenomBenef) {
        this.prenomBenef = prenomBenef;
    }

    public String getProfessionBenef() {
        return professionBenef;
    }

    public void setProfessionBenef(String professionBenef) {
        this.professionBenef = professionBenef;
    }

    public Long getNbUsagers() {
        return nbUsagers;
    }

    public void setNbUsagers(Long nbUsagers) {
        this.nbUsagers = nbUsagers;
    }

    public String getCodeUn() {
        return codeUn;
    }

    public void setCodeUn(String codeUn) {
        this.codeUn = codeUn;
    }

    public Instant getDateRemiseDevis() {
        return dateRemiseDevis;
    }

    public void setDateRemiseDevis(Instant dateRemiseDevis) {
        this.dateRemiseDevis = dateRemiseDevis;
    }

    public Instant getDateDebutTravaux() {
        return dateDebutTravaux;
    }

    public void setDateDebutTravaux(Instant dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
    }

    public Instant getDateFinTravaux() {
        return dateFinTravaux;
    }

    public void setDateFinTravaux(Instant dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
    }

    public String getNumNomRue() {
        return numNomRue;
    }

    public void setNumNomRue(String numNomRue) {
        this.numNomRue = numNomRue;
    }

    public String getNumNomPorte() {
        return numNomPorte;
    }

    public void setNumNomPorte(String numNomPorte) {
        this.numNomPorte = numNomPorte;
    }

    public String getMenage() {
        return menage;
    }

    public void setMenage(String menage) {
        this.menage = menage;
    }

    public Integer getSubvOnea() {
        return subvOnea;
    }

    public void setSubvOnea(Integer subvOnea) {
        this.subvOnea = subvOnea;
    }

    public Integer getSubvProjet() {
        return subvProjet;
    }

    public void setSubvProjet(Integer subvProjet) {
        this.subvProjet = subvProjet;
    }

    public Integer getAutreSubv() {
        return autreSubv;
    }

    public void setAutreSubv(Integer autreSubv) {
        this.autreSubv = autreSubv;
    }

    public String getRefBonFourniture() {
        return refBonFourniture;
    }

    public void setRefBonFourniture(String refBonFourniture) {
        this.refBonFourniture = refBonFourniture;
    }

    public Integer getRealisPorte() {
        return realisPorte;
    }

    public void setRealisPorte(Integer realisPorte) {
        this.realisPorte = realisPorte;
    }

    public Integer getRealisToles() {
        return realisToles;
    }

    public void setRealisToles(Integer realisToles) {
        this.realisToles = realisToles;
    }

    public String getAnimateur() {
        return animateur;
    }

    public void setAnimateur(String animateur) {
        this.animateur = animateur;
    }

    public String getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(String superviseur) {
        this.superviseur = superviseur;
    }

    public String getControleur() {
        return controleur;
    }

    public void setControleur(String controleur) {
        this.controleur = controleur;
    }

    public Long getGeoparcelleId() {
        return geoparcelleId;
    }

    public void setGeoparcelleId(Long geoParcelleId) {
        this.geoparcelleId = geoParcelleId;
    }

    public Long getNatureouvrageId() {
        return natureouvrageId;
    }

    public void setNatureouvrageId(Long natureOuvrageId) {
        this.natureouvrageId = natureOuvrageId;
    }

    public Long getTypehabitationId() {
        return typehabitationId;
    }

    public void setTypehabitationId(Long typeHabitationId) {
        this.typehabitationId = typeHabitationId;
    }

    public Long getSourceapprovepId() {
        return sourceapprovepId;
    }

    public void setSourceapprovepId(Long sourceApprovEpId) {
        this.sourceapprovepId = sourceApprovEpId;
    }

    public Long getModeevacuationeauuseeId() {
        return modeevacuationeauuseeId;
    }

    public void setModeevacuationeauuseeId(Long modeEvacuationEauUseeId) {
        this.modeevacuationeauuseeId = modeEvacuationEauUseeId;
    }

    public Long getModeevacexcretaId() {
        return modeevacexcretaId;
    }

    public void setModeevacexcretaId(Long modeEvacExcretaId) {
        this.modeevacexcretaId = modeEvacExcretaId;
    }

    public Long getMaconId() {
        return maconId;
    }

    public void setMaconId(Long maconId) {
        this.maconId = maconId;
    }

    public Long getPrefabricantId() {
        return prefabricantId;
    }

    public void setPrefabricantId(Long prefabricantId) {
        this.prefabricantId = prefabricantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuAaOuvrageDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuAaOuvrageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuAaOuvrageDTO{" +
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
            ", geoparcelleId=" + getGeoparcelleId() +
            ", natureouvrageId=" + getNatureouvrageId() +
            ", typehabitationId=" + getTypehabitationId() +
            ", sourceapprovepId=" + getSourceapprovepId() +
            ", modeevacuationeauuseeId=" + getModeevacuationeauuseeId() +
            ", modeevacexcretaId=" + getModeevacexcretaId() +
            ", maconId=" + getMaconId() +
            ", prefabricantId=" + getPrefabricantId() +
            "}";
    }
}
