package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefSousDomaineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSousDomaine.class);
        RefSousDomaine refSousDomaine1 = new RefSousDomaine();
        refSousDomaine1.setId(1L);
        RefSousDomaine refSousDomaine2 = new RefSousDomaine();
        refSousDomaine2.setId(refSousDomaine1.getId());
        assertThat(refSousDomaine1).isEqualTo(refSousDomaine2);
        refSousDomaine2.setId(2L);
        assertThat(refSousDomaine1).isNotEqualTo(refSousDomaine2);
        refSousDomaine1.setId(null);
        assertThat(refSousDomaine1).isNotEqualTo(refSousDomaine2);
    }
}
