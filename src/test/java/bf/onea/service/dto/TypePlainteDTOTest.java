package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypePlainteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePlainteDTO.class);
        TypePlainteDTO typePlainteDTO1 = new TypePlainteDTO();
        typePlainteDTO1.setId(1L);
        TypePlainteDTO typePlainteDTO2 = new TypePlainteDTO();
        assertThat(typePlainteDTO1).isNotEqualTo(typePlainteDTO2);
        typePlainteDTO2.setId(typePlainteDTO1.getId());
        assertThat(typePlainteDTO1).isEqualTo(typePlainteDTO2);
        typePlainteDTO2.setId(2L);
        assertThat(typePlainteDTO1).isNotEqualTo(typePlainteDTO2);
        typePlainteDTO1.setId(null);
        assertThat(typePlainteDTO1).isNotEqualTo(typePlainteDTO2);
    }
}
