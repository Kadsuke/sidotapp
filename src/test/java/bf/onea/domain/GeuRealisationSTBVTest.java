package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRealisationSTBVTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRealisationSTBV.class);
        GeuRealisationSTBV geuRealisationSTBV1 = new GeuRealisationSTBV();
        geuRealisationSTBV1.setId(1L);
        GeuRealisationSTBV geuRealisationSTBV2 = new GeuRealisationSTBV();
        geuRealisationSTBV2.setId(geuRealisationSTBV1.getId());
        assertThat(geuRealisationSTBV1).isEqualTo(geuRealisationSTBV2);
        geuRealisationSTBV2.setId(2L);
        assertThat(geuRealisationSTBV1).isNotEqualTo(geuRealisationSTBV2);
        geuRealisationSTBV1.setId(null);
        assertThat(geuRealisationSTBV1).isNotEqualTo(geuRealisationSTBV2);
    }
}
