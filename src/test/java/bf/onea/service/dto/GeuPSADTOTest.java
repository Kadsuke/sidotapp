package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPSADTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPSADTO.class);
        GeuPSADTO geuPSADTO1 = new GeuPSADTO();
        geuPSADTO1.setId(1L);
        GeuPSADTO geuPSADTO2 = new GeuPSADTO();
        assertThat(geuPSADTO1).isNotEqualTo(geuPSADTO2);
        geuPSADTO2.setId(geuPSADTO1.getId());
        assertThat(geuPSADTO1).isEqualTo(geuPSADTO2);
        geuPSADTO2.setId(2L);
        assertThat(geuPSADTO1).isNotEqualTo(geuPSADTO2);
        geuPSADTO1.setId(null);
        assertThat(geuPSADTO1).isNotEqualTo(geuPSADTO2);
    }
}
