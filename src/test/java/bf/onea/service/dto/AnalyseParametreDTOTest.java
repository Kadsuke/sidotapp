package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class AnalyseParametreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnalyseParametreDTO.class);
        AnalyseParametreDTO analyseParametreDTO1 = new AnalyseParametreDTO();
        analyseParametreDTO1.setId(1L);
        AnalyseParametreDTO analyseParametreDTO2 = new AnalyseParametreDTO();
        assertThat(analyseParametreDTO1).isNotEqualTo(analyseParametreDTO2);
        analyseParametreDTO2.setId(analyseParametreDTO1.getId());
        assertThat(analyseParametreDTO1).isEqualTo(analyseParametreDTO2);
        analyseParametreDTO2.setId(2L);
        assertThat(analyseParametreDTO1).isNotEqualTo(analyseParametreDTO2);
        analyseParametreDTO1.setId(null);
        assertThat(analyseParametreDTO1).isNotEqualTo(analyseParametreDTO2);
    }
}
