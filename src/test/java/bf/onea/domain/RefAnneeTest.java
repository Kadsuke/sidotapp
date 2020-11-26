package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefAnneeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefAnnee.class);
        RefAnnee refAnnee1 = new RefAnnee();
        refAnnee1.setId(1L);
        RefAnnee refAnnee2 = new RefAnnee();
        refAnnee2.setId(refAnnee1.getId());
        assertThat(refAnnee1).isEqualTo(refAnnee2);
        refAnnee2.setId(2L);
        assertThat(refAnnee1).isNotEqualTo(refAnnee2);
        refAnnee1.setId(null);
        assertThat(refAnnee1).isNotEqualTo(refAnnee2);
    }
}
