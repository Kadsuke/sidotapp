package bf.onea.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import bf.onea.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PrevisionAssainissementAuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisionAssainissementAuDTO.class);
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO1 = new PrevisionAssainissementAuDTO();
        previsionAssainissementAuDTO1.setId(1L);
        PrevisionAssainissementAuDTO previsionAssainissementAuDTO2 = new PrevisionAssainissementAuDTO();
        assertThat(previsionAssainissementAuDTO1).isNotEqualTo(previsionAssainissementAuDTO2);
        previsionAssainissementAuDTO2.setId(previsionAssainissementAuDTO1.getId());
        assertThat(previsionAssainissementAuDTO1).isEqualTo(previsionAssainissementAuDTO2);
        previsionAssainissementAuDTO2.setId(2L);
        assertThat(previsionAssainissementAuDTO1).isNotEqualTo(previsionAssainissementAuDTO2);
        previsionAssainissementAuDTO1.setId(null);
        assertThat(previsionAssainissementAuDTO1).isNotEqualTo(previsionAssainissementAuDTO2);
    }
}
