package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class AnalyseSpecialiteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnalyseSpecialite.class);
        AnalyseSpecialite analyseSpecialite1 = new AnalyseSpecialite();
        analyseSpecialite1.setId(1L);
        AnalyseSpecialite analyseSpecialite2 = new AnalyseSpecialite();
        analyseSpecialite2.setId(analyseSpecialite1.getId());
        assertThat(analyseSpecialite1).isEqualTo(analyseSpecialite2);
        analyseSpecialite2.setId(2L);
        assertThat(analyseSpecialite1).isNotEqualTo(analyseSpecialite2);
        analyseSpecialite1.setId(null);
        assertThat(analyseSpecialite1).isNotEqualTo(analyseSpecialite2);
    }
}
