package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeAnalyseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAnalyseDTO.class);
        TypeAnalyseDTO typeAnalyseDTO1 = new TypeAnalyseDTO();
        typeAnalyseDTO1.setId(1L);
        TypeAnalyseDTO typeAnalyseDTO2 = new TypeAnalyseDTO();
        assertThat(typeAnalyseDTO1).isNotEqualTo(typeAnalyseDTO2);
        typeAnalyseDTO2.setId(typeAnalyseDTO1.getId());
        assertThat(typeAnalyseDTO1).isEqualTo(typeAnalyseDTO2);
        typeAnalyseDTO2.setId(2L);
        assertThat(typeAnalyseDTO1).isNotEqualTo(typeAnalyseDTO2);
        typeAnalyseDTO1.setId(null);
        assertThat(typeAnalyseDTO1).isNotEqualTo(typeAnalyseDTO2);
    }
}
