package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.PrevisionAssainissementAu} entity.
 */
public class PrevisionAssainissementAuDTO implements Serializable {
    
    private Long id;

    private Integer nbLatrine;

    private Integer nbPuisard;

    private Integer nbPublic;

    private Integer nbScolaire;

    private Integer centreDeSante;

    private Integer population;


    private Long refanneeId;

    private Long centreId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbLatrine() {
        return nbLatrine;
    }

    public void setNbLatrine(Integer nbLatrine) {
        this.nbLatrine = nbLatrine;
    }

    public Integer getNbPuisard() {
        return nbPuisard;
    }

    public void setNbPuisard(Integer nbPuisard) {
        this.nbPuisard = nbPuisard;
    }

    public Integer getNbPublic() {
        return nbPublic;
    }

    public void setNbPublic(Integer nbPublic) {
        this.nbPublic = nbPublic;
    }

    public Integer getNbScolaire() {
        return nbScolaire;
    }

    public void setNbScolaire(Integer nbScolaire) {
        this.nbScolaire = nbScolaire;
    }

    public Integer getCentreDeSante() {
        return centreDeSante;
    }

    public void setCentreDeSante(Integer centreDeSante) {
        this.centreDeSante = centreDeSante;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Long getRefanneeId() {
        return refanneeId;
    }

    public void setRefanneeId(Long refAnneeId) {
        this.refanneeId = refAnneeId;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisionAssainissementAuDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisionAssainissementAuDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionAssainissementAuDTO{" +
            "id=" + getId() +
            ", nbLatrine=" + getNbLatrine() +
            ", nbPuisard=" + getNbPuisard() +
            ", nbPublic=" + getNbPublic() +
            ", nbScolaire=" + getNbScolaire() +
            ", centreDeSante=" + getCentreDeSante() +
            ", population=" + getPopulation() +
            ", refanneeId=" + getRefanneeId() +
            ", centreId=" + getCentreId() +
            "}";
    }
}
