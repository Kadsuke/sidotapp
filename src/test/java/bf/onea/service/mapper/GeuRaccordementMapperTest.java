package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuRaccordementMapperTest {

    private GeuRaccordementMapper geuRaccordementMapper;

    @BeforeEach
    public void setUp() {
        geuRaccordementMapper = new GeuRaccordementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuRaccordementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuRaccordementMapper.fromId(null)).isNull();
    }
}
