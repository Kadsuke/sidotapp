package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Bailleur.
 */
@Entity
@Table(name = "bailleur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bailleur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "responsbale")
    private String responsbale;

    @Column(name = "contact")
    private String contact;

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

    public Bailleur libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsbale() {
        return responsbale;
    }

    public Bailleur responsbale(String responsbale) {
        this.responsbale = responsbale;
        return this;
    }

    public void setResponsbale(String responsbale) {
        this.responsbale = responsbale;
    }

    public String getContact() {
        return contact;
    }

    public Bailleur contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bailleur)) {
            return false;
        }
        return id != null && id.equals(((Bailleur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bailleur{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsbale='" + getResponsbale() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
