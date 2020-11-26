package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuRealisationSTBVMapperTest {

    private GeuRealisationSTBVMapper geuRealisationSTBVMapper;

    @BeforeEach
    public void setUp() {
        geuRealisationSTBVMapper = new GeuRealisationSTBVMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuRealisationSTBVMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuRealisationSTBVMapper.fromId(null)).isNull();
    }
}
