package bf.onea.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PrevisionPsa.
 */
@Entity
@Table(name = "prevision_psa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrevisionPsa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "elabore")
    private Integer elabore;

    @Column(name = "mise_en_oeuvre")
    private Integer miseEnOeuvre;

    @OneToOne
    @JoinColumn(unique = true)
    private Centre centre;

    @OneToOne
    @JoinColumn(unique = true)
    private RefAnnee refAnnee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getElabore() {
        return elabore;
    }

    public PrevisionPsa elabore(Integer elabore) {
        this.elabore = elabore;
        return this;
    }

    public void setElabore(Integer elabore) {
        this.elabore = elabore;
    }

    public Integer getMiseEnOeuvre() {
        return miseEnOeuvre;
    }

    public PrevisionPsa miseEnOeuvre(Integer miseEnOeuvre) {
        this.miseEnOeuvre = miseEnOeuvre;
        return this;
    }

    public void setMiseEnOeuvre(Integer miseEnOeuvre) {
        this.miseEnOeuvre = miseEnOeuvre;
    }

    public Centre getCentre() {
        return centre;
    }

    public PrevisionPsa centre(Centre centre) {
        this.centre = centre;
        return this;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public RefAnnee getRefAnnee() {
        return refAnnee;
    }

    public PrevisionPsa refAnnee(RefAnnee refAnnee) {
        this.refAnnee = refAnnee;
        return this;
    }

    public void setRefAnnee(RefAnnee refAnnee) {
        this.refAnnee = refAnnee;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisionPsa)) {
            return false;
        }
        return id != null && id.equals(((PrevisionPsa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionPsa{" +
            "id=" + getId() +
            ", elabore=" + getElabore() +
            ", miseEnOeuvre=" + getMiseEnOeuvre() +
            "}";
    }
}
