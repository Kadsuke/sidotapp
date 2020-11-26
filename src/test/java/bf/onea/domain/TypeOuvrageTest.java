package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeOuvrageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeOuvrage.class);
        TypeOuvrage typeOuvrage1 = new TypeOuvrage();
        typeOuvrage1.setId(1L);
        TypeOuvrage typeOuvrage2 = new TypeOuvrage();
        typeOuvrage2.setId(typeOuvrage1.getId());
        assertThat(typeOuvrage1).isEqualTo(typeOuvrage2);
        typeOuvrage2.setId(2L);
        assertThat(typeOuvrage1).isNotEqualTo(typeOuvrage2);
        typeOuvrage1.setId(null);
        assertThat(typeOuvrage1).isNotEqualTo(typeOuvrage2);
    }
}
