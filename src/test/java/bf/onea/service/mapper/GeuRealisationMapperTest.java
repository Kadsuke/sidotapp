package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuRealisationMapperTest {

    private GeuRealisationMapper geuRealisationMapper;

    @BeforeEach
    public void setUp() {
        geuRealisationMapper = new GeuRealisationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuRealisationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuRealisationMapper.fromId(null)).isNull();
    }
}
