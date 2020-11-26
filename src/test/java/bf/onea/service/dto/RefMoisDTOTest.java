package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefMoisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefMoisDTO.class);
        RefMoisDTO refMoisDTO1 = new RefMoisDTO();
        refMoisDTO1.setId(1L);
        RefMoisDTO refMoisDTO2 = new RefMoisDTO();
        assertThat(refMoisDTO1).isNotEqualTo(refMoisDTO2);
        refMoisDTO2.setId(refMoisDTO1.getId());
        assertThat(refMoisDTO1).isEqualTo(refMoisDTO2);
        refMoisDTO2.setId(2L);
        assertThat(refMoisDTO1).isNotEqualTo(refMoisDTO2);
        refMoisDTO1.setId(null);
        assertThat(refMoisDTO1).isNotEqualTo(refMoisDTO2);
    }
}
