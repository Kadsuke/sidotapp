package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PrevisionAssainissementAu.
 */
@Entity
@Table(name = "prevision_assainissement_au")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrevisionAssainissementAu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nb_latrine", nullable = false)
    private Integer nbLatrine;

    @NotNull
    @Column(name = "nb_puisard", nullable = false)
    private Integer nbPuisard;

    @NotNull
    @Column(name = "nb_public", nullable = false)
    private Integer nbPublic;

    @NotNull
    @Column(name = "nb_scolaire", nullable = false)
    private Integer nbScolaire;

    @NotNull
    @Column(name = "centre_de_sante", nullable = false)
    private Integer centreDeSante;

    @NotNull
    @Column(name = "population", nullable = false)
    private Integer population;

    @OneToOne
    @JoinColumn(unique = true)
    private RefAnnee refannee;

    @OneToOne
    @JoinColumn(unique = true)
    private Centre centre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbLatrine() {
        return nbLatrine;
    }

    public PrevisionAssainissementAu nbLatrine(Integer nbLatrine) {
        this.nbLatrine = nbLatrine;
        return this;
    }

    public void setNbLatrine(Integer nbLatrine) {
        this.nbLatrine = nbLatrine;
    }

    public Integer getNbPuisard() {
        return nbPuisard;
    }

    public PrevisionAssainissementAu nbPuisard(Integer nbPuisard) {
        this.nbPuisard = nbPuisard;
        return this;
    }

    public void setNbPuisard(Integer nbPuisard) {
        this.nbPuisard = nbPuisard;
    }

    public Integer getNbPublic() {
        return nbPublic;
    }

    public PrevisionAssainissementAu nbPublic(Integer nbPublic) {
        this.nbPublic = nbPublic;
        return this;
    }

    public void setNbPublic(Integer nbPublic) {
        this.nbPublic = nbPublic;
    }

    public Integer getNbScolaire() {
        return nbScolaire;
    }

    public PrevisionAssainissementAu nbScolaire(Integer nbScolaire) {
        this.nbScolaire = nbScolaire;
        return this;
    }

    public void setNbScolaire(Integer nbScolaire) {
        this.nbScolaire = nbScolaire;
    }

    public Integer getCentreDeSante() {
        return centreDeSante;
    }

    public PrevisionAssainissementAu centreDeSante(Integer centreDeSante) {
        this.centreDeSante = centreDeSante;
        return this;
    }

    public void setCentreDeSante(Integer centreDeSante) {
        this.centreDeSante = centreDeSante;
    }

    public Integer getPopulation() {
        return population;
    }

    public PrevisionAssainissementAu population(Integer population) {
        this.population = population;
        return this;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public RefAnnee getRefannee() {
        return refannee;
    }

    public PrevisionAssainissementAu refannee(RefAnnee refAnnee) {
        this.refannee = refAnnee;
        return this;
    }

    public void setRefannee(RefAnnee refAnnee) {
        this.refannee = refAnnee;
    }

    public Centre getCentre() {
        return centre;
    }

    public PrevisionAssainissementAu centre(Centre centre) {
        this.centre = centre;
        return this;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisionAssainissementAu)) {
            return false;
        }
        return id != null && id.equals(((PrevisionAssainissementAu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionAssainissementAu{" +
            "id=" + getId() +
            ", nbLatrine=" + getNbLatrine() +
            ", nbPuisard=" + getNbPuisard() +
            ", nbPublic=" + getNbPublic() +
            ", nbScolaire=" + getNbScolaire() +
            ", centreDeSante=" + getCentreDeSante() +
            ", population=" + getPopulation() +
            "}";
    }
}
