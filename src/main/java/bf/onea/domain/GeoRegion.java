package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoRegion.
 */
@Entity
@Table(name = "geo_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "georegion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoProvince> geoProvinces = new HashSet<>();

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

    public GeoRegion libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoProvince> getGeoProvinces() {
        return geoProvinces;
    }

    public GeoRegion geoProvinces(Set<GeoProvince> geoProvinces) {
        this.geoProvinces = geoProvinces;
        return this;
    }

    public GeoRegion addGeoProvince(GeoProvince geoProvince) {
        this.geoProvinces.add(geoProvince);
        geoProvince.setGeoregion(this);
        return this;
    }

    public GeoRegion removeGeoProvince(GeoProvince geoProvince) {
        this.geoProvinces.remove(geoProvince);
        geoProvince.setGeoregion(null);
        return this;
    }

    public void setGeoProvinces(Set<GeoProvince> geoProvinces) {
        this.geoProvinces = geoProvinces;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoRegion)) {
            return false;
        }
        return id != null && id.equals(((GeoRegion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoRegion{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
