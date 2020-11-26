import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface INatureOuvrage {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class NatureOuvrage implements INatureOuvrage {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
