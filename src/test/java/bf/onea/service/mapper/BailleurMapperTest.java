package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BailleurMapperTest {

    private BailleurMapper bailleurMapper;

    @BeforeEach
    public void setUp() {
        bailleurMapper = new BailleurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bailleurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bailleurMapper.fromId(null)).isNull();
    }
}
