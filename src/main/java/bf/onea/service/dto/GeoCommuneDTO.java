package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoCommune} entity.
 */
public class GeoCommuneDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;


    private Long geoprovinceId;

    private Long geotypecommuneId;
    
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

    public Long getGeoprovinceId() {
        return geoprovinceId;
    }

    public void setGeoprovinceId(Long geoProvinceId) {
        this.geoprovinceId = geoProvinceId;
    }

    public Long getGeotypecommuneId() {
        return geotypecommuneId;
    }

    public void setGeotypecommuneId(Long geoTypeCommuneId) {
        this.geotypecommuneId = geoTypeCommuneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoCommuneDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoCommuneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoCommuneDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geoprovinceId=" + getGeoprovinceId() +
            ", geotypecommuneId=" + getGeotypecommuneId() +
            "}";
    }
}
