package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SourceApprovEpMapperTest {

    private SourceApprovEpMapper sourceApprovEpMapper;

    @BeforeEach
    public void setUp() {
        sourceApprovEpMapper = new SourceApprovEpMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sourceApprovEpMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sourceApprovEpMapper.fromId(null)).isNull();
    }
}
