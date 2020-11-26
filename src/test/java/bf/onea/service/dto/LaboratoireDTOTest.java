package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class LaboratoireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaboratoireDTO.class);
        LaboratoireDTO laboratoireDTO1 = new LaboratoireDTO();
        laboratoireDTO1.setId(1L);
        LaboratoireDTO laboratoireDTO2 = new LaboratoireDTO();
        assertThat(laboratoireDTO1).isNotEqualTo(laboratoireDTO2);
        laboratoireDTO2.setId(laboratoireDTO1.getId());
        assertThat(laboratoireDTO1).isEqualTo(laboratoireDTO2);
        laboratoireDTO2.setId(2L);
        assertThat(laboratoireDTO1).isNotEqualTo(laboratoireDTO2);
        laboratoireDTO1.setId(null);
        assertThat(laboratoireDTO1).isNotEqualTo(laboratoireDTO2);
    }
}
