package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoLotDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoLotDTO.class);
        GeoLotDTO geoLotDTO1 = new GeoLotDTO();
        geoLotDTO1.setId(1L);
        GeoLotDTO geoLotDTO2 = new GeoLotDTO();
        assertThat(geoLotDTO1).isNotEqualTo(geoLotDTO2);
        geoLotDTO2.setId(geoLotDTO1.getId());
        assertThat(geoLotDTO1).isEqualTo(geoLotDTO2);
        geoLotDTO2.setId(2L);
        assertThat(geoLotDTO1).isNotEqualTo(geoLotDTO2);
        geoLotDTO1.setId(null);
        assertThat(geoLotDTO1).isNotEqualTo(geoLotDTO2);
    }
}
