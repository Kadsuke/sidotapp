package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectionRegionaleMapperTest {

    private DirectionRegionaleMapper directionRegionaleMapper;

    @BeforeEach
    public void setUp() {
        directionRegionaleMapper = new DirectionRegionaleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(directionRegionaleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(directionRegionaleMapper.fromId(null)).isNull();
    }
}
