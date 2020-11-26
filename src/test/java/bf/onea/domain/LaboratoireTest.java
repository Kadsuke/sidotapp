package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class LaboratoireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Laboratoire.class);
        Laboratoire laboratoire1 = new Laboratoire();
        laboratoire1.setId(1L);
        Laboratoire laboratoire2 = new Laboratoire();
        laboratoire2.setId(laboratoire1.getId());
        assertThat(laboratoire1).isEqualTo(laboratoire2);
        laboratoire2.setId(2L);
        assertThat(laboratoire1).isNotEqualTo(laboratoire2);
        laboratoire1.setId(null);
        assertThat(laboratoire1).isNotEqualTo(laboratoire2);
    }
}
