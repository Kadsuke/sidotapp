package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoRegionMapperTest {

    private GeoRegionMapper geoRegionMapper;

    @BeforeEach
    public void setUp() {
        geoRegionMapper = new GeoRegionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoRegionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoRegionMapper.fromId(null)).isNull();
    }
}
