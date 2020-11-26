package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoLocaliteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoLocaliteDTO.class);
        GeoLocaliteDTO geoLocaliteDTO1 = new GeoLocaliteDTO();
        geoLocaliteDTO1.setId(1L);
        GeoLocaliteDTO geoLocaliteDTO2 = new GeoLocaliteDTO();
        assertThat(geoLocaliteDTO1).isNotEqualTo(geoLocaliteDTO2);
        geoLocaliteDTO2.setId(geoLocaliteDTO1.getId());
        assertThat(geoLocaliteDTO1).isEqualTo(geoLocaliteDTO2);
        geoLocaliteDTO2.setId(2L);
        assertThat(geoLocaliteDTO1).isNotEqualTo(geoLocaliteDTO2);
        geoLocaliteDTO1.setId(null);
        assertThat(geoLocaliteDTO1).isNotEqualTo(geoLocaliteDTO2);
    }
}
