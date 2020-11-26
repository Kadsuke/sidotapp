package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CaracteristiqueOuvrageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristiqueOuvrage.class);
        CaracteristiqueOuvrage caracteristiqueOuvrage1 = new CaracteristiqueOuvrage();
        caracteristiqueOuvrage1.setId(1L);
        CaracteristiqueOuvrage caracteristiqueOuvrage2 = new CaracteristiqueOuvrage();
        caracteristiqueOuvrage2.setId(caracteristiqueOuvrage1.getId());
        assertThat(caracteristiqueOuvrage1).isEqualTo(caracteristiqueOuvrage2);
        caracteristiqueOuvrage2.setId(2L);
        assertThat(caracteristiqueOuvrage1).isNotEqualTo(caracteristiqueOuvrage2);
        caracteristiqueOuvrage1.setId(null);
        assertThat(caracteristiqueOuvrage1).isNotEqualTo(caracteristiqueOuvrage2);
    }
}
