package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class DirectionRegionaleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectionRegionale.class);
        DirectionRegionale directionRegionale1 = new DirectionRegionale();
        directionRegionale1.setId(1L);
        DirectionRegionale directionRegionale2 = new DirectionRegionale();
        directionRegionale2.setId(directionRegionale1.getId());
        assertThat(directionRegionale1).isEqualTo(directionRegionale2);
        directionRegionale2.setId(2L);
        assertThat(directionRegionale1).isNotEqualTo(directionRegionale2);
        directionRegionale1.setId(null);
        assertThat(directionRegionale1).isNotEqualTo(directionRegionale2);
    }
}
