package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoCommuneMapperTest {

    private GeoCommuneMapper geoCommuneMapper;

    @BeforeEach
    public void setUp() {
        geoCommuneMapper = new GeoCommuneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoCommuneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoCommuneMapper.fromId(null)).isNull();
    }
}
