package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuPrevisionSTBV} entity.
 */
public class GeuPrevisionSTBVDTO implements Serializable {
    
    private Long id;

    private String nbCamions;

    private String volume;

    private String energie;


    private Long geustbvId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNbCamions() {
        return nbCamions;
    }

    public void setNbCamions(String nbCamions) {
        this.nbCamions = nbCamions;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public Long getGeustbvId() {
        return geustbvId;
    }

    public void setGeustbvId(Long geuSTBVId) {
        this.geustbvId = geuSTBVId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuPrevisionSTBVDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuPrevisionSTBVDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPrevisionSTBVDTO{" +
            "id=" + getId() +
            ", nbCamions='" + getNbCamions() + "'" +
            ", volume='" + getVolume() + "'" +
            ", energie='" + getEnergie() + "'" +
            ", geustbvId=" + getGeustbvId() +
            "}";
    }
}
