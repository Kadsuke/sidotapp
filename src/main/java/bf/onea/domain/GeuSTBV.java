package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GeuSTBV.
 */
@Entity
@Table(name = "geu_stbv")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuSTBV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libel_stbv")
    private String libelStbv;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "geustbv")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRealisationSTBV> geuRealisationSTBVS = new HashSet<>();

    @OneToMany(mappedBy = "geustbv")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuPrevisionSTBV> geuPrevisionSTBVS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelStbv() {
        return libelStbv;
    }

    public GeuSTBV libelStbv(String libelStbv) {
        this.libelStbv = libelStbv;
        return this;
    }

    public void setLibelStbv(String libelStbv) {
        this.libelStbv = libelStbv;
    }

    public String getResponsable() {
        return responsable;
    }

    public GeuSTBV responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public GeuSTBV contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<GeuRealisationSTBV> getGeuRealisationSTBVS() {
        return geuRealisationSTBVS;
    }

    public GeuSTBV geuRealisationSTBVS(Set<GeuRealisationSTBV> geuRealisationSTBVS) {
        this.geuRealisationSTBVS = geuRealisationSTBVS;
        return this;
    }

    public GeuSTBV addGeuRealisationSTBV(GeuRealisationSTBV geuRealisationSTBV) {
        this.geuRealisationSTBVS.add(geuRealisationSTBV);
        geuRealisationSTBV.setGeustbv(this);
        return this;
    }

    public GeuSTBV removeGeuRealisationSTBV(GeuRealisationSTBV geuRealisationSTBV) {
        this.geuRealisationSTBVS.remove(geuRealisationSTBV);
        geuRealisationSTBV.setGeustbv(null);
        return this;
    }

    public void setGeuRealisationSTBVS(Set<GeuRealisationSTBV> geuRealisationSTBVS) {
        this.geuRealisationSTBVS = geuRealisationSTBVS;
    }

    public Set<GeuPrevisionSTBV> getGeuPrevisionSTBVS() {
        return geuPrevisionSTBVS;
    }

    public GeuSTBV geuPrevisionSTBVS(Set<GeuPrevisionSTBV> geuPrevisionSTBVS) {
        this.geuPrevisionSTBVS = geuPrevisionSTBVS;
        return this;
    }

    public GeuSTBV addGeuPrevisionSTBV(GeuPrevisionSTBV geuPrevisionSTBV) {
        this.geuPrevisionSTBVS.add(geuPrevisionSTBV);
        geuPrevisionSTBV.setGeustbv(this);
        return this;
    }

    public GeuSTBV removeGeuPrevisionSTBV(GeuPrevisionSTBV geuPrevisionSTBV) {
        this.geuPrevisionSTBVS.remove(geuPrevisionSTBV);
        geuPrevisionSTBV.setGeustbv(null);
        return this;
    }

    public void setGeuPrevisionSTBVS(Set<GeuPrevisionSTBV> geuPrevisionSTBVS) {
        this.geuPrevisionSTBVS = geuPrevisionSTBVS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuSTBV)) {
            return false;
        }
        return id != null && id.equals(((GeuSTBV) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuSTBV{" +
            "id=" + getId() +
            ", libelStbv='" + getLibelStbv() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
