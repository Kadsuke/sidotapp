package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeAnalyseMapperTest {

    private TypeAnalyseMapper typeAnalyseMapper;

    @BeforeEach
    public void setUp() {
        typeAnalyseMapper = new TypeAnalyseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeAnalyseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeAnalyseMapper.fromId(null)).isNull();
    }
}
