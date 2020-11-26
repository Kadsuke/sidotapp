package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoSection} entity.
 */
public class GeoSectionDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long geosecteurId;
    
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

    public Long getGeosecteurId() {
        return geosecteurId;
    }

    public void setGeosecteurId(Long geoSecteurId) {
        this.geosecteurId = geoSecteurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoSectionDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoSectionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoSectionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geosecteurId=" + getGeosecteurId() +
            "}";
    }
}
