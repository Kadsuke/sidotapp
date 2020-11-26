package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoSecteur.
 */
@Entity
@Table(name = "geo_secteur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoSecteur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geosecteur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoSection> geoSections = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoSecteurs", allowSetters = true)
    private GeoLocalite geolocalite;

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

    public GeoSecteur libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoSection> getGeoSections() {
        return geoSections;
    }

    public GeoSecteur geoSections(Set<GeoSection> geoSections) {
        this.geoSections = geoSections;
        return this;
    }

    public GeoSecteur addGeoSection(GeoSection geoSection) {
        this.geoSections.add(geoSection);
        geoSection.setGeosecteur(this);
        return this;
    }

    public GeoSecteur removeGeoSection(GeoSection geoSection) {
        this.geoSections.remove(geoSection);
        geoSection.setGeosecteur(null);
        return this;
    }

    public void setGeoSections(Set<GeoSection> geoSections) {
        this.geoSections = geoSections;
    }

    public GeoLocalite getGeolocalite() {
        return geolocalite;
    }

    public GeoSecteur geolocalite(GeoLocalite geoLocalite) {
        this.geolocalite = geoLocalite;
        return this;
    }

    public void setGeolocalite(GeoLocalite geoLocalite) {
        this.geolocalite = geoLocalite;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoSecteur)) {
            return false;
        }
        return id != null && id.equals(((GeoSecteur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoSecteur{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
