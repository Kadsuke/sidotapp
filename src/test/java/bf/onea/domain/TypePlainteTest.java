package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypePlainteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePlainte.class);
        TypePlainte typePlainte1 = new TypePlainte();
        typePlainte1.setId(1L);
        TypePlainte typePlainte2 = new TypePlainte();
        typePlainte2.setId(typePlainte1.getId());
        assertThat(typePlainte1).isEqualTo(typePlainte2);
        typePlainte2.setId(2L);
        assertThat(typePlainte1).isNotEqualTo(typePlainte2);
        typePlainte1.setId(null);
        assertThat(typePlainte1).isNotEqualTo(typePlainte2);
    }
}
