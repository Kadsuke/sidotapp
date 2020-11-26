package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeuAaOuvrageMapperTest {

    private GeuAaOuvrageMapper geuAaOuvrageMapper;

    @BeforeEach
    public void setUp() {
        geuAaOuvrageMapper = new GeuAaOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geuAaOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geuAaOuvrageMapper.fromId(null)).isNull();
    }
}
