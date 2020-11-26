package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModeEvacExcretaMapperTest {

    private ModeEvacExcretaMapper modeEvacExcretaMapper;

    @BeforeEach
    public void setUp() {
        modeEvacExcretaMapper = new ModeEvacExcretaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(modeEvacExcretaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(modeEvacExcretaMapper.fromId(null)).isNull();
    }
}
