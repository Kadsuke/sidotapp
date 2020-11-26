package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoLotMapperTest {

    private GeoLotMapper geoLotMapper;

    @BeforeEach
    public void setUp() {
        geoLotMapper = new GeoLotMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoLotMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoLotMapper.fromId(null)).isNull();
    }
}
