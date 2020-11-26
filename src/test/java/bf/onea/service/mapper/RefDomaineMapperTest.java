package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefDomaineMapperTest {

    private RefDomaineMapper refDomaineMapper;

    @BeforeEach
    public void setUp() {
        refDomaineMapper = new RefDomaineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refDomaineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refDomaineMapper.fromId(null)).isNull();
    }
}
