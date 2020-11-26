package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatProgramDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatProgramDTO.class);
        EtatProgramDTO etatProgramDTO1 = new EtatProgramDTO();
        etatProgramDTO1.setId(1L);
        EtatProgramDTO etatProgramDTO2 = new EtatProgramDTO();
        assertThat(etatProgramDTO1).isNotEqualTo(etatProgramDTO2);
        etatProgramDTO2.setId(etatProgramDTO1.getId());
        assertThat(etatProgramDTO1).isEqualTo(etatProgramDTO2);
        etatProgramDTO2.setId(2L);
        assertThat(etatProgramDTO1).isNotEqualTo(etatProgramDTO2);
        etatProgramDTO1.setId(null);
        assertThat(etatProgramDTO1).isNotEqualTo(etatProgramDTO2);
    }
}
