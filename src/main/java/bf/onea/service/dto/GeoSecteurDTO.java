package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeoSecteur} entity.
 */
public class GeoSecteurDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long geolocaliteId;
    
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

    public Long getGeolocaliteId() {
        return geolocaliteId;
    }

    public void setGeolocaliteId(Long geoLocaliteId) {
        this.geolocaliteId = geoLocaliteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoSecteurDTO)) {
            return false;
        }

        return id != null && id.equals(((GeoSecteurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoSecteurDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", geolocaliteId=" + getGeolocaliteId() +
            "}";
    }
}
