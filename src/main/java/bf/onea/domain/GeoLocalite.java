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
 * A GeoLocalite.
 */
@Entity
@Table(name = "geo_localite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoLocalite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "geolocalite")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeoSecteur> geoSecteurs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoLocalites", allowSetters = true)
    private GeoCommune geocommune;

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

    public GeoLocalite libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeoSecteur> getGeoSecteurs() {
        return geoSecteurs;
    }

    public GeoLocalite geoSecteurs(Set<GeoSecteur> geoSecteurs) {
        this.geoSecteurs = geoSecteurs;
        return this;
    }

    public GeoLocalite addGeoSecteur(GeoSecteur geoSecteur) {
        this.geoSecteurs.add(geoSecteur);
        geoSecteur.setGeolocalite(this);
        return this;
    }

    public GeoLocalite removeGeoSecteur(GeoSecteur geoSecteur) {
        this.geoSecteurs.remove(geoSecteur);
        geoSecteur.setGeolocalite(null);
        return this;
    }

    public void setGeoSecteurs(Set<GeoSecteur> geoSecteurs) {
        this.geoSecteurs = geoSecteurs;
    }

    public GeoCommune getGeocommune() {
        return geocommune;
    }

    public GeoLocalite geocommune(GeoCommune geoCommune) {
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
        if (!(o instanceof GeoLocalite)) {
            return false;
        }
        return id != null && id.equals(((GeoLocalite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoLocalite{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
