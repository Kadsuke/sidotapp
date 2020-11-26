package bf.onea.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuPrevisionSTEP} entity.
 */
public class GeuPrevisionSTEPDTO implements Serializable {
    
    private Long id;

    private Instant datePrevStep;

    private String volumePrevStep;


    private Long geustepId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDatePrevStep() {
        return datePrevStep;
    }

    public void setDatePrevStep(Instant datePrevStep) {
        this.datePrevStep = datePrevStep;
    }

    public String getVolumePrevStep() {
        return volumePrevStep;
    }

    public void setVolumePrevStep(String volumePrevStep) {
        this.volumePrevStep = volumePrevStep;
    }

    public Long getGeustepId() {
        return geustepId;
    }

    public void setGeustepId(Long geuSTEPId) {
        this.geustepId = geuSTEPId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuPrevisionSTEPDTO)) {
            return false;
        }

        return id != null && id.equals(((GeuPrevisionSTEPDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPrevisionSTEPDTO{" +
            "id=" + getId() +
            ", datePrevStep='" + getDatePrevStep() + "'" +
            ", volumePrevStep='" + getVolumePrevStep() + "'" +
            ", geustepId=" + getGeustepId() +
            "}";
    }
}
