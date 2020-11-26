package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnalyseSpecialiteMapperTest {

    private AnalyseSpecialiteMapper analyseSpecialiteMapper;

    @BeforeEach
    public void setUp() {
        analyseSpecialiteMapper = new AnalyseSpecialiteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(analyseSpecialiteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(analyseSpecialiteMapper.fromId(null)).isNull();
    }
}
