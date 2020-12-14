package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoSection.
 */
@Entity
@Table(name = "geo_section")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoSection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "geosection")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoLot> geoLots = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoSections", allowSetters = true)
    private GeoSecteur geosecteur;

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

    public GeoSection libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoLot> getGeoLots() {
        return geoLots;
    }

    public GeoSection geoLots(Set<GeoLot> geoLots) {
        this.geoLots = geoLots;
        return this;
    }

    public GeoSection addGeoLot(GeoLot geoLot) {
        this.geoLots.add(geoLot);
        geoLot.setGeosection(this);
        return this;
    }

    public GeoSection removeGeoLot(GeoLot geoLot) {
        this.geoLots.remove(geoLot);
        geoLot.setGeosection(null);
        return this;
    }

    public void setGeoLots(Set<GeoLot> geoLots) {
        this.geoLots = geoLots;
    }

    public GeoSecteur getGeosecteur() {
        return geosecteur;
    }

    public GeoSection geosecteur(GeoSecteur geoSecteur) {
        this.geosecteur = geoSecteur;
        return this;
    }

    public void setGeosecteur(GeoSecteur geoSecteur) {
        this.geosecteur = geoSecteur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoSection)) {
            return false;
        }
        return id != null && id.equals(((GeoSection) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoSection{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
