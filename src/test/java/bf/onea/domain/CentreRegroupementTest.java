package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CentreRegroupementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentreRegroupement.class);
        CentreRegroupement centreRegroupement1 = new CentreRegroupement();
        centreRegroupement1.setId(1L);
        CentreRegroupement centreRegroupement2 = new CentreRegroupement();
        centreRegroupement2.setId(centreRegroupement1.getId());
        assertThat(centreRegroupement1).isEqualTo(centreRegroupement2);
        centreRegroupement2.setId(2L);
        assertThat(centreRegroupement1).isNotEqualTo(centreRegroupement2);
        centreRegroupement1.setId(null);
        assertThat(centreRegroupement1).isNotEqualTo(centreRegroupement2);
    }
}
