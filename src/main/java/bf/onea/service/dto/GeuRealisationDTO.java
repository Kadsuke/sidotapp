package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuRealisation} entity.
 */
public class GeuRealisationDTO implements Serializable {
    
    private Long id;

    private Float nbRealisation;


    private Long geuaaouvrageId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNbRealisation() {
        return nbRealisation;
    }

    public void setNbRealisation(Float nbRealisation) {
        this.nbRealisation = nbRealisation;
    }

    public Long getGeuaaouvrageId() {
        return geuaaouvrageId;
    }

    public void setGeuaaouvrageId(Long geuAaOuvrageId) {
        this.geuaaouvrageId = geuAaOuvrageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuRealisationDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuRealisationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuRealisationDTO{" +
            "id=" + getId() +
            ", nbRealisation=" + getNbRealisation() +
            ", geuaaouvrageId=" + getGeuaaouvrageId() +
            "}";
    }
}
