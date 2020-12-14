package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.CaracteristiqueOuvrage} entity.
 */
public class CaracteristiqueOuvrageDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private String unite;


    private Long typeouvrageId;
    
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

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Long getTypeouvrageId() {
        return typeouvrageId;
    }

    public void setTypeouvrageId(Long typeOuvrageId) {
        this.typeouvrageId = typeOuvrageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaracteristiqueOuvrageDTO)) {
            return false;
        }

        return id != null && id.equals(((CaracteristiqueOuvrageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaracteristiqueOuvrageDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", unite='" + getUnite() + "'" +
            ", typeouvrageId=" + getTypeouvrageId() +
            "}";
    }
}
