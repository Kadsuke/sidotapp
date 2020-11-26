package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeInterventionMapperTest {

    private TypeInterventionMapper typeInterventionMapper;

    @BeforeEach
    public void setUp() {
        typeInterventionMapper = new TypeInterventionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeInterventionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeInterventionMapper.fromId(null)).isNull();
    }
}
