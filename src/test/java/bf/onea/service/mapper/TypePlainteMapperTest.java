package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypePlainteMapperTest {

    private TypePlainteMapper typePlainteMapper;

    @BeforeEach
    public void setUp() {
        typePlainteMapper = new TypePlainteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typePlainteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typePlainteMapper.fromId(null)).isNull();
    }
}
