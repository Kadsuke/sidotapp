package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoParcelleMapperTest {

    private GeoParcelleMapper geoParcelleMapper;

    @BeforeEach
    public void setUp() {
        geoParcelleMapper = new GeoParcelleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoParcelleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoParcelleMapper.fromId(null)).isNull();
    }
}
