package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefSousDomaineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSousDomaineDTO.class);
        RefSousDomaineDTO refSousDomaineDTO1 = new RefSousDomaineDTO();
        refSousDomaineDTO1.setId(1L);
        RefSousDomaineDTO refSousDomaineDTO2 = new RefSousDomaineDTO();
        assertThat(refSousDomaineDTO1).isNotEqualTo(refSousDomaineDTO2);
        refSousDomaineDTO2.setId(refSousDomaineDTO1.getId());
        assertThat(refSousDomaineDTO1).isEqualTo(refSousDomaineDTO2);
        refSousDomaineDTO2.setId(2L);
        assertThat(refSousDomaineDTO1).isNotEqualTo(refSousDomaineDTO2);
        refSousDomaineDTO1.setId(null);
        assertThat(refSousDomaineDTO1).isNotEqualTo(refSousDomaineDTO2);
    }
}
