package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatEquipementMapperTest {

    private EtatEquipementMapper etatEquipementMapper;

    @BeforeEach
    public void setUp() {
        etatEquipementMapper = new EtatEquipementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatEquipementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatEquipementMapper.fromId(null)).isNull();
    }
}
