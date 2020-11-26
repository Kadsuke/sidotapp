package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuUsageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuUsage.class);
        GeuUsage geuUsage1 = new GeuUsage();
        geuUsage1.setId(1L);
        GeuUsage geuUsage2 = new GeuUsage();
        geuUsage2.setId(geuUsage1.getId());
        assertThat(geuUsage1).isEqualTo(geuUsage2);
        geuUsage2.setId(2L);
        assertThat(geuUsage1).isNotEqualTo(geuUsage2);
        geuUsage1.setId(null);
        assertThat(geuUsage1).isNotEqualTo(geuUsage2);
    }
}
