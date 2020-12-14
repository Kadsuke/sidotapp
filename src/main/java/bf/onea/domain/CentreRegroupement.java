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
 * A CentreRegroupement.
 */
@Entity
@Table(name = "centre_regroupement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CentreRegroupement implements Serializable {

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

    @OneToMany(mappedBy = "centreregroupement")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Centre> centres = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "centreRegroupements", allowSetters = true)
    private DirectionRegionale directionregionale;

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

    public CentreRegroupement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsable() {
        return responsable;
    }

    public CentreRegroupement responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public CentreRegroupement contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Centre> getCentres() {
        return centres;
    }

    public CentreRegroupement centres(Set<Centre> centres) {
        this.centres = centres;
        return this;
    }

    public CentreRegroupement addCentre(Centre centre) {
        this.centres.add(centre);
        centre.setCentreregroupement(this);
        return this;
    }

    public CentreRegroupement removeCentre(Centre centre) {
        this.centres.remove(centre);
        centre.setCentreregroupement(null);
        return this;
    }

    public void setCentres(Set<Centre> centres) {
        this.centres = centres;
    }

    public DirectionRegionale getDirectionregionale() {
        return directionregionale;
    }

    public CentreRegroupement directionregionale(DirectionRegionale directionRegionale) {
        this.directionregionale = directionRegionale;
        return this;
    }

    public void setDirectionregionale(DirectionRegionale directionRegionale) {
        this.directionregionale = directionRegionale;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CentreRegroupement)) {
            return false;
        }
        return id != null && id.equals(((CentreRegroupement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentreRegroupement{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
