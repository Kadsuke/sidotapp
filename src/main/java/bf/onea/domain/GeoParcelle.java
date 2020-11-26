package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeoParcelle.
 */
@Entity
@Table(name = "geo_parcelle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeoParcelle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "geoparcelle")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuAaOuvrage> geuAaOuvrages = new HashSet<>();

    @OneToMany(mappedBy = "geoparcelle")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRaccordement> geuRaccordements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "geoParcelles", allowSetters = true)
    private GeoLot geolot;

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

    public GeoParcelle libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeuAaOuvrage> getGeuAaOuvrages() {
        return geuAaOuvrages;
    }

    public GeoParcelle geuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
        return this;
    }

    public GeoParcelle addGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.add(geuAaOuvrage);
        geuAaOuvrage.setGeoparcelle(this);
        return this;
    }

    public GeoParcelle removeGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.remove(geuAaOuvrage);
        geuAaOuvrage.setGeoparcelle(null);
        return this;
    }

    public void setGeuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
    }

    public Set<GeuRaccordement> getGeuRaccordements() {
        return geuRaccordements;
    }

    public GeoParcelle geuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
        return this;
    }

    public GeoParcelle addGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.add(geuRaccordement);
        geuRaccordement.setGeoparcelle(this);
        return this;
    }

    public GeoParcelle removeGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.remove(geuRaccordement);
        geuRaccordement.setGeoparcelle(null);
        return this;
    }

    public void setGeuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
    }

    public GeoLot getGeolot() {
        return geolot;
    }

    public GeoParcelle geolot(GeoLot geoLot) {
        this.geolot = geoLot;
        return this;
    }

    public void setGeolot(GeoLot geoLot) {
        this.geolot = geoLot;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoParcelle)) {
            return false;
        }
        return id != null && id.equals(((GeoParcelle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoParcelle{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
