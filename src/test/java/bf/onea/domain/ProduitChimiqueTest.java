package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class ProduitChimiqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitChimique.class);
        ProduitChimique produitChimique1 = new ProduitChimique();
        produitChimique1.setId(1L);
        ProduitChimique produitChimique2 = new ProduitChimique();
        produitChimique2.setId(produitChimique1.getId());
        assertThat(produitChimique1).isEqualTo(produitChimique2);
        produitChimique2.setId(2L);
        assertThat(produitChimique1).isNotEqualTo(produitChimique2);
        produitChimique1.setId(null);
        assertThat(produitChimique1).isNotEqualTo(produitChimique2);
    }
}
