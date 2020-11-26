package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CategorieRessourcesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieRessourcesDTO.class);
        CategorieRessourcesDTO categorieRessourcesDTO1 = new CategorieRessourcesDTO();
        categorieRessourcesDTO1.setId(1L);
        CategorieRessourcesDTO categorieRessourcesDTO2 = new CategorieRessourcesDTO();
        assertThat(categorieRessourcesDTO1).isNotEqualTo(categorieRessourcesDTO2);
        categorieRessourcesDTO2.setId(categorieRessourcesDTO1.getId());
        assertThat(categorieRessourcesDTO1).isEqualTo(categorieRessourcesDTO2);
        categorieRessourcesDTO2.setId(2L);
        assertThat(categorieRessourcesDTO1).isNotEqualTo(categorieRessourcesDTO2);
        categorieRessourcesDTO1.setId(null);
        assertThat(categorieRessourcesDTO1).isNotEqualTo(categorieRessourcesDTO2);
    }
}
