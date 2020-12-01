package bf.onea.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import bf.onea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PrevisionPsaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionPsaDTO.class);
        PrevisionPsaDTO previsionPsaDTO1 = new PrevisionPsaDTO();
        previsionPsaDTO1.setId(1L);
        PrevisionPsaDTO previsionPsaDTO2 = new PrevisionPsaDTO();
        assertThat(previsionPsaDTO1).isNotEqualTo(previsionPsaDTO2);
        previsionPsaDTO2.setId(previsionPsaDTO1.getId());
        assertThat(previsionPsaDTO1).isEqualTo(previsionPsaDTO2);
        previsionPsaDTO2.setId(2L);
        assertThat(previsionPsaDTO1).isNotEqualTo(previsionPsaDTO2);
        previsionPsaDTO1.setId(null);
        assertThat(previsionPsaDTO1).isNotEqualTo(previsionPsaDTO2);
    }
}
