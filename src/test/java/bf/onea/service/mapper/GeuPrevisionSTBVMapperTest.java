package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuPrevisionSTBVMapperTest {

    private GeuPrevisionSTBVMapper geuPrevisionSTBVMapper;

    @BeforeEach
    public void setUp() {
        geuPrevisionSTBVMapper = new GeuPrevisionSTBVMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuPrevisionSTBVMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuPrevisionSTBVMapper.fromId(null)).isNull();
    }
}
