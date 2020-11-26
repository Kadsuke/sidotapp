package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoLotTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoLot.class);
        GeoLot geoLot1 = new GeoLot();
        geoLot1.setId(1L);
        GeoLot geoLot2 = new GeoLot();
        geoLot2.setId(geoLot1.getId());
        assertThat(geoLot1).isEqualTo(geoLot2);
        geoLot2.setId(2L);
        assertThat(geoLot1).isNotEqualTo(geoLot2);
        geoLot1.setId(null);
        assertThat(geoLot1).isNotEqualTo(geoLot2);
    }
}
