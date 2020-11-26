package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuSTEPTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuSTEP.class);
        GeuSTEP geuSTEP1 = new GeuSTEP();
        geuSTEP1.setId(1L);
        GeuSTEP geuSTEP2 = new GeuSTEP();
        geuSTEP2.setId(geuSTEP1.getId());
        assertThat(geuSTEP1).isEqualTo(geuSTEP2);
        geuSTEP2.setId(2L);
        assertThat(geuSTEP1).isNotEqualTo(geuSTEP2);
        geuSTEP1.setId(null);
        assertThat(geuSTEP1).isNotEqualTo(geuSTEP2);
    }
}
