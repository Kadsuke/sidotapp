package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRaccordementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRaccordement.class);
        GeuRaccordement geuRaccordement1 = new GeuRaccordement();
        geuRaccordement1.setId(1L);
        GeuRaccordement geuRaccordement2 = new GeuRaccordement();
        geuRaccordement2.setId(geuRaccordement1.getId());
        assertThat(geuRaccordement1).isEqualTo(geuRaccordement2);
        geuRaccordement2.setId(2L);
        assertThat(geuRaccordement1).isNotEqualTo(geuRaccordement2);
        geuRaccordement1.setId(null);
        assertThat(geuRaccordement1).isNotEqualTo(geuRaccordement2);
    }
}
