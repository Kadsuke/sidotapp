package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CategorieRessourcesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieRessources.class);
        CategorieRessources categorieRessources1 = new CategorieRessources();
        categorieRessources1.setId(1L);
        CategorieRessources categorieRessources2 = new CategorieRessources();
        categorieRessources2.setId(categorieRessources1.getId());
        assertThat(categorieRessources1).isEqualTo(categorieRessources2);
        categorieRessources2.setId(2L);
        assertThat(categorieRessources1).isNotEqualTo(categorieRessources2);
        categorieRessources1.setId(null);
        assertThat(categorieRessources1).isNotEqualTo(categorieRessources2);
    }
}
