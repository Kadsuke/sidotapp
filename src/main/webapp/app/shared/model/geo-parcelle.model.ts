import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';
import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

export interface IGeoParcelle {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
  geuRaccordements?: IGeuRaccordement[];
  geolotId?: number;
}

export class GeoParcelle implements IGeoParcelle {
  constructor(
    public id?: number,
    public libelle?: string,
    public geuAaOuvrages?: IGeuAaOuvrage[],
    public geuRaccordements?: IGeuRaccordement[],
    public geolotId?: number
  ) {}
}
