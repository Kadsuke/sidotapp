package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeOuvrageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeOuvrageDTO.class);
        TypeOuvrageDTO typeOuvrageDTO1 = new TypeOuvrageDTO();
        typeOuvrageDTO1.setId(1L);
        TypeOuvrageDTO typeOuvrageDTO2 = new TypeOuvrageDTO();
        assertThat(typeOuvrageDTO1).isNotEqualTo(typeOuvrageDTO2);
        typeOuvrageDTO2.setId(typeOuvrageDTO1.getId());
        assertThat(typeOuvrageDTO1).isEqualTo(typeOuvrageDTO2);
        typeOuvrageDTO2.setId(2L);
        assertThat(typeOuvrageDTO1).isNotEqualTo(typeOuvrageDTO2);
        typeOuvrageDTO1.setId(null);
        assertThat(typeOuvrageDTO1).isNotEqualTo(typeOuvrageDTO2);
    }
}
