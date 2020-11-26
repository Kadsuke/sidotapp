package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeEquipementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEquipement.class);
        TypeEquipement typeEquipement1 = new TypeEquipement();
        typeEquipement1.setId(1L);
        TypeEquipement typeEquipement2 = new TypeEquipement();
        typeEquipement2.setId(typeEquipement1.getId());
        assertThat(typeEquipement1).isEqualTo(typeEquipement2);
        typeEquipement2.setId(2L);
        assertThat(typeEquipement1).isNotEqualTo(typeEquipement2);
        typeEquipement1.setId(null);
        assertThat(typeEquipement1).isNotEqualTo(typeEquipement2);
    }
}
