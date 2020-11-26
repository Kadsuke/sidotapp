package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CaracteristiqueOuvrage.
 */
@Entity
@Table(name = "caracteristique_ouvrage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaracteristiqueOuvrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "unite")
    private String unite;

    @OneToMany(mappedBy = "caracteristiqueouvrage")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TypeOuvrage> typeOuvrages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "caracteristiqueOuvrages", allowSetters = true)
    private TypeOuvrage typeouvrage;

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

    public CaracteristiqueOuvrage libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUnite() {
        return unite;
    }

    public CaracteristiqueOuvrage unite(String unite) {
        this.unite = unite;
        return this;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Set<TypeOuvrage> getTypeOuvrages() {
        return typeOuvrages;
    }

    public CaracteristiqueOuvrage typeOuvrages(Set<TypeOuvrage> typeOuvrages) {
        this.typeOuvrages = typeOuvrages;
        return this;
    }

    public CaracteristiqueOuvrage addTypeOuvrage(TypeOuvrage typeOuvrage) {
        this.typeOuvrages.add(typeOuvrage);
        typeOuvrage.setCaracteristiqueouvrage(this);
        return this;
    }

    public CaracteristiqueOuvrage removeTypeOuvrage(TypeOuvrage typeOuvrage) {
        this.typeOuvrages.remove(typeOuvrage);
        typeOuvrage.setCaracteristiqueouvrage(null);
        return this;
    }

    public void setTypeOuvrages(Set<TypeOuvrage> typeOuvrages) {
        this.typeOuvrages = typeOuvrages;
    }

    public TypeOuvrage getTypeouvrage() {
        return typeouvrage;
    }

    public CaracteristiqueOuvrage typeouvrage(TypeOuvrage typeOuvrage) {
        this.typeouvrage = typeOuvrage;
        return this;
    }

    public void setTypeouvrage(TypeOuvrage typeOuvrage) {
        this.typeouvrage = typeOuvrage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaracteristiqueOuvrage)) {
            return false;
        }
        return id != null && id.equals(((CaracteristiqueOuvrage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaracteristiqueOuvrage{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", unite='" + getUnite() + "'" +
            "}";
    }
}
