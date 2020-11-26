package bf.onea.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeBeneficiaireMapperTest {

    private TypeBeneficiaireMapper typeBeneficiaireMapper;

    @BeforeEach
    public void setUp() {
        typeBeneficiaireMapper = new TypeBeneficiaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeBeneficiaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeBeneficiaireMapper.fromId(null)).isNull();
    }
}
