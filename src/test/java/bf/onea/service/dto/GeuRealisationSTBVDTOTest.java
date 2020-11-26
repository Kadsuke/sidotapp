package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuRealisationSTBVDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuRealisationSTBVDTO.class);
        GeuRealisationSTBVDTO geuRealisationSTBVDTO1 = new GeuRealisationSTBVDTO();
        geuRealisationSTBVDTO1.setId(1L);
        GeuRealisationSTBVDTO geuRealisationSTBVDTO2 = new GeuRealisationSTBVDTO();
        assertThat(geuRealisationSTBVDTO1).isNotEqualTo(geuRealisationSTBVDTO2);
        geuRealisationSTBVDTO2.setId(geuRealisationSTBVDTO1.getId());
        assertThat(geuRealisationSTBVDTO1).isEqualTo(geuRealisationSTBVDTO2);
        geuRealisationSTBVDTO2.setId(2L);
        assertThat(geuRealisationSTBVDTO1).isNotEqualTo(geuRealisationSTBVDTO2);
        geuRealisationSTBVDTO1.setId(null);
        assertThat(geuRealisationSTBVDTO1).isNotEqualTo(geuRealisationSTBVDTO2);
    }
}
