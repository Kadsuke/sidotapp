package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class AnalyseSpecialiteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnalyseSpecialiteDTO.class);
        AnalyseSpecialiteDTO analyseSpecialiteDTO1 = new AnalyseSpecialiteDTO();
        analyseSpecialiteDTO1.setId(1L);
        AnalyseSpecialiteDTO analyseSpecialiteDTO2 = new AnalyseSpecialiteDTO();
        assertThat(analyseSpecialiteDTO1).isNotEqualTo(analyseSpecialiteDTO2);
        analyseSpecialiteDTO2.setId(analyseSpecialiteDTO1.getId());
        assertThat(analyseSpecialiteDTO1).isEqualTo(analyseSpecialiteDTO2);
        analyseSpecialiteDTO2.setId(2L);
        assertThat(analyseSpecialiteDTO1).isNotEqualTo(analyseSpecialiteDTO2);
        analyseSpecialiteDTO1.setId(null);
        assertThat(analyseSpecialiteDTO1).isNotEqualTo(analyseSpecialiteDTO2);
    }
}
