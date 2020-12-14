package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeuSTEP.
 */
@Entity
@Table(name = "geu_step")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuSTEP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libel_step", nullable = false)
    private String libelSTEP;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @NotNull
    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "geustep")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuPrevisionSTEP> geuPrevisionSTEPS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelSTEP() {
        return libelSTEP;
    }

    public GeuSTEP libelSTEP(String libelSTEP) {
        this.libelSTEP = libelSTEP;
        return this;
    }

    public void setLibelSTEP(String libelSTEP) {
        this.libelSTEP = libelSTEP;
    }

    public String getResponsable() {
        return responsable;
    }

    public GeuSTEP responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public GeuSTEP contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<GeuPrevisionSTEP> getGeuPrevisionSTEPS() {
        return geuPrevisionSTEPS;
    }

    public GeuSTEP geuPrevisionSTEPS(Set<GeuPrevisionSTEP> geuPrevisionSTEPS) {
        this.geuPrevisionSTEPS = geuPrevisionSTEPS;
        return this;
    }

    public GeuSTEP addGeuPrevisionSTEP(GeuPrevisionSTEP geuPrevisionSTEP) {
        this.geuPrevisionSTEPS.add(geuPrevisionSTEP);
        geuPrevisionSTEP.setGeustep(this);
        return this;
    }

    public GeuSTEP removeGeuPrevisionSTEP(GeuPrevisionSTEP geuPrevisionSTEP) {
        this.geuPrevisionSTEPS.remove(geuPrevisionSTEP);
        geuPrevisionSTEP.setGeustep(null);
        return this;
    }

    public void setGeuPrevisionSTEPS(Set<GeuPrevisionSTEP> geuPrevisionSTEPS) {
        this.geuPrevisionSTEPS = geuPrevisionSTEPS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuSTEP)) {
            return false;
        }
        return id != null && id.equals(((GeuSTEP) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuSTEP{" +
            "id=" + getId() +
            ", libelSTEP='" + getLibelSTEP() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
