package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoLot} entity.
 */
public class GeoLotDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long geosectionId;
    
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

    public Long getGeosectionId() {
        return geosectionId;
    }

    public void setGeosectionId(Long geoSectionId) {
        this.geosectionId = geoSectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoLotDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoLotDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoLotDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geosectionId=" + getGeosectionId() +
            "}";
    }
}
