package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agent.
 */
@Entity
@Table(name = "agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "numero")
    private String numero;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "agent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeuRaccordement> geuRaccordements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "agents", allowSetters = true)
    private Site site;

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

    public Agent nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public Agent numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRole() {
        return role;
    }

    public Agent role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<GeuRaccordement> getGeuRaccordements() {
        return geuRaccordements;
    }

    public Agent geuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
        return this;
    }

    public Agent addGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.add(geuRaccordement);
        geuRaccordement.setAgent(this);
        return this;
    }

    public Agent removeGeuRaccordement(GeuRaccordement geuRaccordement) {
        this.geuRaccordements.remove(geuRaccordement);
        geuRaccordement.setAgent(null);
        return this;
    }

    public void setGeuRaccordements(Set<GeuRaccordement> geuRaccordements) {
        this.geuRaccordements = geuRaccordements;
    }

    public Site getSite() {
        return site;
    }

    public Agent site(Site site) {
        this.site = site;
        return this;
    }

    public void setSite(Site site) {
        this.site = site;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agent)) {
            return false;
        }
        return id != null && id.equals(((Agent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agent{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", numero='" + getNumero() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
