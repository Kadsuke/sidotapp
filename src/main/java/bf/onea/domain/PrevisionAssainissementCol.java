package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PrevisionAssainissementCol.
 */
@Entity
@Table(name = "prevision_assainissement_col")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrevisionAssainissementCol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nb_step", nullable = false)
    private Integer nbStep;

    @NotNull
    @Column(name = "nb_stbv", nullable = false)
    private Integer nbStbv;

    @NotNull
    @Column(name = "reseaux", nullable = false)
    private Float reseaux;

    @NotNull
    @Column(name = "nb_raccordement", nullable = false)
    private Integer nbRaccordement;

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

    public Integer getNbStep() {
        return nbStep;
    }

    public PrevisionAssainissementCol nbStep(Integer nbStep) {
        this.nbStep = nbStep;
        return this;
    }

    public void setNbStep(Integer nbStep) {
        this.nbStep = nbStep;
    }

    public Integer getNbStbv() {
        return nbStbv;
    }

    public PrevisionAssainissementCol nbStbv(Integer nbStbv) {
        this.nbStbv = nbStbv;
        return this;
    }

    public void setNbStbv(Integer nbStbv) {
        this.nbStbv = nbStbv;
    }

    public Float getReseaux() {
        return reseaux;
    }

    public PrevisionAssainissementCol reseaux(Float reseaux) {
        this.reseaux = reseaux;
        return this;
    }

    public void setReseaux(Float reseaux) {
        this.reseaux = reseaux;
    }

    public Integer getNbRaccordement() {
        return nbRaccordement;
    }

    public PrevisionAssainissementCol nbRaccordement(Integer nbRaccordement) {
        this.nbRaccordement = nbRaccordement;
        return this;
    }

    public void setNbRaccordement(Integer nbRaccordement) {
        this.nbRaccordement = nbRaccordement;
    }

    public RefAnnee getRefannee() {
        return refannee;
    }

    public PrevisionAssainissementCol refannee(RefAnnee refAnnee) {
        this.refannee = refAnnee;
        return this;
    }

    public void setRefannee(RefAnnee refAnnee) {
        this.refannee = refAnnee;
    }

    public Centre getCentre() {
        return centre;
    }

    public PrevisionAssainissementCol centre(Centre centre) {
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
        if (!(o instanceof PrevisionAssainissementCol)) {
            return false;
        }
        return id != null && id.equals(((PrevisionAssainissementCol) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionAssainissementCol{" +
            "id=" + getId() +
            ", nbStep=" + getNbStep() +
            ", nbStbv=" + getNbStbv() +
            ", reseaux=" + getReseaux() +
            ", nbRaccordement=" + getNbRaccordement() +
            "}";
    }
}
