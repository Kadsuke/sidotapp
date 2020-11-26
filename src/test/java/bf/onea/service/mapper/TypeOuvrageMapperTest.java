package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeOuvrageMapperTest {

    private TypeOuvrageMapper typeOuvrageMapper;

    @BeforeEach
    public void setUp() {
        typeOuvrageMapper = new TypeOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeOuvrageMapper.fromId(null)).isNull();
    }
}
