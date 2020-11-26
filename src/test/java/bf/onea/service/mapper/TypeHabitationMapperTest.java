package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeHabitationMapperTest {

    private TypeHabitationMapper typeHabitationMapper;

    @BeforeEach
    public void setUp() {
        typeHabitationMapper = new TypeHabitationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeHabitationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeHabitationMapper.fromId(null)).isNull();
    }
}
