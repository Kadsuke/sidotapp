package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoProvince.
 */
@Entity
@Table(name = "geo_province")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoProvince implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geoprovince")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoCommune> geoCommunes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoProvinces", allowSetters = true)
    private GeoRegion georegion;

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

    public GeoProvince libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoCommune> getGeoCommunes() {
        return geoCommunes;
    }

    public GeoProvince geoCommunes(Set<GeoCommune> geoCommunes) {
        this.geoCommunes = geoCommunes;
        return this;
    }

    public GeoProvince addGeoCommune(GeoCommune geoCommune) {
        this.geoCommunes.add(geoCommune);
        geoCommune.setGeoprovince(this);
        return this;
    }

    public GeoProvince removeGeoCommune(GeoCommune geoCommune) {
        this.geoCommunes.remove(geoCommune);
        geoCommune.setGeoprovince(null);
        return this;
    }

    public void setGeoCommunes(Set<GeoCommune> geoCommunes) {
        this.geoCommunes = geoCommunes;
    }

    public GeoRegion getGeoregion() {
        return georegion;
    }

    public GeoProvince georegion(GeoRegion geoRegion) {
        this.georegion = geoRegion;
        return this;
    }

    public void setGeoregion(GeoRegion geoRegion) {
        this.georegion = geoRegion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoProvince)) {
            return false;
        }
        return id != null && id.equals(((GeoProvince) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoProvince{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
