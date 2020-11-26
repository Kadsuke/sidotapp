package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuAaOuvrageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuAaOuvrage.class);
        GeuAaOuvrage geuAaOuvrage1 = new GeuAaOuvrage();
        geuAaOuvrage1.setId(1L);
        GeuAaOuvrage geuAaOuvrage2 = new GeuAaOuvrage();
        geuAaOuvrage2.setId(geuAaOuvrage1.getId());
        assertThat(geuAaOuvrage1).isEqualTo(geuAaOuvrage2);
        geuAaOuvrage2.setId(2L);
        assertThat(geuAaOuvrage1).isNotEqualTo(geuAaOuvrage2);
        geuAaOuvrage1.setId(null);
        assertThat(geuAaOuvrage1).isNotEqualTo(geuAaOuvrage2);
    }
}
