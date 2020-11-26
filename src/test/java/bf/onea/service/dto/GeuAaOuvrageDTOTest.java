package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeuAaOuvrageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeuAaOuvrageDTO.class);
        GeuAaOuvrageDTO geuAaOuvrageDTO1 = new GeuAaOuvrageDTO();
        geuAaOuvrageDTO1.setId(1L);
        GeuAaOuvrageDTO geuAaOuvrageDTO2 = new GeuAaOuvrageDTO();
        assertThat(geuAaOuvrageDTO1).isNotEqualTo(geuAaOuvrageDTO2);
        geuAaOuvrageDTO2.setId(geuAaOuvrageDTO1.getId());
        assertThat(geuAaOuvrageDTO1).isEqualTo(geuAaOuvrageDTO2);
        geuAaOuvrageDTO2.setId(2L);
        assertThat(geuAaOuvrageDTO1).isNotEqualTo(geuAaOuvrageDTO2);
        geuAaOuvrageDTO1.setId(null);
        assertThat(geuAaOuvrageDTO1).isNotEqualTo(geuAaOuvrageDTO2);
    }
}
