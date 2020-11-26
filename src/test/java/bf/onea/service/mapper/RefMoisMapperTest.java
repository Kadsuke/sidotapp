package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefMoisMapperTest {

    private RefMoisMapper refMoisMapper;

    @BeforeEach
    public void setUp() {
        refMoisMapper = new RefMoisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refMoisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refMoisMapper.fromId(null)).isNull();
    }
}
