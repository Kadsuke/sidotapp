package bf.onea.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bf.onea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PrevisionAssainissementColTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionAssainissementCol.class);
        PrevisionAssainissementCol previsionAssainissementCol1 = new PrevisionAssainissementCol();
        previsionAssainissementCol1.setId(1L);
        PrevisionAssainissementCol previsionAssainissementCol2 = new PrevisionAssainissementCol();
        previsionAssainissementCol2.setId(previsionAssainissementCol1.getId());
        assertThat(previsionAssainissementCol1).isEqualTo(previsionAssainissementCol2);
        previsionAssainissementCol2.setId(2L);
        assertThat(previsionAssainissementCol1).isNotEqualTo(previsionAssainissementCol2);
        previsionAssainissementCol1.setId(null);
        assertThat(previsionAssainissementCol1).isNotEqualTo(previsionAssainissementCol2);
    }
}
