package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrefabricantMapperTest {

    private PrefabricantMapper prefabricantMapper;

    @BeforeEach
    public void setUp() {
        prefabricantMapper = new PrefabricantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(prefabricantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(prefabricantMapper.fromId(null)).isNull();
    }
}
