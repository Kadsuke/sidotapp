package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuSTEPMapperTest {

    private GeuSTEPMapper geuSTEPMapper;

    @BeforeEach
    public void setUp() {
        geuSTEPMapper = new GeuSTEPMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuSTEPMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuSTEPMapper.fromId(null)).isNull();
    }
}
