package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoCommuneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoCommuneDTO.class);
        GeoCommuneDTO geoCommuneDTO1 = new GeoCommuneDTO();
        geoCommuneDTO1.setId(1L);
        GeoCommuneDTO geoCommuneDTO2 = new GeoCommuneDTO();
        assertThat(geoCommuneDTO1).isNotEqualTo(geoCommuneDTO2);
        geoCommuneDTO2.setId(geoCommuneDTO1.getId());
        assertThat(geoCommuneDTO1).isEqualTo(geoCommuneDTO2);
        geoCommuneDTO2.setId(2L);
        assertThat(geoCommuneDTO1).isNotEqualTo(geoCommuneDTO2);
        geoCommuneDTO1.setId(null);
        assertThat(geoCommuneDTO1).isNotEqualTo(geoCommuneDTO2);
    }
}
