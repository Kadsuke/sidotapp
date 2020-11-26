package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoSecteurMapperTest {

    private GeoSecteurMapper geoSecteurMapper;

    @BeforeEach
    public void setUp() {
        geoSecteurMapper = new GeoSecteurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(geoSecteurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoSecteurMapper.fromId(null)).isNull();
    }
}
