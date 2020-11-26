package bf.onea.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.onea.web.rest.TestUtil;

public class GeoSecteurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoSecteurDTO.class);
        GeoSecteurDTO geoSecteurDTO1 = new GeoSecteurDTO();
        geoSecteurDTO1.setId(1L);
        GeoSecteurDTO geoSecteurDTO2 = new GeoSecteurDTO();
        assertThat(geoSecteurDTO1).isNotEqualTo(geoSecteurDTO2);
        geoSecteurDTO2.setId(geoSecteurDTO1.getId());
        assertThat(geoSecteurDTO1).isEqualTo(geoSecteurDTO2);
        geoSecteurDTO2.setId(2L);
        assertThat(geoSecteurDTO1).isNotEqualTo(geoSecteurDTO2);
        geoSecteurDTO1.setId(null);
        assertThat(geoSecteurDTO1).isNotEqualTo(geoSecteurDTO2);
    }
}
