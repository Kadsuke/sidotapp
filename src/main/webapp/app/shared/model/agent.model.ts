import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

export interface IAgent {
  id?: number;
  nom?: string;
  numero?: string;
  role?: string;
  geuRaccordements?: IGeuRaccordement[];
  siteId?: number;
}

export class Agent implements IAgent {
  constructor(
    public id?: number,
    public nom?: string,
    public numero?: string,
    public role?: string,
    public geuRaccordements?: IGeuRaccordement[],
    public siteId?: number
  ) {}
}
