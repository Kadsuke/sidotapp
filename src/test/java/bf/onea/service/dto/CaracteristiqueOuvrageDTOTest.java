package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class CaracteristiqueOuvrageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristiqueOuvrageDTO.class);
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO1 = new CaracteristiqueOuvrageDTO();
        caracteristiqueOuvrageDTO1.setId(1L);
        CaracteristiqueOuvrageDTO caracteristiqueOuvrageDTO2 = new CaracteristiqueOuvrageDTO();
        assertThat(caracteristiqueOuvrageDTO1).isNotEqualTo(caracteristiqueOuvrageDTO2);
        caracteristiqueOuvrageDTO2.setId(caracteristiqueOuvrageDTO1.getId());
        assertThat(caracteristiqueOuvrageDTO1).isEqualTo(caracteristiqueOuvrageDTO2);
        caracteristiqueOuvrageDTO2.setId(2L);
        assertThat(caracteristiqueOuvrageDTO1).isNotEqualTo(caracteristiqueOuvrageDTO2);
        caracteristiqueOuvrageDTO1.setId(null);
        assertThat(caracteristiqueOuvrageDTO1).isNotEqualTo(caracteristiqueOuvrageDTO2);
    }
}
