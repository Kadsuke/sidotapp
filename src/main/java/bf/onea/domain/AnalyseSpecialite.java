package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AnalyseSpecialite.
 */
@Entity
@Table(name = "analyse_specialite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalyseSpecialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "analysespecialite")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AnalyseParametre> analyseParametres = new HashSet<>();

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

    public AnalyseSpecialite libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<AnalyseParametre> getAnalyseParametres() {
        return analyseParametres;
    }

    public AnalyseSpecialite analyseParametres(Set<AnalyseParametre> analyseParametres) {
        this.analyseParametres = analyseParametres;
        return this;
    }

    public AnalyseSpecialite addAnalyseParametre(AnalyseParametre analyseParametre) {
        this.analyseParametres.add(analyseParametre);
        analyseParametre.setAnalysespecialite(this);
        return this;
    }

    public AnalyseSpecialite removeAnalyseParametre(AnalyseParametre analyseParametre) {
        this.analyseParametres.remove(analyseParametre);
        analyseParametre.setAnalysespecialite(null);
        return this;
    }

    public void setAnalyseParametres(Set<AnalyseParametre> analyseParametres) {
        this.analyseParametres = analyseParametres;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalyseSpecialite)) {
            return false;
        }
        return id != null && id.equals(((AnalyseSpecialite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnalyseSpecialite{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
