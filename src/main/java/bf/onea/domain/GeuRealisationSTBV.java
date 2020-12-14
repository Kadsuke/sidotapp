package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A GeuRealisationSTBV.
 */
@Entity
@Table(name = "geu_realisation_stbv")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuRealisationSTBV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nb_camions", nullable = false)
    private String nbCamions;

    @NotNull
    @Column(name = "volume", nullable = false)
    private String volume;

    @NotNull
    @Column(name = "energie", nullable = false)
    private String energie;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRealisationSTBVS", allowSetters = true)
    private GeuSTBV geustbv;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNbCamions() {
        return nbCamions;
    }

    public GeuRealisationSTBV nbCamions(String nbCamions) {
        this.nbCamions = nbCamions;
        return this;
    }

    public void setNbCamions(String nbCamions) {
        this.nbCamions = nbCamions;
    }

    public String getVolume() {
        return volume;
    }

    public GeuRealisationSTBV volume(String volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEnergie() {
        return energie;
    }

    public GeuRealisationSTBV energie(String energie) {
        this.energie = energie;
        return this;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public GeuSTBV getGeustbv() {
        return geustbv;
    }

    public GeuRealisationSTBV geustbv(GeuSTBV geuSTBV) {
        this.geustbv = geuSTBV;
        return this;
    }

    public void setGeustbv(GeuSTBV geuSTBV) {
        this.geustbv = geuSTBV;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuRealisationSTBV)) {
            return false;
        }
        return id != null && id.equals(((GeuRealisationSTBV) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuRealisationSTBV{" +
            "id=" + getId() +
            ", nbCamions='" + getNbCamions() + "'" +
            ", volume='" + getVolume() + "'" +
            ", energie='" + getEnergie() + "'" +
            "}";
    }
}
