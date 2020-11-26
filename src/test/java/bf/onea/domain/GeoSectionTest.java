package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoSectionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoSection.class);
        GeoSection geoSection1 = new GeoSection();
        geoSection1.setId(1L);
        GeoSection geoSection2 = new GeoSection();
        geoSection2.setId(geoSection1.getId());
        assertThat(geoSection1).isEqualTo(geoSection2);
        geoSection2.setId(2L);
        assertThat(geoSection1).isNotEqualTo(geoSection2);
        geoSection1.setId(null);
        assertThat(geoSection1).isNotEqualTo(geoSection2);
    }
}
