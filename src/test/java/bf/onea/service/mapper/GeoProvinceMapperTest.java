package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoProvinceMapperTest {

    private GeoProvinceMapper geoProvinceMapper;

    @BeforeEach
    public void setUp() {
        geoProvinceMapper = new GeoProvinceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoProvinceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoProvinceMapper.fromId(null)).isNull();
    }
}
