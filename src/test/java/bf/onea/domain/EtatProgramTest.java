package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatProgramTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatProgram.class);
        EtatProgram etatProgram1 = new EtatProgram();
        etatProgram1.setId(1L);
        EtatProgram etatProgram2 = new EtatProgram();
        etatProgram2.setId(etatProgram1.getId());
        assertThat(etatProgram1).isEqualTo(etatProgram2);
        etatProgram2.setId(2L);
        assertThat(etatProgram1).isNotEqualTo(etatProgram2);
        etatProgram1.setId(null);
        assertThat(etatProgram1).isNotEqualTo(etatProgram2);
    }
}
