package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoProvinceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoProvince.class);
        GeoProvince geoProvince1 = new GeoProvince();
        geoProvince1.setId(1L);
        GeoProvince geoProvince2 = new GeoProvince();
        geoProvince2.setId(geoProvince1.getId());
        assertThat(geoProvince1).isEqualTo(geoProvince2);
        geoProvince2.setId(2L);
        assertThat(geoProvince1).isNotEqualTo(geoProvince2);
        geoProvince1.setId(null);
        assertThat(geoProvince1).isNotEqualTo(geoProvince2);
    }
}
