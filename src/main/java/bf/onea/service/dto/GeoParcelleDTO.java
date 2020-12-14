package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoParcelle} entity.
 */
public class GeoParcelleDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;


    private Long geolotId;
    
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

    public Long getGeolotId() {
        return geolotId;
    }

    public void setGeolotId(Long geoLotId) {
        this.geolotId = geoLotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoParcelleDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoParcelleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoParcelleDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geolotId=" + getGeolotId() +
            "}";
    }
}
