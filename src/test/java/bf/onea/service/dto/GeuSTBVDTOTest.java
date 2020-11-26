package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuSTBVDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuSTBVDTO.class);
        GeuSTBVDTO geuSTBVDTO1 = new GeuSTBVDTO();
        geuSTBVDTO1.setId(1L);
        GeuSTBVDTO geuSTBVDTO2 = new GeuSTBVDTO();
        assertThat(geuSTBVDTO1).isNotEqualTo(geuSTBVDTO2);
        geuSTBVDTO2.setId(geuSTBVDTO1.getId());
        assertThat(geuSTBVDTO1).isEqualTo(geuSTBVDTO2);
        geuSTBVDTO2.setId(2L);
        assertThat(geuSTBVDTO1).isNotEqualTo(geuSTBVDTO2);
        geuSTBVDTO1.setId(null);
        assertThat(geuSTBVDTO1).isNotEqualTo(geuSTBVDTO2);
    }
}
