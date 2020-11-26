package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TacheronsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tacherons.class);
        Tacherons tacherons1 = new Tacherons();
        tacherons1.setId(1L);
        Tacherons tacherons2 = new Tacherons();
        tacherons2.setId(tacherons1.getId());
        assertThat(tacherons1).isEqualTo(tacherons2);
        tacherons2.setId(2L);
        assertThat(tacherons1).isNotEqualTo(tacherons2);
        tacherons1.setId(null);
        assertThat(tacherons1).isNotEqualTo(tacherons2);
    }
}
