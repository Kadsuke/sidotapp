package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefSousDomaineMapperTest {

    private RefSousDomaineMapper refSousDomaineMapper;

    @BeforeEach
    public void setUp() {
        refSousDomaineMapper = new RefSousDomaineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refSousDomaineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refSousDomaineMapper.fromId(null)).isNull();
    }
}
