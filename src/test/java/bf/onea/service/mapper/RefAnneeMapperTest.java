package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RefAnneeMapperTest {

    private RefAnneeMapper refAnneeMapper;

    @BeforeEach
    public void setUp() {
        refAnneeMapper = new RefAnneeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(refAnneeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(refAnneeMapper.fromId(null)).isNull();
    }
}
