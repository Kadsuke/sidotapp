package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoTypeCommuneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoTypeCommuneDTO.class);
        GeoTypeCommuneDTO geoTypeCommuneDTO1 = new GeoTypeCommuneDTO();
        geoTypeCommuneDTO1.setId(1L);
        GeoTypeCommuneDTO geoTypeCommuneDTO2 = new GeoTypeCommuneDTO();
        assertThat(geoTypeCommuneDTO1).isNotEqualTo(geoTypeCommuneDTO2);
        geoTypeCommuneDTO2.setId(geoTypeCommuneDTO1.getId());
        assertThat(geoTypeCommuneDTO1).isEqualTo(geoTypeCommuneDTO2);
        geoTypeCommuneDTO2.setId(2L);
        assertThat(geoTypeCommuneDTO1).isNotEqualTo(geoTypeCommuneDTO2);
        geoTypeCommuneDTO1.setId(null);
        assertThat(geoTypeCommuneDTO1).isNotEqualTo(geoTypeCommuneDTO2);
    }
}
