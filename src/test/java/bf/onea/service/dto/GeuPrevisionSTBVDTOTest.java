package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPrevisionSTBVDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPrevisionSTBVDTO.class);
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO1 = new GeuPrevisionSTBVDTO();
        geuPrevisionSTBVDTO1.setId(1L);
        GeuPrevisionSTBVDTO geuPrevisionSTBVDTO2 = new GeuPrevisionSTBVDTO();
        assertThat(geuPrevisionSTBVDTO1).isNotEqualTo(geuPrevisionSTBVDTO2);
        geuPrevisionSTBVDTO2.setId(geuPrevisionSTBVDTO1.getId());
        assertThat(geuPrevisionSTBVDTO1).isEqualTo(geuPrevisionSTBVDTO2);
        geuPrevisionSTBVDTO2.setId(2L);
        assertThat(geuPrevisionSTBVDTO1).isNotEqualTo(geuPrevisionSTBVDTO2);
        geuPrevisionSTBVDTO1.setId(null);
        assertThat(geuPrevisionSTBVDTO1).isNotEqualTo(geuPrevisionSTBVDTO2);
    }
}
