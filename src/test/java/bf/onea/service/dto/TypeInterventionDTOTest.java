package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeInterventionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInterventionDTO.class);
        TypeInterventionDTO typeInterventionDTO1 = new TypeInterventionDTO();
        typeInterventionDTO1.setId(1L);
        TypeInterventionDTO typeInterventionDTO2 = new TypeInterventionDTO();
        assertThat(typeInterventionDTO1).isNotEqualTo(typeInterventionDTO2);
        typeInterventionDTO2.setId(typeInterventionDTO1.getId());
        assertThat(typeInterventionDTO1).isEqualTo(typeInterventionDTO2);
        typeInterventionDTO2.setId(2L);
        assertThat(typeInterventionDTO1).isNotEqualTo(typeInterventionDTO2);
        typeInterventionDTO1.setId(null);
        assertThat(typeInterventionDTO1).isNotEqualTo(typeInterventionDTO2);
    }
}
