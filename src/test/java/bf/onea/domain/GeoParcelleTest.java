package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoParcelleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoParcelle.class);
        GeoParcelle geoParcelle1 = new GeoParcelle();
        geoParcelle1.setId(1L);
        GeoParcelle geoParcelle2 = new GeoParcelle();
        geoParcelle2.setId(geoParcelle1.getId());
        assertThat(geoParcelle1).isEqualTo(geoParcelle2);
        geoParcelle2.setId(2L);
        assertThat(geoParcelle1).isNotEqualTo(geoParcelle2);
        geoParcelle1.setId(null);
        assertThat(geoParcelle1).isNotEqualTo(geoParcelle2);
    }
}
