package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuPrevisionSTEPMapperTest {

    private GeuPrevisionSTEPMapper geuPrevisionSTEPMapper;

    @BeforeEach
    public void setUp() {
        geuPrevisionSTEPMapper = new GeuPrevisionSTEPMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuPrevisionSTEPMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuPrevisionSTEPMapper.fromId(null)).isNull();
    }
}
