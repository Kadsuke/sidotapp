package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeBeneficiaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeBeneficiaire.class);
        TypeBeneficiaire typeBeneficiaire1 = new TypeBeneficiaire();
        typeBeneficiaire1.setId(1L);
        TypeBeneficiaire typeBeneficiaire2 = new TypeBeneficiaire();
        typeBeneficiaire2.setId(typeBeneficiaire1.getId());
        assertThat(typeBeneficiaire1).isEqualTo(typeBeneficiaire2);
        typeBeneficiaire2.setId(2L);
        assertThat(typeBeneficiaire1).isNotEqualTo(typeBeneficiaire2);
        typeBeneficiaire1.setId(null);
        assertThat(typeBeneficiaire1).isNotEqualTo(typeBeneficiaire2);
    }
}
