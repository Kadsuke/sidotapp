package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class PrevisionPsaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionPsa.class);
        PrevisionPsa previsionPsa1 = new PrevisionPsa();
        previsionPsa1.setId(1L);
        PrevisionPsa previsionPsa2 = new PrevisionPsa();
        previsionPsa2.setId(previsionPsa1.getId());
        assertThat(previsionPsa1).isEqualTo(previsionPsa2);
        previsionPsa2.setId(2L);
        assertThat(previsionPsa1).isNotEqualTo(previsionPsa2);
        previsionPsa1.setId(null);
        assertThat(previsionPsa1).isNotEqualTo(previsionPsa2);
    }
}
