package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoProvince} entity.
 */
public class GeoProvinceDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long georegionId;
    
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

    public Long getGeoregionId() {
        return georegionId;
    }

    public void setGeoregionId(Long geoRegionId) {
        this.georegionId = geoRegionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoProvinceDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoProvinceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoProvinceDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", georegionId=" + getGeoregionId() +
            "}";
    }
}
