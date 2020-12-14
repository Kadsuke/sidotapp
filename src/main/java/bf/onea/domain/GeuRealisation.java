package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A GeuRealisation.
 */
@Entity
@Table(name = "geu_realisation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuRealisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nb_realisation", nullable = false)
    private Float nbRealisation;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRealisations", allowSetters = true)
    private GeuAaOuvrage geuaaouvrage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNbRealisation() {
        return nbRealisation;
    }

    public GeuRealisation nbRealisation(Float nbRealisation) {
        this.nbRealisation = nbRealisation;
        return this;
    }

    public void setNbRealisation(Float nbRealisation) {
        this.nbRealisation = nbRealisation;
    }

    public GeuAaOuvrage getGeuaaouvrage() {
        return geuaaouvrage;
    }

    public GeuRealisation geuaaouvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuaaouvrage = geuAaOuvrage;
        return this;
    }

    public void setGeuaaouvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuaaouvrage = geuAaOuvrage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuRealisation)) {
            return false;
        }
        return id != null && id.equals(((GeuRealisation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuRealisation{" +
            "id=" + getId() +
            ", nbRealisation=" + getNbRealisation() +
            "}";
    }
}
