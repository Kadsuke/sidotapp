package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrestataireMapperTest {

    private PrestataireMapper prestataireMapper;

    @BeforeEach
    public void setUp() {
        prestataireMapper = new PrestataireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(prestataireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(prestataireMapper.fromId(null)).isNull();
    }
}
