package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuRaccordement} entity.
 */
public class GeuRaccordementDTO implements Serializable {
    
    private Long id;

    private Long numAbonnement;

    private String nom;

    private String prenom;

    private String adresse;

    private String proprietaireParacelle;

    private String entreprise;

    private String otherUsage;


    private Long geoparcelleId;

    private Long agentId;

    private Long tacheronsId;

    private Long geuusageId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumAbonnement() {
        return numAbonnement;
    }

    public void setNumAbonnement(Long numAbonnement) {
        this.numAbonnement = numAbonnement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProprietaireParacelle() {
        return proprietaireParacelle;
    }

    public void setProprietaireParacelle(String proprietaireParacelle) {
        this.proprietaireParacelle = proprietaireParacelle;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getOtherUsage() {
        return otherUsage;
    }

    public void setOtherUsage(String otherUsage) {
        this.otherUsage = otherUsage;
    }

    public Long getGeoparcelleId() {
        return geoparcelleId;
    }

    public void setGeoparcelleId(Long geoParcelleId) {
        this.geoparcelleId = geoParcelleId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getTacheronsId() {
        return tacheronsId;
    }

    public void setTacheronsId(Long tacheronsId) {
        this.tacheronsId = tacheronsId;
    }

    public Long getGeuusageId() {
        return geuusageId;
    }

    public void setGeuusageId(Long geuUsageId) {
        this.geuusageId = geuUsageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuRaccordementDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuRaccordementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuRaccordementDTO{" +
            "id=" + getId() +
            ", numAbonnement=" + getNumAbonnement() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", proprietaireParacelle='" + getProprietaireParacelle() + "'" +
            ", entreprise='" + getEntreprise() + "'" +
            ", otherUsage='" + getOtherUsage() + "'" +
            ", geoparcelleId=" + getGeoparcelleId() +
            ", agentId=" + getAgentId() +
            ", tacheronsId=" + getTacheronsId() +
            ", geuusageId=" + getGeuusageId() +
            "}";
    }
}
