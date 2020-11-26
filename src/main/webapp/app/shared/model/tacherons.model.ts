import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

export interface ITacherons {
  id?: number;
  nom?: string;
  tel?: string;
  adresse?: string;
  geuRaccordements?: IGeuRaccordement[];
}

export class Tacherons implements ITacherons {
  constructor(
    public id?: number,
    public nom?: string,
    public tel?: string,
    public adresse?: string,
    public geuRaccordements?: IGeuRaccordement[]
  ) {}
}
