package bf.onea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A GeuRaccordement.
 */
@Entity
@Table(name = "geu_raccordement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GeuRaccordement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_abonnement")
    private Long numAbonnement;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "proprietaire_paracelle")
    private String proprietaireParacelle;

    @Column(name = "entreprise")
    private String entreprise;

    @Column(name = "other_usage")
    private String otherUsage;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRaccordements", allowSetters = true)
    private GeoParcelle geoparcelle;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRaccordements", allowSetters = true)
    private Agent agent;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRaccordements", allowSetters = true)
    private Tacherons tacherons;

    @ManyToOne
    @JsonIgnoreProperties(value = "geuRaccordements", allowSetters = true)
    private GeuUsage geuusage;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumAbonnement() {
        return numAbonnement;
    }

    public GeuRaccordement numAbonnement(Long numAbonnement) {
        this.numAbonnement = numAbonnement;
        return this;
    }

    public void setNumAbonnement(Long numAbonnement) {
        this.numAbonnement = numAbonnement;
    }

    public String getNom() {
        return nom;
    }

    public GeuRaccordement nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public GeuRaccordement prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public GeuRaccordement adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProprietaireParacelle() {
        return proprietaireParacelle;
    }

    public GeuRaccordement proprietaireParacelle(String proprietaireParacelle) {
        this.proprietaireParacelle = proprietaireParacelle;
        return this;
    }

    public void setProprietaireParacelle(String proprietaireParacelle) {
        this.proprietaireParacelle = proprietaireParacelle;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public GeuRaccordement entreprise(String entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getOtherUsage() {
        return otherUsage;
    }

    public GeuRaccordement otherUsage(String otherUsage) {
        this.otherUsage = otherUsage;
        return this;
    }

    public void setOtherUsage(String otherUsage) {
        this.otherUsage = otherUsage;
    }

    public GeoParcelle getGeoparcelle() {
        return geoparcelle;
    }

    public GeuRaccordement geoparcelle(GeoParcelle geoParcelle) {
        this.geoparcelle = geoParcelle;
        return this;
    }

    public void setGeoparcelle(GeoParcelle geoParcelle) {
        this.geoparcelle = geoParcelle;
    }

    public Agent getAgent() {
        return agent;
    }

    public GeuRaccordement agent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Tacherons getTacherons() {
        return tacherons;
    }

    public GeuRaccordement tacherons(Tacherons tacherons) {
        this.tacherons = tacherons;
        return this;
    }

    public void setTacherons(Tacherons tacherons) {
        this.tacherons = tacherons;
    }

    public GeuUsage getGeuusage() {
        return geuusage;
    }

    public GeuRaccordement geuusage(GeuUsage geuUsage) {
        this.geuusage = geuUsage;
        return this;
    }

    public void setGeuusage(GeuUsage geuUsage) {
        this.geuusage = geuUsage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuRaccordement)) {
            return false;
        }
        return id != null && id.equals(((GeuRaccordement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuRaccordement{" +
            "id=" + getId() +
            ", numAbonnement=" + getNumAbonnement() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", proprietaireParacelle='" + getProprietaireParacelle() + "'" +
            ", entreprise='" + getEntreprise() + "'" +
            ", otherUsage='" + getOtherUsage() + "'" +
            "}";
    }
}
