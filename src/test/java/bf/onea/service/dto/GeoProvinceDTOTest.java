package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoProvinceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoProvinceDTO.class);
        GeoProvinceDTO geoProvinceDTO1 = new GeoProvinceDTO();
        geoProvinceDTO1.setId(1L);
        GeoProvinceDTO geoProvinceDTO2 = new GeoProvinceDTO();
        assertThat(geoProvinceDTO1).isNotEqualTo(geoProvinceDTO2);
        geoProvinceDTO2.setId(geoProvinceDTO1.getId());
        assertThat(geoProvinceDTO1).isEqualTo(geoProvinceDTO2);
        geoProvinceDTO2.setId(2L);
        assertThat(geoProvinceDTO1).isNotEqualTo(geoProvinceDTO2);
        geoProvinceDTO1.setId(null);
        assertThat(geoProvinceDTO1).isNotEqualTo(geoProvinceDTO2);
    }
}
