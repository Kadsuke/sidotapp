package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProduitChimiqueMapperTest {

    private ProduitChimiqueMapper produitChimiqueMapper;

    @BeforeEach
    public void setUp() {
        produitChimiqueMapper = new ProduitChimiqueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(produitChimiqueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(produitChimiqueMapper.fromId(null)).isNull();
    }
}
