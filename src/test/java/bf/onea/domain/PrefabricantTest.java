package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class PrefabricantTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prefabricant.class);
        Prefabricant prefabricant1 = new Prefabricant();
        prefabricant1.setId(1L);
        Prefabricant prefabricant2 = new Prefabricant();
        prefabricant2.setId(prefabricant1.getId());
        assertThat(prefabricant1).isEqualTo(prefabricant2);
        prefabricant2.setId(2L);
        assertThat(prefabricant1).isNotEqualTo(prefabricant2);
        prefabricant1.setId(null);
        assertThat(prefabricant1).isNotEqualTo(prefabricant2);
    }
}
