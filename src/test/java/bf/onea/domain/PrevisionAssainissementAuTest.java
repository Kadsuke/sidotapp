package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class PrevisionAssainissementAuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionAssainissementAu.class);
        PrevisionAssainissementAu previsionAssainissementAu1 = new PrevisionAssainissementAu();
        previsionAssainissementAu1.setId(1L);
        PrevisionAssainissementAu previsionAssainissementAu2 = new PrevisionAssainissementAu();
        previsionAssainissementAu2.setId(previsionAssainissementAu1.getId());
        assertThat(previsionAssainissementAu1).isEqualTo(previsionAssainissementAu2);
        previsionAssainissementAu2.setId(2L);
        assertThat(previsionAssainissementAu1).isNotEqualTo(previsionAssainissementAu2);
        previsionAssainissementAu1.setId(null);
        assertThat(previsionAssainissementAu1).isNotEqualTo(previsionAssainissementAu2);
    }
}
