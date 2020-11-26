package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPSATest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPSA.class);
        GeuPSA geuPSA1 = new GeuPSA();
        geuPSA1.setId(1L);
        GeuPSA geuPSA2 = new GeuPSA();
        geuPSA2.setId(geuPSA1.getId());
        assertThat(geuPSA1).isEqualTo(geuPSA2);
        geuPSA2.setId(2L);
        assertThat(geuPSA1).isNotEqualTo(geuPSA2);
        geuPSA1.setId(null);
        assertThat(geuPSA1).isNotEqualTo(geuPSA2);
    }
}
