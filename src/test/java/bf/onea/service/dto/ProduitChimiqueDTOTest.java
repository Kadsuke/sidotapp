package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class ProduitChimiqueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitChimiqueDTO.class);
        ProduitChimiqueDTO produitChimiqueDTO1 = new ProduitChimiqueDTO();
        produitChimiqueDTO1.setId(1L);
        ProduitChimiqueDTO produitChimiqueDTO2 = new ProduitChimiqueDTO();
        assertThat(produitChimiqueDTO1).isNotEqualTo(produitChimiqueDTO2);
        produitChimiqueDTO2.setId(produitChimiqueDTO1.getId());
        assertThat(produitChimiqueDTO1).isEqualTo(produitChimiqueDTO2);
        produitChimiqueDTO2.setId(2L);
        assertThat(produitChimiqueDTO1).isNotEqualTo(produitChimiqueDTO2);
        produitChimiqueDTO1.setId(null);
        assertThat(produitChimiqueDTO1).isNotEqualTo(produitChimiqueDTO2);
    }
}
