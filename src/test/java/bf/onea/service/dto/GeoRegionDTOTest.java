package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoRegionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoRegionDTO.class);
        GeoRegionDTO geoRegionDTO1 = new GeoRegionDTO();
        geoRegionDTO1.setId(1L);
        GeoRegionDTO geoRegionDTO2 = new GeoRegionDTO();
        assertThat(geoRegionDTO1).isNotEqualTo(geoRegionDTO2);
        geoRegionDTO2.setId(geoRegionDTO1.getId());
        assertThat(geoRegionDTO1).isEqualTo(geoRegionDTO2);
        geoRegionDTO2.setId(2L);
        assertThat(geoRegionDTO1).isNotEqualTo(geoRegionDTO2);
        geoRegionDTO1.setId(null);
        assertThat(geoRegionDTO1).isNotEqualTo(geoRegionDTO2);
    }
}
