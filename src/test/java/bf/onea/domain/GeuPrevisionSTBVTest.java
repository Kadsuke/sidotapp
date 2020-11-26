package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPrevisionSTBVTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPrevisionSTBV.class);
        GeuPrevisionSTBV geuPrevisionSTBV1 = new GeuPrevisionSTBV();
        geuPrevisionSTBV1.setId(1L);
        GeuPrevisionSTBV geuPrevisionSTBV2 = new GeuPrevisionSTBV();
        geuPrevisionSTBV2.setId(geuPrevisionSTBV1.getId());
        assertThat(geuPrevisionSTBV1).isEqualTo(geuPrevisionSTBV2);
        geuPrevisionSTBV2.setId(2L);
        assertThat(geuPrevisionSTBV1).isNotEqualTo(geuPrevisionSTBV2);
        geuPrevisionSTBV1.setId(null);
        assertThat(geuPrevisionSTBV1).isNotEqualTo(geuPrevisionSTBV2);
    }
}
