package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeuUsage.
 */
@Entity
@Table(name = "geu_usage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuUsage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "geuusage")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRaccordement> geuRaccordements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public GeuUsage libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeuRaccordement> getGeuRaccordements() {
        return geuRaccordements;
    }

    public GeuUsage geuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
        return this;
    }

    public GeuUsage addGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.add(geuRaccordement);
        geuRaccordement.setGeuusage(this);
        return this;
    }

    public GeuUsage removeGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.remove(geuRaccordement);
        geuRaccordement.setGeuusage(null);
        return this;
    }

    public void setGeuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuUsage)) {
            return false;
        }
        return id != null && id.equals(((GeuUsage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuUsage{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
