package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Les entites
 */
@Entity
@Table(name = "ref_annee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RefAnnee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToOne(mappedBy = "refannee")
    @JsonIgnore
    private PrevisionAssainissementAu previsionAssainissementAu;

    @OneToOne(mappedBy = "refannee")
    @JsonIgnore
    private PrevisionAssainissementCol previsionAssainissementCol;

    @OneToOne(mappedBy = "refAnnee")
    @JsonIgnore
    private PrevisionPsa previsionPsa;

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

    public RefAnnee libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public PrevisionAssainissementAu getPrevisionAssainissementAu() {
        return previsionAssainissementAu;
    }

    public RefAnnee previsionAssainissementAu(PrevisionAssainissementAu previsionAssainissementAu) {
        this.previsionAssainissementAu = previsionAssainissementAu;
        return this;
    }

    public void setPrevisionAssainissementAu(PrevisionAssainissementAu previsionAssainissementAu) {
        this.previsionAssainissementAu = previsionAssainissementAu;
    }

    public PrevisionAssainissementCol getPrevisionAssainissementCol() {
        return previsionAssainissementCol;
    }

    public RefAnnee previsionAssainissementCol(PrevisionAssainissementCol previsionAssainissementCol) {
        this.previsionAssainissementCol = previsionAssainissementCol;
        return this;
    }

    public void setPrevisionAssainissementCol(PrevisionAssainissementCol previsionAssainissementCol) {
        this.previsionAssainissementCol = previsionAssainissementCol;
    }

    public PrevisionPsa getPrevisionPsa() {
        return previsionPsa;
    }

    public RefAnnee previsionPsa(PrevisionPsa previsionPsa) {
        this.previsionPsa = previsionPsa;
        return this;
    }

    public void setPrevisionPsa(PrevisionPsa previsionPsa) {
        this.previsionPsa = previsionPsa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RefAnnee)) {
            return false;
        }
        return id != null && id.equals(((RefAnnee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RefAnnee{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
