package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatOuvrageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatOuvrageDTO.class);
        EtatOuvrageDTO etatOuvrageDTO1 = new EtatOuvrageDTO();
        etatOuvrageDTO1.setId(1L);
        EtatOuvrageDTO etatOuvrageDTO2 = new EtatOuvrageDTO();
        assertThat(etatOuvrageDTO1).isNotEqualTo(etatOuvrageDTO2);
        etatOuvrageDTO2.setId(etatOuvrageDTO1.getId());
        assertThat(etatOuvrageDTO1).isEqualTo(etatOuvrageDTO2);
        etatOuvrageDTO2.setId(2L);
        assertThat(etatOuvrageDTO1).isNotEqualTo(etatOuvrageDTO2);
        etatOuvrageDTO1.setId(null);
        assertThat(etatOuvrageDTO1).isNotEqualTo(etatOuvrageDTO2);
    }
}
