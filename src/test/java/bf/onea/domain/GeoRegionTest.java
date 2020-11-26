package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoRegionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoRegion.class);
        GeoRegion geoRegion1 = new GeoRegion();
        geoRegion1.setId(1L);
        GeoRegion geoRegion2 = new GeoRegion();
        geoRegion2.setId(geoRegion1.getId());
        assertThat(geoRegion1).isEqualTo(geoRegion2);
        geoRegion2.setId(2L);
        assertThat(geoRegion1).isNotEqualTo(geoRegion2);
        geoRegion1.setId(null);
        assertThat(geoRegion1).isNotEqualTo(geoRegion2);
    }
}
