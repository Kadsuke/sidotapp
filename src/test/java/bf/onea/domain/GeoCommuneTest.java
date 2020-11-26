package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoCommuneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoCommune.class);
        GeoCommune geoCommune1 = new GeoCommune();
        geoCommune1.setId(1L);
        GeoCommune geoCommune2 = new GeoCommune();
        geoCommune2.setId(geoCommune1.getId());
        assertThat(geoCommune1).isEqualTo(geoCommune2);
        geoCommune2.setId(2L);
        assertThat(geoCommune1).isNotEqualTo(geoCommune2);
        geoCommune1.setId(null);
        assertThat(geoCommune1).isNotEqualTo(geoCommune2);
    }
}
