package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class EtatEquipementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtatEquipement.class);
        EtatEquipement etatEquipement1 = new EtatEquipement();
        etatEquipement1.setId(1L);
        EtatEquipement etatEquipement2 = new EtatEquipement();
        etatEquipement2.setId(etatEquipement1.getId());
        assertThat(etatEquipement1).isEqualTo(etatEquipement2);
        etatEquipement2.setId(2L);
        assertThat(etatEquipement1).isNotEqualTo(etatEquipement2);
        etatEquipement1.setId(null);
        assertThat(etatEquipement1).isNotEqualTo(etatEquipement2);
    }
}
