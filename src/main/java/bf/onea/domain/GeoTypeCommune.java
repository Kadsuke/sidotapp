package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoTypeCommune.
 */
@Entity
@Table(name = "geo_type_commune")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoTypeCommune implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geotypecommune")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoCommune> geoCommunes = new HashSet<>();

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

    public GeoTypeCommune libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoCommune> getGeoCommunes() {
        return geoCommunes;
    }

    public GeoTypeCommune geoCommunes(Set<GeoCommune> geoCommunes) {
        this.geoCommunes = geoCommunes;
        return this;
    }

    public GeoTypeCommune addGeoCommune(GeoCommune geoCommune) {
        this.geoCommunes.add(geoCommune);
        geoCommune.setGeotypecommune(this);
        return this;
    }

    public GeoTypeCommune removeGeoCommune(GeoCommune geoCommune) {
        this.geoCommunes.remove(geoCommune);
        geoCommune.setGeotypecommune(null);
        return this;
    }

    public void setGeoCommunes(Set<GeoCommune> geoCommunes) {
        this.geoCommunes = geoCommunes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoTypeCommune)) {
            return false;
        }
        return id != null && id.equals(((GeoTypeCommune) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoTypeCommune{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
