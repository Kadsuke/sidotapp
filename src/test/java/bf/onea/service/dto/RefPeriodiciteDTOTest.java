package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefPeriodiciteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefPeriodiciteDTO.class);
        RefPeriodiciteDTO refPeriodiciteDTO1 = new RefPeriodiciteDTO();
        refPeriodiciteDTO1.setId(1L);
        RefPeriodiciteDTO refPeriodiciteDTO2 = new RefPeriodiciteDTO();
        assertThat(refPeriodiciteDTO1).isNotEqualTo(refPeriodiciteDTO2);
        refPeriodiciteDTO2.setId(refPeriodiciteDTO1.getId());
        assertThat(refPeriodiciteDTO1).isEqualTo(refPeriodiciteDTO2);
        refPeriodiciteDTO2.setId(2L);
        assertThat(refPeriodiciteDTO1).isNotEqualTo(refPeriodiciteDTO2);
        refPeriodiciteDTO1.setId(null);
        assertThat(refPeriodiciteDTO1).isNotEqualTo(refPeriodiciteDTO2);
    }
}
