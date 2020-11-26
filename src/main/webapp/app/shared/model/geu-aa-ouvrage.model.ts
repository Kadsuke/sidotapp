import { Moment } from 'moment';
import { IGeuRealisation } from 'app/shared/model/geu-realisation.model';

export interface IGeuAaOuvrage {
  id?: number;
  refOuvrage?: string;
  prjAppuis?: string;
  numCompteur?: string;
  nomBenef?: string;
  prenomBenef?: string;
  professionBenef?: string;
  nbUsagers?: number;
  codeUn?: string;
  dateRemiseDevis?: Moment;
  dateDebutTravaux?: Moment;
  dateFinTravaux?: Moment;
  numNomRue?: string;
  numNomPorte?: string;
  menage?: string;
  subvOnea?: number;
  subvProjet?: number;
  autreSubv?: number;
  refBonFourniture?: string;
  realisPorte?: number;
  realisToles?: number;
  animateur?: string;
  superviseur?: string;
  controleur?: string;
  geuRealisations?: IGeuRealisation[];
  geoparcelleId?: number;
  natureouvrageId?: number;
  typehabitationId?: number;
  sourceapprovepId?: number;
  modeevacuationeauuseeId?: number;
  modeevacexcretaId?: number;
  maconId?: number;
  prefabricantId?: number;
}

export class GeuAaOuvrage implements IGeuAaOuvrage {
  constructor(
    public id?: number,
    public refOuvrage?: string,
    public prjAppuis?: string,
    public numCompteur?: string,
    public nomBenef?: string,
    public prenomBenef?: string,
    public professionBenef?: string,
    public nbUsagers?: number,
    public codeUn?: string,
    public dateRemiseDevis?: Moment,
    public dateDebutTravaux?: Moment,
    public dateFinTravaux?: Moment,
    public numNomRue?: string,
    public numNomPorte?: string,
    public menage?: string,
    public subvOnea?: number,
    public subvProjet?: number,
    public autreSubv?: number,
    public refBonFourniture?: string,
    public realisPorte?: number,
    public realisToles?: number,
    public animateur?: string,
    public superviseur?: string,
    public controleur?: string,
    public geuRealisations?: IGeuRealisation[],
    public geoparcelleId?: number,
    public natureouvrageId?: number,
    public typehabitationId?: number,
    public sourceapprovepId?: number,
    public modeevacuationeauuseeId?: number,
    public modeevacexcretaId?: number,
    public maconId?: number,
    public prefabricantId?: number
  ) {}
}
