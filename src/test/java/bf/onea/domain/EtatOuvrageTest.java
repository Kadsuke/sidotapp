package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatOuvrageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatOuvrage.class);
        EtatOuvrage etatOuvrage1 = new EtatOuvrage();
        etatOuvrage1.setId(1L);
        EtatOuvrage etatOuvrage2 = new EtatOuvrage();
        etatOuvrage2.setId(etatOuvrage1.getId());
        assertThat(etatOuvrage1).isEqualTo(etatOuvrage2);
        etatOuvrage2.setId(2L);
        assertThat(etatOuvrage1).isNotEqualTo(etatOuvrage2);
        etatOuvrage1.setId(null);
        assertThat(etatOuvrage1).isNotEqualTo(etatOuvrage2);
    }
}
