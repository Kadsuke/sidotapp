package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefPeriodiciteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefPeriodicite.class);
        RefPeriodicite refPeriodicite1 = new RefPeriodicite();
        refPeriodicite1.setId(1L);
        RefPeriodicite refPeriodicite2 = new RefPeriodicite();
        refPeriodicite2.setId(refPeriodicite1.getId());
        assertThat(refPeriodicite1).isEqualTo(refPeriodicite2);
        refPeriodicite2.setId(2L);
        assertThat(refPeriodicite1).isNotEqualTo(refPeriodicite2);
        refPeriodicite1.setId(null);
        assertThat(refPeriodicite1).isNotEqualTo(refPeriodicite2);
    }
}
