package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class RefAnneeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefAnneeDTO.class);
        RefAnneeDTO refAnneeDTO1 = new RefAnneeDTO();
        refAnneeDTO1.setId(1L);
        RefAnneeDTO refAnneeDTO2 = new RefAnneeDTO();
        assertThat(refAnneeDTO1).isNotEqualTo(refAnneeDTO2);
        refAnneeDTO2.setId(refAnneeDTO1.getId());
        assertThat(refAnneeDTO1).isEqualTo(refAnneeDTO2);
        refAnneeDTO2.setId(2L);
        assertThat(refAnneeDTO1).isNotEqualTo(refAnneeDTO2);
        refAnneeDTO1.setId(null);
        assertThat(refAnneeDTO1).isNotEqualTo(refAnneeDTO2);
    }
}
