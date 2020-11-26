package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuSTBVTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuSTBV.class);
        GeuSTBV geuSTBV1 = new GeuSTBV();
        geuSTBV1.setId(1L);
        GeuSTBV geuSTBV2 = new GeuSTBV();
        geuSTBV2.setId(geuSTBV1.getId());
        assertThat(geuSTBV1).isEqualTo(geuSTBV2);
        geuSTBV2.setId(2L);
        assertThat(geuSTBV1).isNotEqualTo(geuSTBV2);
        geuSTBV1.setId(null);
        assertThat(geuSTBV1).isNotEqualTo(geuSTBV2);
    }
}
