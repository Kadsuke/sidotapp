package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LaboratoireMapperTest {

    private LaboratoireMapper laboratoireMapper;

    @BeforeEach
    public void setUp() {
        laboratoireMapper = new LaboratoireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(laboratoireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(laboratoireMapper.fromId(null)).isNull();
    }
}
