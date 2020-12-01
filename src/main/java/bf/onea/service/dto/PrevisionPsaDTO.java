package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.PrevisionPsa} entity.
 */
public class PrevisionPsaDTO implements Serializable {
    
    private Long id;

    private Integer elabore;

    private Integer miseEnOeuvre;


    private Long centreId;

    private Long refAnneeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getElabore() {
        return elabore;
    }

    public void setElabore(Integer elabore) {
        this.elabore = elabore;
    }

    public Integer getMiseEnOeuvre() {
        return miseEnOeuvre;
    }

    public void setMiseEnOeuvre(Integer miseEnOeuvre) {
        this.miseEnOeuvre = miseEnOeuvre;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public Long getRefAnneeId() {
        return refAnneeId;
    }

    public void setRefAnneeId(Long refAnneeId) {
        this.refAnneeId = refAnneeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrevisionPsaDTO)) {
            return false;
        }

        return id != null && id.equals(((PrevisionPsaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrevisionPsaDTO{" +
            "id=" + getId() +
            ", elabore=" + getElabore() +
            ", miseEnOeuvre=" + getMiseEnOeuvre() +
            ", centreId=" + getCentreId() +
            ", refAnneeId=" + getRefAnneeId() +
            "}";
    }
}
