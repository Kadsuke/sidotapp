package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MaconMapperTest {

    private MaconMapper maconMapper;

    @BeforeEach
    public void setUp() {
        maconMapper = new MaconMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(maconMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(maconMapper.fromId(null)).isNull();
    }
}
