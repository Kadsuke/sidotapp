import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ref-annee',
        loadChildren: () => import('./ref-annee/ref-annee.module').then(m => m.SidotRefAnneeModule),
      },
      {
        path: 'ref-mois',
        loadChildren: () => import('./ref-mois/ref-mois.module').then(m => m.SidotRefMoisModule),
      },
      {
        path: 'ref-domaine',
        loadChildren: () => import('./ref-domaine/ref-domaine.module').then(m => m.SidotRefDomaineModule),
      },
      {
        path: 'ref-sous-domaine',
        loadChildren: () => import('./ref-sous-domaine/ref-sous-domaine.module').then(m => m.SidotRefSousDomaineModule),
      },
      {
        path: 'ref-periodicite',
        loadChildren: () => import('./ref-periodicite/ref-periodicite.module').then(m => m.SidotRefPeriodiciteModule),
      },
      {
        path: 'geo-type-commune',
        loadChildren: () => import('./geo-type-commune/geo-type-commune.module').then(m => m.SidotGeoTypeCommuneModule),
      },
      {
        path: 'geo-region',
        loadChildren: () => import('./geo-region/geo-region.module').then(m => m.SidotGeoRegionModule),
      },
      {
        path: 'geo-province',
        loadChildren: () => import('./geo-province/geo-province.module').then(m => m.SidotGeoProvinceModule),
      },
      {
        path: 'geo-commune',
        loadChildren: () => import('./geo-commune/geo-commune.module').then(m => m.SidotGeoCommuneModule),
      },
      {
        path: 'geo-localite',
        loadChildren: () => import('./geo-localite/geo-localite.module').then(m => m.SidotGeoLocaliteModule),
      },
      {
        path: 'geo-secteur',
        loadChildren: () => import('./geo-secteur/geo-secteur.module').then(m => m.SidotGeoSecteurModule),
      },
      {
        path: 'geo-section',
        loadChildren: () => import('./geo-section/geo-section.module').then(m => m.SidotGeoSectionModule),
      },
      {
        path: 'geo-lot',
        loadChildren: () => import('./geo-lot/geo-lot.module').then(m => m.SidotGeoLotModule),
      },
      {
        path: 'geo-parcelle',
        loadChildren: () => import('./geo-parcelle/geo-parcelle.module').then(m => m.SidotGeoParcelleModule),
      },
      {
        path: 'direction-regionale',
        loadChildren: () => import('./direction-regionale/direction-regionale.module').then(m => m.SidotDirectionRegionaleModule),
      },
      {
        path: 'centre-regroupement',
        loadChildren: () => import('./centre-regroupement/centre-regroupement.module').then(m => m.SidotCentreRegroupementModule),
      },
      {
        path: 'centre',
        loadChildren: () => import('./centre/centre.module').then(m => m.SidotCentreModule),
      },
      {
        path: 'site',
        loadChildren: () => import('./site/site.module').then(m => m.SidotSiteModule),
      },
      {
        path: 'agent',
        loadChildren: () => import('./agent/agent.module').then(m => m.SidotAgentModule),
      },
      {
        path: 'categorie-ressources',
        loadChildren: () => import('./categorie-ressources/categorie-ressources.module').then(m => m.SidotCategorieRessourcesModule),
      },
      {
        path: 'type-ouvrage',
        loadChildren: () => import('./type-ouvrage/type-ouvrage.module').then(m => m.SidotTypeOuvrageModule),
      },
      {
        path: 'caracteristique-ouvrage',
        loadChildren: () =>
          import('./caracteristique-ouvrage/caracteristique-ouvrage.module').then(m => m.SidotCaracteristiqueOuvrageModule),
      },
      {
        path: 'etat-ouvrage',
        loadChildren: () => import('./etat-ouvrage/etat-ouvrage.module').then(m => m.SidotEtatOuvrageModule),
      },
      {
        path: 'etat-equipement',
        loadChildren: () => import('./etat-equipement/etat-equipement.module').then(m => m.SidotEtatEquipementModule),
      },
      {
        path: 'type-equipement',
        loadChildren: () => import('./type-equipement/type-equipement.module').then(m => m.SidotTypeEquipementModule),
      },
      {
        path: 'produit-chimique',
        loadChildren: () => import('./produit-chimique/produit-chimique.module').then(m => m.SidotProduitChimiqueModule),
      },
      {
        path: 'analyse-specialite',
        loadChildren: () => import('./analyse-specialite/analyse-specialite.module').then(m => m.SidotAnalyseSpecialiteModule),
      },
      {
        path: 'type-analyse',
        loadChildren: () => import('./type-analyse/type-analyse.module').then(m => m.SidotTypeAnalyseModule),
      },
      {
        path: 'analyse-parametre',
        loadChildren: () => import('./analyse-parametre/analyse-parametre.module').then(m => m.SidotAnalyseParametreModule),
      },
      {
        path: 'type-beneficiaire',
        loadChildren: () => import('./type-beneficiaire/type-beneficiaire.module').then(m => m.SidotTypeBeneficiaireModule),
      },
      {
        path: 'laboratoire',
        loadChildren: () => import('./laboratoire/laboratoire.module').then(m => m.SidotLaboratoireModule),
      },
      {
        path: 'etat-program',
        loadChildren: () => import('./etat-program/etat-program.module').then(m => m.SidotEtatProgramModule),
      },
      {
        path: 'etat-projet',
        loadChildren: () => import('./etat-projet/etat-projet.module').then(m => m.SidotEtatProjetModule),
      },
      {
        path: 'prestataire',
        loadChildren: () => import('./prestataire/prestataire.module').then(m => m.SidotPrestataireModule),
      },
      {
        path: 'bailleur',
        loadChildren: () => import('./bailleur/bailleur.module').then(m => m.SidotBailleurModule),
      },
      {
        path: 'source-approv-ep',
        loadChildren: () => import('./source-approv-ep/source-approv-ep.module').then(m => m.SidotSourceApprovEpModule),
      },
      {
        path: 'type-habitation',
        loadChildren: () => import('./type-habitation/type-habitation.module').then(m => m.SidotTypeHabitationModule),
      },
      {
        path: 'mode-evacuation-eau-usee',
        loadChildren: () =>
          import('./mode-evacuation-eau-usee/mode-evacuation-eau-usee.module').then(m => m.SidotModeEvacuationEauUseeModule),
      },
      {
        path: 'mode-evac-excreta',
        loadChildren: () => import('./mode-evac-excreta/mode-evac-excreta.module').then(m => m.SidotModeEvacExcretaModule),
      },
      {
        path: 'prefabricant',
        loadChildren: () => import('./prefabricant/prefabricant.module').then(m => m.SidotPrefabricantModule),
      },
      {
        path: 'macon',
        loadChildren: () => import('./macon/macon.module').then(m => m.SidotMaconModule),
      },
      {
        path: 'nature-ouvrage',
        loadChildren: () => import('./nature-ouvrage/nature-ouvrage.module').then(m => m.SidotNatureOuvrageModule),
      },
      {
        path: 'type-intervention',
        loadChildren: () => import('./type-intervention/type-intervention.module').then(m => m.SidotTypeInterventionModule),
      },
      {
        path: 'type-plainte',
        loadChildren: () => import('./type-plainte/type-plainte.module').then(m => m.SidotTypePlainteModule),
      },
      {
        path: 'geu-stbv',
        loadChildren: () => import('./geu-stbv/geu-stbv.module').then(m => m.SidotGeuSTBVModule),
      },
      {
        path: 'geu-prevision-stbv',
        loadChildren: () => import('./geu-prevision-stbv/geu-prevision-stbv.module').then(m => m.SidotGeuPrevisionSTBVModule),
      },
      {
        path: 'geu-realisation-stbv',
        loadChildren: () => import('./geu-realisation-stbv/geu-realisation-stbv.module').then(m => m.SidotGeuRealisationSTBVModule),
      },
      {
        path: 'geu-step',
        loadChildren: () => import('./geu-step/geu-step.module').then(m => m.SidotGeuSTEPModule),
      },
      {
        path: 'geu-psa',
        loadChildren: () => import('./geu-psa/geu-psa.module').then(m => m.SidotGeuPSAModule),
      },
      {
        path: 'geu-prevision-step',
        loadChildren: () => import('./geu-prevision-step/geu-prevision-step.module').then(m => m.SidotGeuPrevisionSTEPModule),
      },
      {
        path: 'geu-aa-ouvrage',
        loadChildren: () => import('./geu-aa-ouvrage/geu-aa-ouvrage.module').then(m => m.SidotGeuAaOuvrageModule),
      },
      {
        path: 'geu-realisation',
        loadChildren: () => import('./geu-realisation/geu-realisation.module').then(m => m.SidotGeuRealisationModule),
      },
      {
        path: 'geu-raccordement',
        loadChildren: () => import('./geu-raccordement/geu-raccordement.module').then(m => m.SidotGeuRaccordementModule),
      },
      {
        path: 'tacherons',
        loadChildren: () => import('./tacherons/tacherons.module').then(m => m.SidotTacheronsModule),
      },
      {
        path: 'geu-usage',
        loadChildren: () => import('./geu-usage/geu-usage.module').then(m => m.SidotGeuUsageModule),
      },
      {
        path: 'prevision-assainissement-au',
        loadChildren: () =>
          import('./prevision-assainissement-au/prevision-assainissement-au.module').then(m => m.SidotPrevisionAssainissementAuModule),
      },
      {
        path: 'prevision-assainissement-col',
        loadChildren: () =>
          import('./prevision-assainissement-col/prevision-assainissement-col.module').then(m => m.SidotPrevisionAssainissementColModule),
      },
      {
        path: 'prevision-psa',
        loadChildren: () => import('./prevision-psa/prevision-psa.module').then(m => m.SidotPrevisionPsaModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SidotEntityModule {}
