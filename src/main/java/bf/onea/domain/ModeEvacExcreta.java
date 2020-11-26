package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ModeEvacExcreta.
 */
@Entity
@Table(name = "mode_evac_excreta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModeEvacExcreta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "modeevacexcreta")
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

    public ModeEvacExcreta libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeuAaOuvrage> getGeuAaOuvrages() {
        return geuAaOuvrages;
    }

    public ModeEvacExcreta geuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
        return this;
    }

    public ModeEvacExcreta addGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.add(geuAaOuvrage);
        geuAaOuvrage.setModeevacexcreta(this);
        return this;
    }

    public ModeEvacExcreta removeGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.remove(geuAaOuvrage);
        geuAaOuvrage.setModeevacexcreta(null);
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
        if (!(o instanceof ModeEvacExcreta)) {
            return false;
        }
        return id != null && id.equals(((ModeEvacExcreta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModeEvacExcreta{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
