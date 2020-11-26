package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoLocalite} entity.
 */
public class GeoLocaliteDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long geocommuneId;
    
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

    public Long getGeocommuneId() {
        return geocommuneId;
    }

    public void setGeocommuneId(Long geoCommuneId) {
        this.geocommuneId = geoCommuneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoLocaliteDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoLocaliteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoLocaliteDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geocommuneId=" + getGeocommuneId() +
            "}";
    }
}
