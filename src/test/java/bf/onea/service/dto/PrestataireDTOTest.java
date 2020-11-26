package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class PrestataireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrestataireDTO.class);
        PrestataireDTO prestataireDTO1 = new PrestataireDTO();
        prestataireDTO1.setId(1L);
        PrestataireDTO prestataireDTO2 = new PrestataireDTO();
        assertThat(prestataireDTO1).isNotEqualTo(prestataireDTO2);
        prestataireDTO2.setId(prestataireDTO1.getId());
        assertThat(prestataireDTO1).isEqualTo(prestataireDTO2);
        prestataireDTO2.setId(2L);
        assertThat(prestataireDTO1).isNotEqualTo(prestataireDTO2);
        prestataireDTO1.setId(null);
        assertThat(prestataireDTO1).isNotEqualTo(prestataireDTO2);
    }
}
