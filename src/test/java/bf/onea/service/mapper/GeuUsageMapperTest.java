package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuUsageMapperTest {

    private GeuUsageMapper geuUsageMapper;

    @BeforeEach
    public void setUp() {
        geuUsageMapper = new GeuUsageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuUsageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuUsageMapper.fromId(null)).isNull();
    }
}
