package bf.onea.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
