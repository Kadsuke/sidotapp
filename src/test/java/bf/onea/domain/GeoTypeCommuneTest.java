package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoTypeCommuneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoTypeCommune.class);
        GeoTypeCommune geoTypeCommune1 = new GeoTypeCommune();
        geoTypeCommune1.setId(1L);
        GeoTypeCommune geoTypeCommune2 = new GeoTypeCommune();
        geoTypeCommune2.setId(geoTypeCommune1.getId());
        assertThat(geoTypeCommune1).isEqualTo(geoTypeCommune2);
        geoTypeCommune2.setId(2L);
        assertThat(geoTypeCommune1).isNotEqualTo(geoTypeCommune2);
        geoTypeCommune1.setId(null);
        assertThat(geoTypeCommune1).isNotEqualTo(geoTypeCommune2);
    }
}
