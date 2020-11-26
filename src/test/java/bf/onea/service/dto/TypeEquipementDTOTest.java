package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeEquipementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEquipementDTO.class);
        TypeEquipementDTO typeEquipementDTO1 = new TypeEquipementDTO();
        typeEquipementDTO1.setId(1L);
        TypeEquipementDTO typeEquipementDTO2 = new TypeEquipementDTO();
        assertThat(typeEquipementDTO1).isNotEqualTo(typeEquipementDTO2);
        typeEquipementDTO2.setId(typeEquipementDTO1.getId());
        assertThat(typeEquipementDTO1).isEqualTo(typeEquipementDTO2);
        typeEquipementDTO2.setId(2L);
        assertThat(typeEquipementDTO1).isNotEqualTo(typeEquipementDTO2);
        typeEquipementDTO1.setId(null);
        assertThat(typeEquipementDTO1).isNotEqualTo(typeEquipementDTO2);
    }
}
