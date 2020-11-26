package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeAnalyseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAnalyse.class);
        TypeAnalyse typeAnalyse1 = new TypeAnalyse();
        typeAnalyse1.setId(1L);
        TypeAnalyse typeAnalyse2 = new TypeAnalyse();
        typeAnalyse2.setId(typeAnalyse1.getId());
        assertThat(typeAnalyse1).isEqualTo(typeAnalyse2);
        typeAnalyse2.setId(2L);
        assertThat(typeAnalyse1).isNotEqualTo(typeAnalyse2);
        typeAnalyse1.setId(null);
        assertThat(typeAnalyse1).isNotEqualTo(typeAnalyse2);
    }
}
