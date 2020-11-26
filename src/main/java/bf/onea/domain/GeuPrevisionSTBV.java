package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A GeuPrevisionSTBV.
 */
@Entity
@Table(name = "geu_prevision_stbv")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuPrevisionSTBV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nb_camions")
    private String nbCamions;

    @Column(name = "volume")
    private String volume;

    @Column(name = "energie")
    private String energie;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuPrevisionSTBVS", allowSetters = true)
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

    public GeuPrevisionSTBV nbCamions(String nbCamions) {
        this.nbCamions = nbCamions;
        return this;
    }

    public void setNbCamions(String nbCamions) {
        this.nbCamions = nbCamions;
    }

    public String getVolume() {
        return volume;
    }

    public GeuPrevisionSTBV volume(String volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEnergie() {
        return energie;
    }

    public GeuPrevisionSTBV energie(String energie) {
        this.energie = energie;
        return this;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public GeuSTBV getGeustbv() {
        return geustbv;
    }

    public GeuPrevisionSTBV geustbv(GeuSTBV geuSTBV) {
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
        if (!(o instanceof GeuPrevisionSTBV)) {
            return false;
        }
        return id != null && id.equals(((GeuPrevisionSTBV) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPrevisionSTBV{" +
            "id=" + getId() +
            ", nbCamions='" + getNbCamions() + "'" +
            ", volume='" + getVolume() + "'" +
            ", energie='" + getEnergie() + "'" +
            "}";
    }
}
