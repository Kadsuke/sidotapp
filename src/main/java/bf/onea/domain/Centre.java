package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Centre.
 */
@Entity
@Table(name = "centre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Centre implements Serializable {

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

    @OneToMany(mappedBy = "centre")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Site> sites = new HashSet<>();

    @OneToOne(mappedBy = "centre")
    @JsonIgnore
    private PrevisionAssainissementAu previsionAssainissementAu;

    @OneToOne(mappedBy = "centre")
    @JsonIgnore
    private PrevisionAssainissementCol previsionAssainissementCol;

    @OneToOne(mappedBy = "centre")
    @JsonIgnore
    private PrevisionPsa previsionPsa;

    @ManyToOne
    @JsonIgnoreProperties(value = "centres", allowSetters = true)
    private CentreRegroupement centreregroupement;

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

    public Centre libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsable() {
        return responsable;
    }

    public Centre responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public Centre contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public Centre sites(Set<Site> sites) {
        this.sites = sites;
        return this;
    }

    public Centre addSite(Site site) {
        this.sites.add(site);
        site.setCentre(this);
        return this;
    }

    public Centre removeSite(Site site) {
        this.sites.remove(site);
        site.setCentre(null);
        return this;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    public PrevisionAssainissementAu getPrevisionAssainissementAu() {
        return previsionAssainissementAu;
    }

    public Centre previsionAssainissementAu(PrevisionAssainissementAu previsionAssainissementAu) {
        this.previsionAssainissementAu = previsionAssainissementAu;
        return this;
    }

    public void setPrevisionAssainissementAu(PrevisionAssainissementAu previsionAssainissementAu) {
        this.previsionAssainissementAu = previsionAssainissementAu;
    }

    public PrevisionAssainissementCol getPrevisionAssainissementCol() {
        return previsionAssainissementCol;
    }

    public Centre previsionAssainissementCol(PrevisionAssainissementCol previsionAssainissementCol) {
        this.previsionAssainissementCol = previsionAssainissementCol;
        return this;
    }

    public void setPrevisionAssainissementCol(PrevisionAssainissementCol previsionAssainissementCol) {
        this.previsionAssainissementCol = previsionAssainissementCol;
    }

    public PrevisionPsa getPrevisionPsa() {
        return previsionPsa;
    }

    public Centre previsionPsa(PrevisionPsa previsionPsa) {
        this.previsionPsa = previsionPsa;
        return this;
    }

    public void setPrevisionPsa(PrevisionPsa previsionPsa) {
        this.previsionPsa = previsionPsa;
    }

    public CentreRegroupement getCentreregroupement() {
        return centreregroupement;
    }

    public Centre centreregroupement(CentreRegroupement centreRegroupement) {
        this.centreregroupement = centreRegroupement;
        return this;
    }

    public void setCentreregroupement(CentreRegroupement centreRegroupement) {
        this.centreregroupement = centreRegroupement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Centre)) {
            return false;
        }
        return id != null && id.equals(((Centre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Centre{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
