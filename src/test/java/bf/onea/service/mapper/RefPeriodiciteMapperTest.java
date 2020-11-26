package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefPeriodiciteMapperTest {

    private RefPeriodiciteMapper refPeriodiciteMapper;

    @BeforeEach
    public void setUp() {
        refPeriodiciteMapper = new RefPeriodiciteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refPeriodiciteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refPeriodiciteMapper.fromId(null)).isNull();
    }
}
