package bf.onea.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.GeuPSA} entity.
 */
public class GeuPSADTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant dateElaboration;

    @NotNull
    private Instant dateMiseEnOeuvre;


    private Long geocommuneId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateElaboration() {
        return dateElaboration;
    }

    public void setDateElaboration(Instant dateElaboration) {
        this.dateElaboration = dateElaboration;
    }

    public Instant getDateMiseEnOeuvre() {
        return dateMiseEnOeuvre;
    }

    public void setDateMiseEnOeuvre(Instant dateMiseEnOeuvre) {
        this.dateMiseEnOeuvre = dateMiseEnOeuvre;
    }

    public Long getGeocommuneId() {
        return geocommuneId;
    }

    public void setGeocommuneId(Long geoCommuneId) {
        this.geocommuneId = geoCommuneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeuPSADTO)) {
            return false;
        }

        return id != null && id.equals(((GeuPSADTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeuPSADTO{" +
            "id=" + getId() +
            ", dateElaboration='" + getDateElaboration() + "'" +
            ", dateMiseEnOeuvre='" + getDateMiseEnOeuvre() + "'" +
            ", geocommuneId=" + getGeocommuneId() +
            "}";
    }
}
