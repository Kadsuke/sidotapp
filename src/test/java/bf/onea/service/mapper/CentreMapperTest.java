package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CentreMapperTest {

    private CentreMapper centreMapper;

    @BeforeEach
    public void setUp() {
        centreMapper = new CentreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(centreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(centreMapper.fromId(null)).isNull();
    }
}
