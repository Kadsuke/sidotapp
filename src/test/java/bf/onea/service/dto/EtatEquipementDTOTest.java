package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatEquipementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatEquipementDTO.class);
        EtatEquipementDTO etatEquipementDTO1 = new EtatEquipementDTO();
        etatEquipementDTO1.setId(1L);
        EtatEquipementDTO etatEquipementDTO2 = new EtatEquipementDTO();
        assertThat(etatEquipementDTO1).isNotEqualTo(etatEquipementDTO2);
        etatEquipementDTO2.setId(etatEquipementDTO1.getId());
        assertThat(etatEquipementDTO1).isEqualTo(etatEquipementDTO2);
        etatEquipementDTO2.setId(2L);
        assertThat(etatEquipementDTO1).isNotEqualTo(etatEquipementDTO2);
        etatEquipementDTO1.setId(null);
        assertThat(etatEquipementDTO1).isNotEqualTo(etatEquipementDTO2);
    }
}
