package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoSectionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoSectionDTO.class);
        GeoSectionDTO geoSectionDTO1 = new GeoSectionDTO();
        geoSectionDTO1.setId(1L);
        GeoSectionDTO geoSectionDTO2 = new GeoSectionDTO();
        assertThat(geoSectionDTO1).isNotEqualTo(geoSectionDTO2);
        geoSectionDTO2.setId(geoSectionDTO1.getId());
        assertThat(geoSectionDTO1).isEqualTo(geoSectionDTO2);
        geoSectionDTO2.setId(2L);
        assertThat(geoSectionDTO1).isNotEqualTo(geoSectionDTO2);
        geoSectionDTO1.setId(null);
        assertThat(geoSectionDTO1).isNotEqualTo(geoSectionDTO2);
    }
}
