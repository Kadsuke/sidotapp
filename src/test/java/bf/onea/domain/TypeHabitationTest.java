package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeHabitationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeHabitation.class);
        TypeHabitation typeHabitation1 = new TypeHabitation();
        typeHabitation1.setId(1L);
        TypeHabitation typeHabitation2 = new TypeHabitation();
        typeHabitation2.setId(typeHabitation1.getId());
        assertThat(typeHabitation1).isEqualTo(typeHabitation2);
        typeHabitation2.setId(2L);
        assertThat(typeHabitation1).isNotEqualTo(typeHabitation2);
        typeHabitation1.setId(null);
        assertThat(typeHabitation1).isNotEqualTo(typeHabitation2);
    }
}
