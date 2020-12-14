package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GeuPrevisionSTEP.
 */
@Entity
@Table(name = "geu_prevision_step")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuPrevisionSTEP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_prev_step", nullable = false)
    private Instant datePrevStep;

    @NotNull
    @Column(name = "volume_prev_step", nullable = false)
    private String volumePrevStep;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuPrevisionSTEPS", allowSetters = true)
    private GeuSTEP geustep;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDatePrevStep() {
        return datePrevStep;
    }

    public GeuPrevisionSTEP datePrevStep(Instant datePrevStep) {
        this.datePrevStep = datePrevStep;
        return this;
    }

    public void setDatePrevStep(Instant datePrevStep) {
        this.datePrevStep = datePrevStep;
    }

    public String getVolumePrevStep() {
        return volumePrevStep;
    }

    public GeuPrevisionSTEP volumePrevStep(String volumePrevStep) {
        this.volumePrevStep = volumePrevStep;
        return this;
    }

    public void setVolumePrevStep(String volumePrevStep) {
        this.volumePrevStep = volumePrevStep;
    }

    public GeuSTEP getGeustep() {
        return geustep;
    }

    public GeuPrevisionSTEP geustep(GeuSTEP geuSTEP) {
        this.geustep = geuSTEP;
        return this;
    }

    public void setGeustep(GeuSTEP geuSTEP) {
        this.geustep = geuSTEP;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuPrevisionSTEP)) {
            return false;
        }
        return id != null && id.equals(((GeuPrevisionSTEP) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPrevisionSTEP{" +
            "id=" + getId() +
            ", datePrevStep='" + getDatePrevStep() + "'" +
            ", volumePrevStep='" + getVolumePrevStep() + "'" +
            "}";
    }
}
