package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoLocaliteMapperTest {

    private GeoLocaliteMapper geoLocaliteMapper;

    @BeforeEach
    public void setUp() {
        geoLocaliteMapper = new GeoLocaliteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoLocaliteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoLocaliteMapper.fromId(null)).isNull();
    }
}
