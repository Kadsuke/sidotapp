package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class MaconDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaconDTO.class);
        MaconDTO maconDTO1 = new MaconDTO();
        maconDTO1.setId(1L);
        MaconDTO maconDTO2 = new MaconDTO();
        assertThat(maconDTO1).isNotEqualTo(maconDTO2);
        maconDTO2.setId(maconDTO1.getId());
        assertThat(maconDTO1).isEqualTo(maconDTO2);
        maconDTO2.setId(2L);
        assertThat(maconDTO1).isNotEqualTo(maconDTO2);
        maconDTO1.setId(null);
        assertThat(maconDTO1).isNotEqualTo(maconDTO2);
    }
}
