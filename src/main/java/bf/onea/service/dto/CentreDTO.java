package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.Centre} entity.
 */
public class CentreDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private String responsable;

    @NotNull
    private String contact;


    private Long centreregroupementId;
    
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getCentreregroupementId() {
        return centreregroupementId;
    }

    public void setCentreregroupementId(Long centreRegroupementId) {
        this.centreregroupementId = centreRegroupementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CentreDTO)) {
            return false;
        }

        return id != null && id.equals(((CentreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CentreDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            ", centreregroupementId=" + getCentreregroupementId() +
            "}";
    }
}
