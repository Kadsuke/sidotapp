package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatProjetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatProjet.class);
        EtatProjet etatProjet1 = new EtatProjet();
        etatProjet1.setId(1L);
        EtatProjet etatProjet2 = new EtatProjet();
        etatProjet2.setId(etatProjet1.getId());
        assertThat(etatProjet1).isEqualTo(etatProjet2);
        etatProjet2.setId(2L);
        assertThat(etatProjet1).isNotEqualTo(etatProjet2);
        etatProjet1.setId(null);
        assertThat(etatProjet1).isNotEqualTo(etatProjet2);
    }
}
