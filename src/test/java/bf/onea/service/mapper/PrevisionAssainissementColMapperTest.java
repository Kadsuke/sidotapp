package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrevisionAssainissementColMapperTest {

    private PrevisionAssainissementColMapper previsionAssainissementColMapper;

    @BeforeEach
    public void setUp() {
        previsionAssainissementColMapper = new PrevisionAssainissementColMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsionAssainissementColMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsionAssainissementColMapper.fromId(null)).isNull();
    }
}
