package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPrevisionSTEPTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPrevisionSTEP.class);
        GeuPrevisionSTEP geuPrevisionSTEP1 = new GeuPrevisionSTEP();
        geuPrevisionSTEP1.setId(1L);
        GeuPrevisionSTEP geuPrevisionSTEP2 = new GeuPrevisionSTEP();
        geuPrevisionSTEP2.setId(geuPrevisionSTEP1.getId());
        assertThat(geuPrevisionSTEP1).isEqualTo(geuPrevisionSTEP2);
        geuPrevisionSTEP2.setId(2L);
        assertThat(geuPrevisionSTEP1).isNotEqualTo(geuPrevisionSTEP2);
        geuPrevisionSTEP1.setId(null);
        assertThat(geuPrevisionSTEP1).isNotEqualTo(geuPrevisionSTEP2);
    }
}
