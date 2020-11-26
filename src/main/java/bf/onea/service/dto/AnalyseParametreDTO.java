package bf.onea.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.AnalyseParametre} entity.
 */
public class AnalyseParametreDTO implements Serializable {
    
    private Long id;

    private String libelle;


    private Long analysespecialiteId;
    
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

    public Long getAnalysespecialiteId() {
        return analysespecialiteId;
    }

    public void setAnalysespecialiteId(Long analyseSpecialiteId) {
        this.analysespecialiteId = analyseSpecialiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalyseParametreDTO)) {
            return false;
        }

        return id != null && id.equals(((AnalyseParametreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnalyseParametreDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", analysespecialiteId=" + getAnalysespecialiteId() +
            "}";
    }
}
