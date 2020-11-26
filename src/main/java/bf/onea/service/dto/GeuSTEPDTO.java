package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuSTEP} entity.
 */
public class GeuSTEPDTO implements Serializable {
    
    private Long id;

    private String libelSTEP;

    private String responsable;

    private String contact;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelSTEP() {
        return libelSTEP;
    }

    public void setLibelSTEP(String libelSTEP) {
        this.libelSTEP = libelSTEP;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuSTEPDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuSTEPDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuSTEPDTO{" +
            "id=" + getId() +
            ", libelSTEP='" + getLibelSTEP() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
