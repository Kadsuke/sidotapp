package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NatureOuvrage.
 */
@Entity
@Table(name = "nature_ouvrage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NatureOuvrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "natureouvrage")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuAaOuvrage> geuAaOuvrages = new HashSet<>();

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

    public NatureOuvrage libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeuAaOuvrage> getGeuAaOuvrages() {
        return geuAaOuvrages;
    }

    public NatureOuvrage geuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
        return this;
    }

    public NatureOuvrage addGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.add(geuAaOuvrage);
        geuAaOuvrage.setNatureouvrage(this);
        return this;
    }

    public NatureOuvrage removeGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.remove(geuAaOuvrage);
        geuAaOuvrage.setNatureouvrage(null);
        return this;
    }

    public void setGeuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NatureOuvrage)) {
            return false;
        }
        return id != null && id.equals(((NatureOuvrage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NatureOuvrage{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
