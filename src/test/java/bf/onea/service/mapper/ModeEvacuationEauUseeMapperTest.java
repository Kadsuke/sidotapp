package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModeEvacuationEauUseeMapperTest {

    private ModeEvacuationEauUseeMapper modeEvacuationEauUseeMapper;

    @BeforeEach
    public void setUp() {
        modeEvacuationEauUseeMapper = new ModeEvacuationEauUseeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(modeEvacuationEauUseeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(modeEvacuationEauUseeMapper.fromId(null)).isNull();
    }
}
