package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuSTEPDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuSTEPDTO.class);
        GeuSTEPDTO geuSTEPDTO1 = new GeuSTEPDTO();
        geuSTEPDTO1.setId(1L);
        GeuSTEPDTO geuSTEPDTO2 = new GeuSTEPDTO();
        assertThat(geuSTEPDTO1).isNotEqualTo(geuSTEPDTO2);
        geuSTEPDTO2.setId(geuSTEPDTO1.getId());
        assertThat(geuSTEPDTO1).isEqualTo(geuSTEPDTO2);
        geuSTEPDTO2.setId(2L);
        assertThat(geuSTEPDTO1).isNotEqualTo(geuSTEPDTO2);
        geuSTEPDTO1.setId(null);
        assertThat(geuSTEPDTO1).isNotEqualTo(geuSTEPDTO2);
    }
}
