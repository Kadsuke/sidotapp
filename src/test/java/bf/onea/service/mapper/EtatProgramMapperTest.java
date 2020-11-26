package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtatProgramMapperTest {

    private EtatProgramMapper etatProgramMapper;

    @BeforeEach
    public void setUp() {
        etatProgramMapper = new EtatProgramMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etatProgramMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etatProgramMapper.fromId(null)).isNull();
    }
}
