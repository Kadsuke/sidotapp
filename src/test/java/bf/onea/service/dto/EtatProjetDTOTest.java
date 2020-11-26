package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatProjetDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatProjetDTO.class);
        EtatProjetDTO etatProjetDTO1 = new EtatProjetDTO();
        etatProjetDTO1.setId(1L);
        EtatProjetDTO etatProjetDTO2 = new EtatProjetDTO();
        assertThat(etatProjetDTO1).isNotEqualTo(etatProjetDTO2);
        etatProjetDTO2.setId(etatProjetDTO1.getId());
        assertThat(etatProjetDTO1).isEqualTo(etatProjetDTO2);
        etatProjetDTO2.setId(2L);
        assertThat(etatProjetDTO1).isNotEqualTo(etatProjetDTO2);
        etatProjetDTO1.setId(null);
        assertThat(etatProjetDTO1).isNotEqualTo(etatProjetDTO2);
    }
}
