package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefMoisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefMois.class);
        RefMois refMois1 = new RefMois();
        refMois1.setId(1L);
        RefMois refMois2 = new RefMois();
        refMois2.setId(refMois1.getId());
        assertThat(refMois1).isEqualTo(refMois2);
        refMois2.setId(2L);
        assertThat(refMois1).isNotEqualTo(refMois2);
        refMois1.setId(null);
        assertThat(refMois1).isNotEqualTo(refMois2);
    }
}
