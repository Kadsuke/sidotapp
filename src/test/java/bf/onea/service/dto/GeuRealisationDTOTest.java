package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRealisationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRealisationDTO.class);
        GeuRealisationDTO geuRealisationDTO1 = new GeuRealisationDTO();
        geuRealisationDTO1.setId(1L);
        GeuRealisationDTO geuRealisationDTO2 = new GeuRealisationDTO();
        assertThat(geuRealisationDTO1).isNotEqualTo(geuRealisationDTO2);
        geuRealisationDTO2.setId(geuRealisationDTO1.getId());
        assertThat(geuRealisationDTO1).isEqualTo(geuRealisationDTO2);
        geuRealisationDTO2.setId(2L);
        assertThat(geuRealisationDTO1).isNotEqualTo(geuRealisationDTO2);
        geuRealisationDTO1.setId(null);
        assertThat(geuRealisationDTO1).isNotEqualTo(geuRealisationDTO2);
    }
}
