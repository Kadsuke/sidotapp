package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuSTBVMapperTest {

    private GeuSTBVMapper geuSTBVMapper;

    @BeforeEach
    public void setUp() {
        geuSTBVMapper = new GeuSTBVMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuSTBVMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuSTBVMapper.fromId(null)).isNull();
    }
}
