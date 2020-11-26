package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class SourceApprovEpDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SourceApprovEpDTO.class);
        SourceApprovEpDTO sourceApprovEpDTO1 = new SourceApprovEpDTO();
        sourceApprovEpDTO1.setId(1L);
        SourceApprovEpDTO sourceApprovEpDTO2 = new SourceApprovEpDTO();
        assertThat(sourceApprovEpDTO1).isNotEqualTo(sourceApprovEpDTO2);
        sourceApprovEpDTO2.setId(sourceApprovEpDTO1.getId());
        assertThat(sourceApprovEpDTO1).isEqualTo(sourceApprovEpDTO2);
        sourceApprovEpDTO2.setId(2L);
        assertThat(sourceApprovEpDTO1).isNotEqualTo(sourceApprovEpDTO2);
        sourceApprovEpDTO1.setId(null);
        assertThat(sourceApprovEpDTO1).isNotEqualTo(sourceApprovEpDTO2);
    }
}
