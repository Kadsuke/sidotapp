package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyseParametreMapperTest {

    private AnalyseParametreMapper analyseParametreMapper;

    @BeforeEach
    public void setUp() {
        analyseParametreMapper = new AnalyseParametreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(analyseParametreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(analyseParametreMapper.fromId(null)).isNull();
    }
}
