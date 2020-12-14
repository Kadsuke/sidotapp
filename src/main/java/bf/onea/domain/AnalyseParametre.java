package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AnalyseParametre.
 */
@Entity
@Table(name = "analyse_parametre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnalyseParametre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @ManyToOne
    @JsonIgnoreProperties(value = "analyseParametres", allowSetters = true)
    private AnalyseSpecialite analysespecialite;

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

    public AnalyseParametre libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public AnalyseSpecialite getAnalysespecialite() {
        return analysespecialite;
    }

    public AnalyseParametre analysespecialite(AnalyseSpecialite analyseSpecialite) {
        this.analysespecialite = analyseSpecialite;
        return this;
    }

    public void setAnalysespecialite(AnalyseSpecialite analyseSpecialite) {
        this.analysespecialite = analyseSpecialite;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalyseParametre)) {
            return false;
        }
        return id != null && id.equals(((AnalyseParametre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnalyseParametre{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
