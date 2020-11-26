package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoLot.
 */
@Entity
@Table(name = "geo_lot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoLot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geolot")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoParcelle> geoParcelles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoLots", allowSetters = true)
    private GeoSection geosection;

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

    public GeoLot libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoParcelle> getGeoParcelles() {
        return geoParcelles;
    }

    public GeoLot geoParcelles(Set<GeoParcelle> geoParcelles) {
        this.geoParcelles = geoParcelles;
        return this;
    }

    public GeoLot addGeoParcelle(GeoParcelle geoParcelle) {
        this.geoParcelles.add(geoParcelle);
        geoParcelle.setGeolot(this);
        return this;
    }

    public GeoLot removeGeoParcelle(GeoParcelle geoParcelle) {
        this.geoParcelles.remove(geoParcelle);
        geoParcelle.setGeolot(null);
        return this;
    }

    public void setGeoParcelles(Set<GeoParcelle> geoParcelles) {
        this.geoParcelles = geoParcelles;
    }

    public GeoSection getGeosection() {
        return geosection;
    }

    public GeoLot geosection(GeoSection geoSection) {
        this.geosection = geoSection;
        return this;
    }

    public void setGeosection(GeoSection geoSection) {
        this.geosection = geoSection;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoLot)) {
            return false;
        }
        return id != null && id.equals(((GeoLot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoLot{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
