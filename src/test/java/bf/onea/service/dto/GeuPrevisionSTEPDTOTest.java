package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuPrevisionSTEPDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuPrevisionSTEPDTO.class);
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO1 = new GeuPrevisionSTEPDTO();
        geuPrevisionSTEPDTO1.setId(1L);
        GeuPrevisionSTEPDTO geuPrevisionSTEPDTO2 = new GeuPrevisionSTEPDTO();
        assertThat(geuPrevisionSTEPDTO1).isNotEqualTo(geuPrevisionSTEPDTO2);
        geuPrevisionSTEPDTO2.setId(geuPrevisionSTEPDTO1.getId());
        assertThat(geuPrevisionSTEPDTO1).isEqualTo(geuPrevisionSTEPDTO2);
        geuPrevisionSTEPDTO2.setId(2L);
        assertThat(geuPrevisionSTEPDTO1).isNotEqualTo(geuPrevisionSTEPDTO2);
        geuPrevisionSTEPDTO1.setId(null);
        assertThat(geuPrevisionSTEPDTO1).isNotEqualTo(geuPrevisionSTEPDTO2);
    }
}
