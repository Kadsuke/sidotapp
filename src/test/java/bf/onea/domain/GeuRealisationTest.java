package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRealisationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRealisation.class);
        GeuRealisation geuRealisation1 = new GeuRealisation();
        geuRealisation1.setId(1L);
        GeuRealisation geuRealisation2 = new GeuRealisation();
        geuRealisation2.setId(geuRealisation1.getId());
        assertThat(geuRealisation1).isEqualTo(geuRealisation2);
        geuRealisation2.setId(2L);
        assertThat(geuRealisation1).isNotEqualTo(geuRealisation2);
        geuRealisation1.setId(null);
        assertThat(geuRealisation1).isNotEqualTo(geuRealisation2);
    }
}
