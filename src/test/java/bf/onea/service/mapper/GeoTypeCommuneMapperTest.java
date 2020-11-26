package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoTypeCommuneMapperTest {

    private GeoTypeCommuneMapper geoTypeCommuneMapper;

    @BeforeEach
    public void setUp() {
        geoTypeCommuneMapper = new GeoTypeCommuneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoTypeCommuneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoTypeCommuneMapper.fromId(null)).isNull();
    }
}
