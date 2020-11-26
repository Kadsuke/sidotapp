package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class BailleurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BailleurDTO.class);
        BailleurDTO bailleurDTO1 = new BailleurDTO();
        bailleurDTO1.setId(1L);
        BailleurDTO bailleurDTO2 = new BailleurDTO();
        assertThat(bailleurDTO1).isNotEqualTo(bailleurDTO2);
        bailleurDTO2.setId(bailleurDTO1.getId());
        assertThat(bailleurDTO1).isEqualTo(bailleurDTO2);
        bailleurDTO2.setId(2L);
        assertThat(bailleurDTO1).isNotEqualTo(bailleurDTO2);
        bailleurDTO1.setId(null);
        assertThat(bailleurDTO1).isNotEqualTo(bailleurDTO2);
    }
}
