package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuSTBV} entity.
 */
public class GeuSTBVDTO implements Serializable {
    
    private Long id;

    private String libelStbv;

    private String responsable;

    private String contact;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelStbv() {
        return libelStbv;
    }

    public void setLibelStbv(String libelStbv) {
        this.libelStbv = libelStbv;
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
        if (!(o instanceof GeuSTBVDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuSTBVDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuSTBVDTO{" +
            "id=" + getId() +
            ", libelStbv='" + getLibelStbv() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
