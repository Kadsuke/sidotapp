package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class AnalyseParametreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnalyseParametre.class);
        AnalyseParametre analyseParametre1 = new AnalyseParametre();
        analyseParametre1.setId(1L);
        AnalyseParametre analyseParametre2 = new AnalyseParametre();
        analyseParametre2.setId(analyseParametre1.getId());
        assertThat(analyseParametre1).isEqualTo(analyseParametre2);
        analyseParametre2.setId(2L);
        assertThat(analyseParametre1).isNotEqualTo(analyseParametre2);
        analyseParametre1.setId(null);
        assertThat(analyseParametre1).isNotEqualTo(analyseParametre2);
    }
}
