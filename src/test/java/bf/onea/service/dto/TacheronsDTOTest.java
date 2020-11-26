package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TacheronsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TacheronsDTO.class);
        TacheronsDTO tacheronsDTO1 = new TacheronsDTO();
        tacheronsDTO1.setId(1L);
        TacheronsDTO tacheronsDTO2 = new TacheronsDTO();
        assertThat(tacheronsDTO1).isNotEqualTo(tacheronsDTO2);
        tacheronsDTO2.setId(tacheronsDTO1.getId());
        assertThat(tacheronsDTO1).isEqualTo(tacheronsDTO2);
        tacheronsDTO2.setId(2L);
        assertThat(tacheronsDTO1).isNotEqualTo(tacheronsDTO2);
        tacheronsDTO1.setId(null);
        assertThat(tacheronsDTO1).isNotEqualTo(tacheronsDTO2);
    }
}
