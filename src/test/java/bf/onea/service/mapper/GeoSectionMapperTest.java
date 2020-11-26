package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoSectionMapperTest {

    private GeoSectionMapper geoSectionMapper;

    @BeforeEach
    public void setUp() {
        geoSectionMapper = new GeoSectionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoSectionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoSectionMapper.fromId(null)).isNull();
    }
}
