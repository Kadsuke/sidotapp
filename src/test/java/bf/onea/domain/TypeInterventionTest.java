package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class TypeInterventionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeIntervention.class);
        TypeIntervention typeIntervention1 = new TypeIntervention();
        typeIntervention1.setId(1L);
        TypeIntervention typeIntervention2 = new TypeIntervention();
        typeIntervention2.setId(typeIntervention1.getId());
        assertThat(typeIntervention1).isEqualTo(typeIntervention2);
        typeIntervention2.setId(2L);
        assertThat(typeIntervention1).isNotEqualTo(typeIntervention2);
        typeIntervention1.setId(null);
        assertThat(typeIntervention1).isNotEqualTo(typeIntervention2);
    }
}
