package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NatureOuvrageMapperTest {

    private NatureOuvrageMapper natureOuvrageMapper;

    @BeforeEach
    public void setUp() {
        natureOuvrageMapper = new NatureOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(natureOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(natureOuvrageMapper.fromId(null)).isNull();
    }
}
