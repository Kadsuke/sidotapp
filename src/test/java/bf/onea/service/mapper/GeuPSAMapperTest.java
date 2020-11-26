package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuPSAMapperTest {

    private GeuPSAMapper geuPSAMapper;

    @BeforeEach
    public void setUp() {
        geuPSAMapper = new GeuPSAMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuPSAMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuPSAMapper.fromId(null)).isNull();
    }
}
