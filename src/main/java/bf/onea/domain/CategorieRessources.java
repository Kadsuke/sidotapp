package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CategorieRessources.
 */
@Entity
@Table(name = "categorie_ressources")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategorieRessources implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "categorieressources")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TypeOuvrage> typeOuvrages = new HashSet<>();

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

    public CategorieRessources libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<TypeOuvrage> getTypeOuvrages() {
        return typeOuvrages;
    }

    public CategorieRessources typeOuvrages(Set<TypeOuvrage> typeOuvrages) {
        this.typeOuvrages = typeOuvrages;
        return this;
    }

    public CategorieRessources addTypeOuvrage(TypeOuvrage typeOuvrage) {
        this.typeOuvrages.add(typeOuvrage);
        typeOuvrage.setCategorieressources(this);
        return this;
    }

    public CategorieRessources removeTypeOuvrage(TypeOuvrage typeOuvrage) {
        this.typeOuvrages.remove(typeOuvrage);
        typeOuvrage.setCategorieressources(null);
        return this;
    }

    public void setTypeOuvrages(Set<TypeOuvrage> typeOuvrages) {
        this.typeOuvrages = typeOuvrages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieRessources)) {
            return false;
        }
        return id != null && id.equals(((CategorieRessources) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieRessources{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
