package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CentreRegroupementMapperTest {

    private CentreRegroupementMapper centreRegroupementMapper;

    @BeforeEach
    public void setUp() {
        centreRegroupementMapper = new CentreRegroupementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(centreRegroupementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(centreRegroupementMapper.fromId(null)).isNull();
    }
}
