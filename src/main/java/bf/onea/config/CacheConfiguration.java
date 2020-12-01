package bf.onea.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, bf.onea.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, bf.onea.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, bf.onea.domain.User.class.getName());
            createCache(cm, bf.onea.domain.Authority.class.getName());
            createCache(cm, bf.onea.domain.User.class.getName() + ".authorities");
            createCache(cm, bf.onea.domain.RefAnnee.class.getName());
            createCache(cm, bf.onea.domain.RefMois.class.getName());
            createCache(cm, bf.onea.domain.RefDomaine.class.getName());
            createCache(cm, bf.onea.domain.RefSousDomaine.class.getName());
            createCache(cm, bf.onea.domain.RefPeriodicite.class.getName());
            createCache(cm, bf.onea.domain.GeoTypeCommune.class.getName());
            createCache(cm, bf.onea.domain.GeoTypeCommune.class.getName() + ".geoCommunes");
            createCache(cm, bf.onea.domain.GeoRegion.class.getName());
            createCache(cm, bf.onea.domain.GeoRegion.class.getName() + ".geoProvinces");
            createCache(cm, bf.onea.domain.GeoProvince.class.getName());
            createCache(cm, bf.onea.domain.GeoProvince.class.getName() + ".geoCommunes");
            createCache(cm, bf.onea.domain.GeoCommune.class.getName());
            createCache(cm, bf.onea.domain.GeoCommune.class.getName() + ".geoLocalites");
            createCache(cm, bf.onea.domain.GeoCommune.class.getName() + ".geuPSAS");
            createCache(cm, bf.onea.domain.GeoLocalite.class.getName());
            createCache(cm, bf.onea.domain.GeoLocalite.class.getName() + ".geoSecteurs");
            createCache(cm, bf.onea.domain.GeoSecteur.class.getName());
            createCache(cm, bf.onea.domain.GeoSecteur.class.getName() + ".geoSections");
            createCache(cm, bf.onea.domain.GeoSection.class.getName());
            createCache(cm, bf.onea.domain.GeoSection.class.getName() + ".geoLots");
            createCache(cm, bf.onea.domain.GeoLot.class.getName());
            createCache(cm, bf.onea.domain.GeoLot.class.getName() + ".geoParcelles");
            createCache(cm, bf.onea.domain.GeoParcelle.class.getName());
            createCache(cm, bf.onea.domain.GeoParcelle.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.GeoParcelle.class.getName() + ".geuRaccordements");
            createCache(cm, bf.onea.domain.DirectionRegionale.class.getName());
            createCache(cm, bf.onea.domain.DirectionRegionale.class.getName() + ".centreRegroupements");
            createCache(cm, bf.onea.domain.CentreRegroupement.class.getName());
            createCache(cm, bf.onea.domain.CentreRegroupement.class.getName() + ".centres");
            createCache(cm, bf.onea.domain.Centre.class.getName());
            createCache(cm, bf.onea.domain.Centre.class.getName() + ".sites");
            createCache(cm, bf.onea.domain.Site.class.getName());
            createCache(cm, bf.onea.domain.Site.class.getName() + ".agents");
            createCache(cm, bf.onea.domain.Agent.class.getName());
            createCache(cm, bf.onea.domain.Agent.class.getName() + ".geuRaccordements");
            createCache(cm, bf.onea.domain.CategorieRessources.class.getName());
            createCache(cm, bf.onea.domain.CategorieRessources.class.getName() + ".typeOuvrages");
            createCache(cm, bf.onea.domain.TypeOuvrage.class.getName());
            createCache(cm, bf.onea.domain.TypeOuvrage.class.getName() + ".caracteristiqueOuvrages");
            createCache(cm, bf.onea.domain.CaracteristiqueOuvrage.class.getName());
            createCache(cm, bf.onea.domain.CaracteristiqueOuvrage.class.getName() + ".typeOuvrages");
            createCache(cm, bf.onea.domain.EtatOuvrage.class.getName());
            createCache(cm, bf.onea.domain.EtatEquipement.class.getName());
            createCache(cm, bf.onea.domain.TypeEquipement.class.getName());
            createCache(cm, bf.onea.domain.ProduitChimique.class.getName());
            createCache(cm, bf.onea.domain.AnalyseSpecialite.class.getName());
            createCache(cm, bf.onea.domain.AnalyseSpecialite.class.getName() + ".analyseParametres");
            createCache(cm, bf.onea.domain.TypeAnalyse.class.getName());
            createCache(cm, bf.onea.domain.AnalyseParametre.class.getName());
            createCache(cm, bf.onea.domain.TypeBeneficiaire.class.getName());
            createCache(cm, bf.onea.domain.Laboratoire.class.getName());
            createCache(cm, bf.onea.domain.EtatProgram.class.getName());
            createCache(cm, bf.onea.domain.EtatProjet.class.getName());
            createCache(cm, bf.onea.domain.Prestataire.class.getName());
            createCache(cm, bf.onea.domain.Bailleur.class.getName());
            createCache(cm, bf.onea.domain.SourceApprovEp.class.getName());
            createCache(cm, bf.onea.domain.SourceApprovEp.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.TypeHabitation.class.getName());
            createCache(cm, bf.onea.domain.TypeHabitation.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.ModeEvacuationEauUsee.class.getName());
            createCache(cm, bf.onea.domain.ModeEvacuationEauUsee.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.ModeEvacExcreta.class.getName());
            createCache(cm, bf.onea.domain.ModeEvacExcreta.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.Prefabricant.class.getName());
            createCache(cm, bf.onea.domain.Prefabricant.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.Macon.class.getName());
            createCache(cm, bf.onea.domain.Macon.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.NatureOuvrage.class.getName());
            createCache(cm, bf.onea.domain.NatureOuvrage.class.getName() + ".geuAaOuvrages");
            createCache(cm, bf.onea.domain.TypeIntervention.class.getName());
            createCache(cm, bf.onea.domain.TypePlainte.class.getName());
            createCache(cm, bf.onea.domain.GeuSTBV.class.getName());
            createCache(cm, bf.onea.domain.GeuSTBV.class.getName() + ".geuRealisationSTBVS");
            createCache(cm, bf.onea.domain.GeuSTBV.class.getName() + ".geuPrevisionSTBVS");
            createCache(cm, bf.onea.domain.GeuPrevisionSTBV.class.getName());
            createCache(cm, bf.onea.domain.GeuRealisationSTBV.class.getName());
            createCache(cm, bf.onea.domain.GeuSTEP.class.getName());
            createCache(cm, bf.onea.domain.GeuSTEP.class.getName() + ".geuPrevisionSTEPS");
            createCache(cm, bf.onea.domain.GeuPSA.class.getName());
            createCache(cm, bf.onea.domain.GeuPrevisionSTEP.class.getName());
            createCache(cm, bf.onea.domain.GeuAaOuvrage.class.getName());
            createCache(cm, bf.onea.domain.GeuAaOuvrage.class.getName() + ".geuRealisations");
            createCache(cm, bf.onea.domain.GeuRealisation.class.getName());
            createCache(cm, bf.onea.domain.GeuRaccordement.class.getName());
            createCache(cm, bf.onea.domain.Tacherons.class.getName());
            createCache(cm, bf.onea.domain.Tacherons.class.getName() + ".geuRaccordements");
            createCache(cm, bf.onea.domain.GeuUsage.class.getName());
            createCache(cm, bf.onea.domain.GeuUsage.class.getName() + ".geuRaccordements");
            createCache(cm, bf.onea.domain.PrevisionAssainissementAu.class.getName());
            createCache(cm, bf.onea.domain.PrevisionAssainissementCol.class.getName());
            createCache(cm, bf.onea.domain.PrevisionPsa.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
