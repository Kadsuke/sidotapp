package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CentreRegroupementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentreRegroupementDTO.class);
        CentreRegroupementDTO centreRegroupementDTO1 = new CentreRegroupementDTO();
        centreRegroupementDTO1.setId(1L);
        CentreRegroupementDTO centreRegroupementDTO2 = new CentreRegroupementDTO();
        assertThat(centreRegroupementDTO1).isNotEqualTo(centreRegroupementDTO2);
        centreRegroupementDTO2.setId(centreRegroupementDTO1.getId());
        assertThat(centreRegroupementDTO1).isEqualTo(centreRegroupementDTO2);
        centreRegroupementDTO2.setId(2L);
        assertThat(centreRegroupementDTO1).isNotEqualTo(centreRegroupementDTO2);
        centreRegroupementDTO1.setId(null);
        assertThat(centreRegroupementDTO1).isNotEqualTo(centreRegroupementDTO2);
    }
}
