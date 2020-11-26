package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeEquipementMapperTest {

    private TypeEquipementMapper typeEquipementMapper;

    @BeforeEach
    public void setUp() {
        typeEquipementMapper = new TypeEquipementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeEquipementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeEquipementMapper.fromId(null)).isNull();
    }
}
