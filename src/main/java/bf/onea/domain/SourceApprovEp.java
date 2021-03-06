package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A SourceApprovEp.
 */
@Entity
@Table(name = "source_approv_ep")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceApprovEp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "sourceapprovep")
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

    public SourceApprovEp libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<GeuAaOuvrage> getGeuAaOuvrages() {
        return geuAaOuvrages;
    }

    public SourceApprovEp geuAaOuvrages(Set<GeuAaOuvrage> geuAaOuvrages) {
        this.geuAaOuvrages = geuAaOuvrages;
        return this;
    }

    public SourceApprovEp addGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.add(geuAaOuvrage);
        geuAaOuvrage.setSourceapprovep(this);
        return this;
    }

    public SourceApprovEp removeGeuAaOuvrage(GeuAaOuvrage geuAaOuvrage) {
        this.geuAaOuvrages.remove(geuAaOuvrage);
        geuAaOuvrage.setSourceapprovep(null);
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
        if (!(o instanceof SourceApprovEp)) {
            return false;
        }
        return id != null && id.equals(((SourceApprovEp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SourceApprovEp{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
