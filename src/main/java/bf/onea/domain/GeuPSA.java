package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GeuPSA.
 */
@Entity
@Table(name = "geu_psa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuPSA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_elaboration")
    private Instant dateElaboration;

    @Column(name = "date_mise_en_oeuvre")
    private Instant dateMiseEnOeuvre;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuPSAS", allowSetters = true)
    private GeoCommune geocommune;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateElaboration() {
        return dateElaboration;
    }

    public GeuPSA dateElaboration(Instant dateElaboration) {
        this.dateElaboration = dateElaboration;
        return this;
    }

    public void setDateElaboration(Instant dateElaboration) {
        this.dateElaboration = dateElaboration;
    }

    public Instant getDateMiseEnOeuvre() {
        return dateMiseEnOeuvre;
    }

    public GeuPSA dateMiseEnOeuvre(Instant dateMiseEnOeuvre) {
        this.dateMiseEnOeuvre = dateMiseEnOeuvre;
        return this;
    }

    public void setDateMiseEnOeuvre(Instant dateMiseEnOeuvre) {
        this.dateMiseEnOeuvre = dateMiseEnOeuvre;
    }

    public GeoCommune getGeocommune() {
        return geocommune;
    }

    public GeuPSA geocommune(GeoCommune geoCommune) {
        this.geocommune = geoCommune;
        return this;
    }

    public void setGeocommune(GeoCommune geoCommune) {
        this.geocommune = geoCommune;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuPSA)) {
            return false;
        }
        return id != null && id.equals(((GeuPSA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPSA{" +
            "id=" + getId() +
            ", dateElaboration='" + getDateElaboration() + "'" +
            ", dateMiseEnOeuvre='" + getDateMiseEnOeuvre() + "'" +
            "}";
    }
}
