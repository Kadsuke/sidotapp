package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeBeneficiaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeBeneficiaireDTO.class);
        TypeBeneficiaireDTO typeBeneficiaireDTO1 = new TypeBeneficiaireDTO();
        typeBeneficiaireDTO1.setId(1L);
        TypeBeneficiaireDTO typeBeneficiaireDTO2 = new TypeBeneficiaireDTO();
        assertThat(typeBeneficiaireDTO1).isNotEqualTo(typeBeneficiaireDTO2);
        typeBeneficiaireDTO2.setId(typeBeneficiaireDTO1.getId());
        assertThat(typeBeneficiaireDTO1).isEqualTo(typeBeneficiaireDTO2);
        typeBeneficiaireDTO2.setId(2L);
        assertThat(typeBeneficiaireDTO1).isNotEqualTo(typeBeneficiaireDTO2);
        typeBeneficiaireDTO1.setId(null);
        assertThat(typeBeneficiaireDTO1).isNotEqualTo(typeBeneficiaireDTO2);
    }
}
