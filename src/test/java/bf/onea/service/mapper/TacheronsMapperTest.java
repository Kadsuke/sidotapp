package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TacheronsMapperTest {

    private TacheronsMapper tacheronsMapper;

    @BeforeEach
    public void setUp() {
        tacheronsMapper = new TacheronsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tacheronsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tacheronsMapper.fromId(null)).isNull();
    }
}
