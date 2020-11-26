package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuUsageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuUsageDTO.class);
        GeuUsageDTO geuUsageDTO1 = new GeuUsageDTO();
        geuUsageDTO1.setId(1L);
        GeuUsageDTO geuUsageDTO2 = new GeuUsageDTO();
        assertThat(geuUsageDTO1).isNotEqualTo(geuUsageDTO2);
        geuUsageDTO2.setId(geuUsageDTO1.getId());
        assertThat(geuUsageDTO1).isEqualTo(geuUsageDTO2);
        geuUsageDTO2.setId(2L);
        assertThat(geuUsageDTO1).isNotEqualTo(geuUsageDTO2);
        geuUsageDTO1.setId(null);
        assertThat(geuUsageDTO1).isNotEqualTo(geuUsageDTO2);
    }
}
