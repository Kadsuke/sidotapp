package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class BailleurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bailleur.class);
        Bailleur bailleur1 = new Bailleur();
        bailleur1.setId(1L);
        Bailleur bailleur2 = new Bailleur();
        bailleur2.setId(bailleur1.getId());
        assertThat(bailleur1).isEqualTo(bailleur2);
        bailleur2.setId(2L);
        assertThat(bailleur1).isNotEqualTo(bailleur2);
        bailleur1.setId(null);
        assertThat(bailleur1).isNotEqualTo(bailleur2);
    }
}
