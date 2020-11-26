package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoCommune.
 */
@Entity
@Table(name = "geo_commune")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoCommune implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geocommune")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoLocalite> geoLocalites = new HashSet<>();

    @OneToMany(mappedBy = "geocommune")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuPSA> geuPSAS = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoCommunes", allowSetters = true)
    private GeoProvince geoprovince;

    @ManyToOne
    @JsonIgnoreProperties(value = "geoCommunes", allowSetters = true)
    private GeoTypeCommune geotypecommune;

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

    public GeoCommune libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoLocalite> getGeoLocalites() {
        return geoLocalites;
    }

    public GeoCommune geoLocalites(Set<GeoLocalite> geoLocalites) {
        this.geoLocalites = geoLocalites;
        return this;
    }

    public GeoCommune addGeoLocalite(GeoLocalite geoLocalite) {
        this.geoLocalites.add(geoLocalite);
        geoLocalite.setGeocommune(this);
        return this;
    }

    public GeoCommune removeGeoLocalite(GeoLocalite geoLocalite) {
        this.geoLocalites.remove(geoLocalite);
        geoLocalite.setGeocommune(null);
        return this;
    }

    public void setGeoLocalites(Set<GeoLocalite> geoLocalites) {
        this.geoLocalites = geoLocalites;
    }

    public Set<GeuPSA> getGeuPSAS() {
        return geuPSAS;
    }

    public GeoCommune geuPSAS(Set<GeuPSA> geuPSAS) {
        this.geuPSAS = geuPSAS;
        return this;
    }

    public GeoCommune addGeuPSA(GeuPSA geuPSA) {
        this.geuPSAS.add(geuPSA);
        geuPSA.setGeocommune(this);
        return this;
    }

    public GeoCommune removeGeuPSA(GeuPSA geuPSA) {
        this.geuPSAS.remove(geuPSA);
        geuPSA.setGeocommune(null);
        return this;
    }

    public void setGeuPSAS(Set<GeuPSA> geuPSAS) {
        this.geuPSAS = geuPSAS;
    }

    public GeoProvince getGeoprovince() {
        return geoprovince;
    }

    public GeoCommune geoprovince(GeoProvince geoProvince) {
        this.geoprovince = geoProvince;
        return this;
    }

    public void setGeoprovince(GeoProvince geoProvince) {
        this.geoprovince = geoProvince;
    }

    public GeoTypeCommune getGeotypecommune() {
        return geotypecommune;
    }

    public GeoCommune geotypecommune(GeoTypeCommune geoTypeCommune) {
        this.geotypecommune = geoTypeCommune;
        return this;
    }

    public void setGeotypecommune(GeoTypeCommune geoTypeCommune) {
        this.geotypecommune = geoTypeCommune;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoCommune)) {
            return false;
        }
        return id != null && id.equals(((GeoCommune) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoCommune{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
