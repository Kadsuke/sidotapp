package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class ModeEvacExcretaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModeEvacExcretaDTO.class);
        ModeEvacExcretaDTO modeEvacExcretaDTO1 = new ModeEvacExcretaDTO();
        modeEvacExcretaDTO1.setId(1L);
        ModeEvacExcretaDTO modeEvacExcretaDTO2 = new ModeEvacExcretaDTO();
        assertThat(modeEvacExcretaDTO1).isNotEqualTo(modeEvacExcretaDTO2);
        modeEvacExcretaDTO2.setId(modeEvacExcretaDTO1.getId());
        assertThat(modeEvacExcretaDTO1).isEqualTo(modeEvacExcretaDTO2);
        modeEvacExcretaDTO2.setId(2L);
        assertThat(modeEvacExcretaDTO1).isNotEqualTo(modeEvacExcretaDTO2);
        modeEvacExcretaDTO1.setId(null);
        assertThat(modeEvacExcretaDTO1).isNotEqualTo(modeEvacExcretaDTO2);
    }
}
