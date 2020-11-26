package bf.onea.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tacherons.
 */
@Entity
@Table(name = "tacherons")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tacherons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "tel")
    private String tel;

    @Column(name = "adresse")
    private String adresse;

    @OneToMany(mappedBy = "tacherons")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRaccordement> geuRaccordements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Tacherons nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public Tacherons tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public Tacherons adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<GeuRaccordement> getGeuRaccordements() {
        return geuRaccordements;
    }

    public Tacherons geuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
        return this;
    }

    public Tacherons addGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.add(geuRaccordement);
        geuRaccordement.setTacherons(this);
        return this;
    }

    public Tacherons removeGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.remove(geuRaccordement);
        geuRaccordement.setTacherons(null);
        return this;
    }

    public void setGeuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tacherons)) {
            return false;
        }
        return id != null && id.equals(((Tacherons) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tacherons{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", tel='" + getTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
