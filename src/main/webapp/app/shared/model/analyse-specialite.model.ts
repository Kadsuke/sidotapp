import { IAnalyseParametre } from 'app/shared/model/analyse-parametre.model';

export interface IAnalyseSpecialite {
  id?: number;
  libelle?: string;
  analyseParametres?: IAnalyseParametre[];
}

export class AnalyseSpecialite implements IAnalyseSpecialite {
  constructor(public id?: number, public libelle?: string, public analyseParametres?: IAnalyseParametre[]) {}
}
