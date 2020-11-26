package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CentreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentreDTO.class);
        CentreDTO centreDTO1 = new CentreDTO();
        centreDTO1.setId(1L);
        CentreDTO centreDTO2 = new CentreDTO();
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
        centreDTO2.setId(centreDTO1.getId());
        assertThat(centreDTO1).isEqualTo(centreDTO2);
        centreDTO2.setId(2L);
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
        centreDTO1.setId(null);
        assertThat(centreDTO1).isNotEqualTo(centreDTO2);
    }
}
