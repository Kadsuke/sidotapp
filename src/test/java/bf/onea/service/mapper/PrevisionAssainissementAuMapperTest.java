package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrevisionAssainissementAuMapperTest {

    private PrevisionAssainissementAuMapper previsionAssainissementAuMapper;

    @BeforeEach
    public void setUp() {
        previsionAssainissementAuMapper = new PrevisionAssainissementAuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsionAssainissementAuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsionAssainissementAuMapper.fromId(null)).isNull();
    }
}
