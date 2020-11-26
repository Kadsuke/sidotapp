package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatProjetMapperTest {

    private EtatProjetMapper etatProjetMapper;

    @BeforeEach
    public void setUp() {
        etatProjetMapper = new EtatProjetMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatProjetMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatProjetMapper.fromId(null)).isNull();
    }
}
