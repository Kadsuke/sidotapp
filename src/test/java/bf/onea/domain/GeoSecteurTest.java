package bf.onea.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoSecteurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoSecteur.class);
        GeoSecteur geoSecteur1 = new GeoSecteur();
        geoSecteur1.setId(1L);
        GeoSecteur geoSecteur2 = new GeoSecteur();
        geoSecteur2.setId(geoSecteur1.getId());
        assertThat(geoSecteur1).isEqualTo(geoSecteur2);
        geoSecteur2.setId(2L);
        assertThat(geoSecteur1).isNotEqualTo(geoSecteur2);
        geoSecteur1.setId(null);
        assertThat(geoSecteur1).isNotEqualTo(geoSecteur2);
    }
}
