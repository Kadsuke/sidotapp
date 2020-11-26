package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategorieRessourcesMapperTest {

    private CategorieRessourcesMapper categorieRessourcesMapper;

    @BeforeEach
    public void setUp() {
        categorieRessourcesMapper = new CategorieRessourcesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieRessourcesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieRessourcesMapper.fromId(null)).isNull();
    }
}
