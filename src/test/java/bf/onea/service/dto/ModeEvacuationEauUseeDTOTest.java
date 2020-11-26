package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class ModeEvacuationEauUseeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModeEvacuationEauUseeDTO.class);
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO1 = new ModeEvacuationEauUseeDTO();
        modeEvacuationEauUseeDTO1.setId(1L);
        ModeEvacuationEauUseeDTO modeEvacuationEauUseeDTO2 = new ModeEvacuationEauUseeDTO();
        assertThat(modeEvacuationEauUseeDTO1).isNotEqualTo(modeEvacuationEauUseeDTO2);
        modeEvacuationEauUseeDTO2.setId(modeEvacuationEauUseeDTO1.getId());
        assertThat(modeEvacuationEauUseeDTO1).isEqualTo(modeEvacuationEauUseeDTO2);
        modeEvacuationEauUseeDTO2.setId(2L);
        assertThat(modeEvacuationEauUseeDTO1).isNotEqualTo(modeEvacuationEauUseeDTO2);
        modeEvacuationEauUseeDTO1.setId(null);
        assertThat(modeEvacuationEauUseeDTO1).isNotEqualTo(modeEvacuationEauUseeDTO2);
    }
}
