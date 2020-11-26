package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class MaconTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Macon.class);
        Macon macon1 = new Macon();
        macon1.setId(1L);
        Macon macon2 = new Macon();
        macon2.setId(macon1.getId());
        assertThat(macon1).isEqualTo(macon2);
        macon2.setId(2L);
        assertThat(macon1).isNotEqualTo(macon2);
        macon1.setId(null);
        assertThat(macon1).isNotEqualTo(macon2);
    }
}
