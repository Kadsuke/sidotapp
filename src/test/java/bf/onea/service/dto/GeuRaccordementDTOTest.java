package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRaccordementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRaccordementDTO.class);
        GeuRaccordementDTO geuRaccordementDTO1 = new GeuRaccordementDTO();
        geuRaccordementDTO1.setId(1L);
        GeuRaccordementDTO geuRaccordementDTO2 = new GeuRaccordementDTO();
        assertThat(geuRaccordementDTO1).isNotEqualTo(geuRaccordementDTO2);
        geuRaccordementDTO2.setId(geuRaccordementDTO1.getId());
        assertThat(geuRaccordementDTO1).isEqualTo(geuRaccordementDTO2);
        geuRaccordementDTO2.setId(2L);
        assertThat(geuRaccordementDTO1).isNotEqualTo(geuRaccordementDTO2);
        geuRaccordementDTO1.setId(null);
        assertThat(geuRaccordementDTO1).isNotEqualTo(geuRaccordementDTO2);
    }
}
