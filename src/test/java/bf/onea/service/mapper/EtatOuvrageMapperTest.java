package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatOuvrageMapperTest {

    private EtatOuvrageMapper etatOuvrageMapper;

    @BeforeEach
    public void setUp() {
        etatOuvrageMapper = new EtatOuvrageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatOuvrageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatOuvrageMapper.fromId(null)).isNull();
    }
}
