package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefDomaineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefDomaineDTO.class);
        RefDomaineDTO refDomaineDTO1 = new RefDomaineDTO();
        refDomaineDTO1.setId(1L);
        RefDomaineDTO refDomaineDTO2 = new RefDomaineDTO();
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
        refDomaineDTO2.setId(refDomaineDTO1.getId());
        assertThat(refDomaineDTO1).isEqualTo(refDomaineDTO2);
        refDomaineDTO2.setId(2L);
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
        refDomaineDTO1.setId(null);
        assertThat(refDomaineDTO1).isNotEqualTo(refDomaineDTO2);
    }
}
