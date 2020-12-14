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
 * A TypeOuvrage.
 */
@Entity
@Table(name = "type_ouvrage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeOuvrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "typeouvrage")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CaracteristiqueOuvrage> caracteristiqueOuvrages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "typeOuvrages", allowSetters = true)
    private CategorieRessources categorieressources;

    @ManyToOne
    @JsonIgnoreProperties(value = "typeOuvrages", allowSetters = true)
    private CaracteristiqueOuvrage caracteristiqueouvrage;

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

    public TypeOuvrage libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<CaracteristiqueOuvrage> getCaracteristiqueOuvrages() {
        return caracteristiqueOuvrages;
    }

    public TypeOuvrage caracteristiqueOuvrages(Set<CaracteristiqueOuvrage> caracteristiqueOuvrages) {
        this.caracteristiqueOuvrages = caracteristiqueOuvrages;
        return this;
    }

    public TypeOuvrage addCaracteristiqueOuvrage(CaracteristiqueOuvrage caracteristiqueOuvrage) {
        this.caracteristiqueOuvrages.add(caracteristiqueOuvrage);
        caracteristiqueOuvrage.setTypeouvrage(this);
        return this;
    }

    public TypeOuvrage removeCaracteristiqueOuvrage(CaracteristiqueOuvrage caracteristiqueOuvrage) {
        this.caracteristiqueOuvrages.remove(caracteristiqueOuvrage);
        caracteristiqueOuvrage.setTypeouvrage(null);
        return this;
    }

    public void setCaracteristiqueOuvrages(Set<CaracteristiqueOuvrage> caracteristiqueOuvrages) {
        this.caracteristiqueOuvrages = caracteristiqueOuvrages;
    }

    public CategorieRessources getCategorieressources() {
        return categorieressources;
    }

    public TypeOuvrage categorieressources(CategorieRessources categorieRessources) {
        this.categorieressources = categorieRessources;
        return this;
    }

    public void setCategorieressources(CategorieRessources categorieRessources) {
        this.categorieressources = categorieRessources;
    }

    public CaracteristiqueOuvrage getCaracteristiqueouvrage() {
        return caracteristiqueouvrage;
    }

    public TypeOuvrage caracteristiqueouvrage(CaracteristiqueOuvrage caracteristiqueOuvrage) {
        this.caracteristiqueouvrage = caracteristiqueOuvrage;
        return this;
    }

    public void setCaracteristiqueouvrage(CaracteristiqueOuvrage caracteristiqueOuvrage) {
        this.caracteristiqueouvrage = caracteristiqueOuvrage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeOuvrage)) {
            return false;
        }
        return id != null && id.equals(((TypeOuvrage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeOuvrage{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
