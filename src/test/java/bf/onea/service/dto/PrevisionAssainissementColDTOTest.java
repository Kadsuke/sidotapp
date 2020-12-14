package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class PrevisionAssainissementColDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionAssainissementColDTO.class);
        PrevisionAssainissementColDTO previsionAssainissementColDTO1 = new PrevisionAssainissementColDTO();
        previsionAssainissementColDTO1.setId(1L);
        PrevisionAssainissementColDTO previsionAssainissementColDTO2 = new PrevisionAssainissementColDTO();
        assertThat(previsionAssainissementColDTO1).isNotEqualTo(previsionAssainissementColDTO2);
        previsionAssainissementColDTO2.setId(previsionAssainissementColDTO1.getId());
        assertThat(previsionAssainissementColDTO1).isEqualTo(previsionAssainissementColDTO2);
        previsionAssainissementColDTO2.setId(2L);
        assertThat(previsionAssainissementColDTO1).isNotEqualTo(previsionAssainissementColDTO2);
        previsionAssainissementColDTO1.setId(null);
        assertThat(previsionAssainissementColDTO1).isNotEqualTo(previsionAssainissementColDTO2);
    }
}
