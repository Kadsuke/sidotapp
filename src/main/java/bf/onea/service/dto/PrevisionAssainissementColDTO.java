package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.PrevisionAssainissementCol} entity.
 */
public class PrevisionAssainissementColDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer nbStep;

    @NotNull
    private Integer nbStbv;

    @NotNull
    private Float reseaux;

    @NotNull
    private Integer nbRaccordement;


    private Long refanneeId;

    private Long centreId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbStep() {
        return nbStep;
    }

    public void setNbStep(Integer nbStep) {
        this.nbStep = nbStep;
    }

    public Integer getNbStbv() {
        return nbStbv;
    }

    public void setNbStbv(Integer nbStbv) {
        this.nbStbv = nbStbv;
    }

    public Float getReseaux() {
        return reseaux;
    }

    public void setReseaux(Float reseaux) {
        this.reseaux = reseaux;
    }

    public Integer getNbRaccordement() {
        return nbRaccordement;
    }

    public void setNbRaccordement(Integer nbRaccordement) {
        this.nbRaccordement = nbRaccordement;
    }

    public Long getRefanneeId() {
        return refanneeId;
    }

    public void setRefanneeId(Long refAnneeId) {
        this.refanneeId = refAnneeId;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisionAssainissementColDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisionAssainissementColDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionAssainissementColDTO{" +
            "id=" + getId() +
            ", nbStep=" + getNbStep() +
            ", nbStbv=" + getNbStbv() +
            ", reseaux=" + getReseaux() +
            ", nbRaccordement=" + getNbRaccordement() +
            ", refanneeId=" + getRefanneeId() +
            ", centreId=" + getCentreId() +
            "}";
    }
}
