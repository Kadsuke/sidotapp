package bf.onea.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrevisionPsaMapperTest {
    private PrevisionPsaMapper previsionPsaMapper;

    @BeforeEach
    public void setUp() {
        previsionPsaMapper = new PrevisionPsaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsionPsaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsionPsaMapper.fromId(null)).isNull();
    }
}
