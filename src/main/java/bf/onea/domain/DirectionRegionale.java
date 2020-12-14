package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DirectionRegionale.
 */
@Entity
@Table(name = "direction_regionale")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DirectionRegionale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @NotNull
    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "directionregionale")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CentreRegroupement> centreRegroupements = new HashSet<>();

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

    public DirectionRegionale libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsable() {
        return responsable;
    }

    public DirectionRegionale responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public DirectionRegionale contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<CentreRegroupement> getCentreRegroupements() {
        return centreRegroupements;
    }

    public DirectionRegionale centreRegroupements(Set<CentreRegroupement> centreRegroupements) {
        this.centreRegroupements = centreRegroupements;
        return this;
    }

    public DirectionRegionale addCentreRegroupement(CentreRegroupement centreRegroupement) {
        this.centreRegroupements.add(centreRegroupement);
        centreRegroupement.setDirectionregionale(this);
        return this;
    }

    public DirectionRegionale removeCentreRegroupement(CentreRegroupement centreRegroupement) {
        this.centreRegroupements.remove(centreRegroupement);
        centreRegroupement.setDirectionregionale(null);
        return this;
    }

    public void setCentreRegroupements(Set<CentreRegroupement> centreRegroupements) {
        this.centreRegroupements = centreRegroupements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectionRegionale)) {
            return false;
        }
        return id != null && id.equals(((DirectionRegionale) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DirectionRegionale{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
