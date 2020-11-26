package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.Bailleur} entity.
 */
public class BailleurDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private String responsbale;

    private String contact;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getResponsbale() {
        return responsbale;
    }

    public void setResponsbale(String responsbale) {
        this.responsbale = responsbale;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BailleurDTO)) {
            return false;
        }

        return id != null && id.equals(((BailleurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BailleurDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsbale='" + getResponsbale() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
