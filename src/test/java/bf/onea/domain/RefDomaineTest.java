package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefDomaineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefDomaine.class);
        RefDomaine refDomaine1 = new RefDomaine();
        refDomaine1.setId(1L);
        RefDomaine refDomaine2 = new RefDomaine();
        refDomaine2.setId(refDomaine1.getId());
        assertThat(refDomaine1).isEqualTo(refDomaine2);
        refDomaine2.setId(2L);
        assertThat(refDomaine1).isNotEqualTo(refDomaine2);
        refDomaine1.setId(null);
        assertThat(refDomaine1).isNotEqualTo(refDomaine2);
    }
}
