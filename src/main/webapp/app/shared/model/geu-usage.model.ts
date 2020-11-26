import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

export interface IGeuUsage {
  id?: number;
  libelle?: string;
  geuRaccordements?: IGeuRaccordement[];
}

export class GeuUsage implements IGeuUsage {
  constructor(public id?: number, public libelle?: string, public geuRaccordements?: IGeuRaccordement[]) {}
}
