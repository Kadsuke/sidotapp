package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CaracteristiqueOuvrageMapperTest {

    private CaracteristiqueOuvrageMapper caracteristiqueOuvrageMapper;

    @BeforeEach
    public void setUp() {
        caracteristiqueOuvrageMapper = new CaracteristiqueOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(caracteristiqueOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(caracteristiqueOuvrageMapper.fromId(null)).isNull();
    }
}
