package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoLocaliteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoLocalite.class);
        GeoLocalite geoLocalite1 = new GeoLocalite();
        geoLocalite1.setId(1L);
        GeoLocalite geoLocalite2 = new GeoLocalite();
        geoLocalite2.setId(geoLocalite1.getId());
        assertThat(geoLocalite1).isEqualTo(geoLocalite2);
        geoLocalite2.setId(2L);
        assertThat(geoLocalite1).isNotEqualTo(geoLocalite2);
        geoLocalite1.setId(null);
        assertThat(geoLocalite1).isNotEqualTo(geoLocalite2);
    }
}
