package bf.onea.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link bf.onea.domain.TypeOuvrage} entity.
 */
public class TypeOuvrageDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;


    private Long categorieressourcesId;

    private Long caracteristiqueouvrageId;
    
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

    public Long getCategorieressourcesId() {
        return categorieressourcesId;
    }

    public void setCategorieressourcesId(Long categorieRessourcesId) {
        this.categorieressourcesId = categorieRessourcesId;
    }

    public Long getCaracteristiqueouvrageId() {
        return caracteristiqueouvrageId;
    }

    public void setCaracteristiqueouvrageId(Long caracteristiqueOuvrageId) {
        this.caracteristiqueouvrageId = caracteristiqueOuvrageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeOuvrageDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeOuvrageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeOuvrageDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", categorieressourcesId=" + getCategorieressourcesId() +
            ", caracteristiqueouvrageId=" + getCaracteristiqueouvrageId() +
            "}";
    }
}
