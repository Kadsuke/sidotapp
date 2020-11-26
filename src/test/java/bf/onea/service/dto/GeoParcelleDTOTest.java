package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoParcelleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoParcelleDTO.class);
        GeoParcelleDTO geoParcelleDTO1 = new GeoParcelleDTO();
        geoParcelleDTO1.setId(1L);
        GeoParcelleDTO geoParcelleDTO2 = new GeoParcelleDTO();
        assertThat(geoParcelleDTO1).isNotEqualTo(geoParcelleDTO2);
        geoParcelleDTO2.setId(geoParcelleDTO1.getId());
        assertThat(geoParcelleDTO1).isEqualTo(geoParcelleDTO2);
        geoParcelleDTO2.setId(2L);
        assertThat(geoParcelleDTO1).isNotEqualTo(geoParcelleDTO2);
        geoParcelleDTO1.setId(null);
        assertThat(geoParcelleDTO1).isNotEqualTo(geoParcelleDTO2);
    }
}
